package com.viw.viwmall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.viw.viwmall.product.service.CategoryBrandRelationService;
import com.viw.viwmall.product.vo.Catelog2Vo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viw.common.utils.PageUtils;
import com.viw.common.utils.Query;

import com.viw.viwmall.product.dao.CategoryDao;
import com.viw.viwmall.product.entity.CategoryEntity;
import com.viw.viwmall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {


    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;


    @Autowired
    private StringRedisTemplate redisTemplate;



    //TODO 产生堆外内存溢出：OutOfDirectMemoryError
    //1）、springboot2.0以后默认使用lettuce作为操作redis的客户端。它使用netty进行网络通信。
    //2）、lettuce的bug导致netty堆外内存溢出 -Xmx300m；netty如果没有指定堆外内存，默认使用-Xmx300m
    //   可以通过-Dio.netty.maxDirectMemory进行设置
    //解决方案：不能使用-Dio.netty.maxDirectMemory只去调大堆外内存。服务上线长时间还是会出现问题
    //1）、升级lettuce客户端。   2）、切换使用jedis
    //  redisTemplate：
    //  lettuce、jedis操作redis的底层客户端。Spring再次封装redisTemplate；
//    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson2() {
        //给缓存中放json字符串，拿出的json字符串，还用逆转为能用的对象类型；【序列化与反序列化】
        /**
         * 1、空结果缓存：解决缓存穿透
         * 2、设置过期时间（加随机值）：解决缓存雪崩
         * 3、加锁：解决缓存击穿
         */
        //1、加入缓存逻辑,缓存中存的数据是json字符串。
        //JSON跨语言，跨平台兼容。
        String catalogJSON = redisTemplate.opsForValue().get("catalogJSON");
        if (StringUtils.isEmpty(catalogJSON)) {
            //2、缓存中没有,查询数据库
            //保证数据库查询完成以后，将数据放在redis中，这是一个原子操作。
            System.out.println("缓存不命中....将要查询数据库...");
            Map<String, List<Catelog2Vo>> catalogJsonFromDb = getCatalogJsonFromDbWithRedisLock();
            return catalogJsonFromDb;
        }

        System.out.println("缓存命中....直接返回....");
        //转为我们指定的对象。 json=>object
        Map<String, List<Catelog2Vo>> result = JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {
        });
        return result;
    }


    /**
     * 使用reids setnx 作为分布式锁
     * @return
     */
    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDbWithRedisLock() {
        //1、占分布式锁。去redis占坑
        String uuid = UUID.randomUUID().toString();
        //setIfAbsent 如果缺少key 。 设置key来保存字符串value和过期timeout。加锁并同时设置过期时间
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 300, TimeUnit.SECONDS);
        if (lock) {
            System.out.println("获取分布式锁成功...");
            //加锁成功... 执行业务
            //2、设置过期时间，必须和加锁是同步的，原子的
            //redisTemplate.expire("lock",30,TimeUnit.SECONDS);
            Map<String, List<Catelog2Vo>> dataFromDb;
            try {
                dataFromDb = getDataFromDb();
            } finally {
                // 最后调用业务处理逻辑就解锁
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                //删除锁 通过uuid来保证删自己的锁 成功返回1 不成功返回0
                Long lock1 = redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class)
                        , Arrays.asList("lock"), uuid);
            }
            //获取值对比+对比成功删除=原子操作  lua脚本解锁
//            String lockValue = redisTemplate.opsForValue().get("lock");
//            if(uuid.equals(lockValue)){
//                //删除我自己的锁
//                redisTemplate.delete("lock");//删除锁
//            }
            return dataFromDb;
        } else {
            //加锁失败...重试。synchronized ()
            //休眠100ms重试
            System.out.println("获取分布式锁失败...等待重试");
            try {
                Thread.sleep(200);
            } catch (Exception e) {

            }
            return getCatalogJsonFromDbWithRedisLock();//自旋的方式
        }

    }

    // 加锁成功执行查询数据库方法
    private Map<String, List<Catelog2Vo>> getDataFromDb() {
        String catalogJSON = redisTemplate.opsForValue().get("catalogJSON");
        if (!StringUtils.isEmpty(catalogJSON)) {
            //缓存不为null直接返回
            Map<String, List<Catelog2Vo>> result = JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {
            });
            return result;
        }
        System.out.println("查询了数据库.....");

        List<CategoryEntity> selectList = baseMapper.selectList(null);


        List<CategoryEntity> level1Categorys = getParent_cid(selectList, 0L);

        //2、封装数据
        Map<String, List<Catelog2Vo>> parent_cid = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            //1、每一个的一级分类，查到这个一级分类的二级分类
            List<CategoryEntity> categoryEntities = getParent_cid(selectList, v.getCatId());
            //2、封装上面面的结果
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    //1、找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = getParent_cid(selectList, l2.getCatId());
                    if (level3Catelog != null) {
                        List<Catelog2Vo.Catelog3Vo> collect = level3Catelog.stream().map(l3 -> {
                            //2、封装成指定格式
                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return catelog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(collect);
                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }


            return catelog2Vos;
        }));


        //3、查到的数据再放入缓存，将对象转为json放在缓存中
        String s = JSON.toJSONString(parent_cid);
        redisTemplate.opsForValue().set("catalogJSON", s, 1, TimeUnit.DAYS);
        return parent_cid;
    }



    /**
     * 查询出所有一级 二级 三级 分类 数据 返回json给页面
     *
     * @return
     */
    @Cacheable(value = "category",key = "#root.methodName")
    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        System.out.println("查询了数据库.....");
        //提取把分类数据查出来保存，防止重复查询，增加数据库的压力。
        List<CategoryEntity> selectList = baseMapper.selectList(null);
        List<CategoryEntity> level1Categorys = getParent_cid(selectList, 0L);
        //2、封装数据
        Map<String, List<Catelog2Vo>> parent_cid = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            //1、每一个的一级分类，查到这个一级分类的二级分类
            List<CategoryEntity> categoryEntities = getParent_cid(selectList, v.getCatId());
            //2、封装上面面的结果
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    //1、找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = getParent_cid(selectList, l2.getCatId());
                    if (level3Catelog != null) {
                        List<Catelog2Vo.Catelog3Vo> collect = level3Catelog.stream().map(l3 -> {
                            //2、封装成指定格式
                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return catelog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(collect);
                    }
                    //映射  map中的 value
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }


            return catelog2Vos;
        }));
        return parent_cid;
    }
    /**
     * 已经提前查出来了，
     * 返回 CategoryId 是指定的值
     */
    private List<CategoryEntity> getParent_cid(List<CategoryEntity> selectList, Long parent_cid) {
        List<CategoryEntity> collect = selectList.stream().filter(item -> item.getParentCid().equals(parent_cid)).collect(Collectors.toList());
        //return baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", v.getCatId()));
        return collect;
    }

    /**
     * 1、每一个需要缓存的数据我们都来指定要放到那个名字的缓存。【缓存的分区(按照业务类型分)】
     * 2、 @Cacheable({"category"})
     *      代表当前方法的结果需要缓存，如果缓存中有，方法不用调用。
     *      如果缓存中没有，会调用方法，最后将方法的结果放入缓存
     * 3、默认行为
     *      1）、如果缓存中有，方法不用调用。
     *      2）、key默认自动生成；缓存的名字::SimpleKey [](自主生成的key值)
     *      3）、缓存的value的值。默认使用jdk序列化机制，将序列化后的数据存到redis
     *      4）、默认ttl时间 -1；
     *
     *    自定义：
     *      1）、指定生成的缓存使用的key：  key属性指定，接受一个SpEL
     *             SpEL的详细https://docs.spring.io/spring/docs/5.1.12.RELEASE/spring-framework-reference/integration.html#cache-spel-context
     *      2）、指定缓存的数据的存活时间： 配置文件中修改ttl
     *      3）、将数据保存为json格式:
     *              自定义RedisCacheConfiguration即可
     * 4、Spring-Cache的不足；
     *      1）、读模式：
     *          缓存穿透：查询一个null数据。解决：缓存空数据；ache-null-values=true
     *          缓存击穿：大量并发进来同时查询一个正好过期的数据。解决：加锁；？默认是无加锁的;sync = true（加锁，解决击穿）
     *          缓存雪崩：大量的key同时过期。解决：加随机时间。加上过期时间。：spring.cache.redis.time-to-live=3600000
     *      2）、写模式：（缓存与数据库一致）
     *          1）、读写加锁。
     *          2）、引入Canal，感知到MySQL的更新去更新数据库
     *          3）、读多写多，直接去数据库查询就行
     *    总结：
     *      常规数据（读多写少，即时性，一致性要求不高的数据）；完全可以使用Spring-Cache；写模式（只要缓存的数据有过期时间就足够了）
     *      特殊数据：特殊设计
     *
     *   原理：
     *      CacheManager(RedisCacheManager)->Cache(RedisCache)->Cache负责缓存的读写
     *
     *
     * @return
     */
    @Cacheable(value = {"category"},key = "#root.method.name",sync = true)
    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        System.out.println("getLevel1Categorys.....");
        long l = System.currentTimeMillis();
        List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
        return categoryEntities;
    }

    /**
     * 级联更新所有关联的数据
     * @param category
     */
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());
    }


    /**
     *   //[2,25,225]
     * 找到catelogId的完整路径；
     * [父/子/孙]
     * @param catelogId
     * @return
     */
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);

        Collections.reverse(parentPath);


        return parentPath.toArray(new Long[parentPath.size()]);
    }

    //225,25,2
    private List<Long> findParentPath(Long catelogId,List<Long> paths){
        //1、收集当前节点id
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if(byId.getParentCid()!=0){
            findParentPath(byId.getParentCid(),paths);
        }
        return paths;

    }


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }


    /**
     * 如 A的子类是B B的子类是C
     * A:
     *  B:
     *   C:
     * 查询所有商品分类及其子分类
     * @return
     */
    @Override
    public List<CategoryEntity> listWithTree() {
        //查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);
        //2、组装成父子的树形结构
        //2.1）、找到所有的一级分类
        List<CategoryEntity> level1Menus = entities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid() == 0
        ).map((menu)->{
            menu.setChildren(getChildrens(menu,entities));
            return menu;
        }).sorted((menu1,menu2)->{
            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());
        return level1Menus;
    }

    /**
     *  递归查找所有菜单的子菜单
     * @param root 当前菜单
     * @param all 所有菜单
     * @return
     */
    private List<CategoryEntity> getChildrens(CategoryEntity root,List<CategoryEntity> all){
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid() == root.getCatId();
        }).map(categoryEntity -> {
            //1、找到子菜单
            categoryEntity.setChildren(getChildrens(categoryEntity,all));
            return categoryEntity;
        }).sorted((menu1,menu2)->{
            //2、菜单的排序
            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());
        return children;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO  1、检查当前删除的菜单，是否被别的地方引用
        //逻辑删除 （使用标识位来删除）
        baseMapper.deleteBatchIds(asList);
    }
}
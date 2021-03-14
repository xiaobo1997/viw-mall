package com.viw.viwmall.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.viw.common.utils.R;
import com.viw.viwmall.seckill.feign.CouponFeignService;
import com.viw.viwmall.seckill.feign.ProductFeignService;
import com.viw.viwmall.seckill.service.SeckillService;
import com.viw.viwmall.seckill.to.SecKillSkuRedisTo;
import com.viw.viwmall.seckill.vo.SeckillSesssionsWithSkus;
import com.viw.viwmall.seckill.vo.SkuInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/14 22:32
 * @description:
 */
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {


    @Autowired
    CouponFeignService couponFeignService;

    @Autowired
    ProductFeignService productFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RedissonClient redissonClient;

    private final String SESSIONS_CACHE_PREFIX = "seckill:sessions:";
    private final String SKUKILL_CACHE_PREFIX = "seckill:skus";

    private final String SKU_STOCK_SEMAPHORE = "seckill:stock:";//+商品随机码

    private  final String  upload_lock = "seckill:upload:lock";

    @Override
    public void uploadSeckillSkuLatest3Days() {
        //1、扫描最近三天需要参与秒杀的活动
        R session = couponFeignService.getLates3DaySession();
        if (session.getCode() == 0) {
            //上架商品
            List<SeckillSesssionsWithSkus> sessionData = session.getData(new TypeReference<List<SeckillSesssionsWithSkus>>() {
            });
            //缓存到redis
            //1、缓存活动信息
            saveSessionInfos(sessionData);
            //2、缓存活动的关联商品信息
            saveSessionSkuInfos(sessionData);
        }
    }


    /**
     * 缓存活动信息
     * @param sesssions
     */
    private void saveSessionInfos(List<SeckillSesssionsWithSkus> sesssions) {
        if (sesssions != null)
            sesssions.stream().forEach(sesssion -> {

                Long startTime = sesssion.getStartTime().getTime();
                Long endTime = sesssion.getEndTime().getTime();
                String key = SESSIONS_CACHE_PREFIX + startTime + "_" + endTime;
                Boolean hasKey = redisTemplate.hasKey(key);
                if (!hasKey) {
                    List<String> collect = sesssion.getRelationSkus().stream().map(item -> item.getPromotionSessionId() + "_" + item.getSkuId().toString()).collect(Collectors.toList());
                    //缓存活动信息
                    redisTemplate.opsForList().leftPushAll(key, collect);
                    //TODO 设置过期时间[已完成]
                    redisTemplate.expireAt(key, new Date(endTime));
                }


            });
    }

    /**
     * 缓存活动的关联商品信息
     * @param sesssions
     */
    private void saveSessionSkuInfos(List<SeckillSesssionsWithSkus> sesssions) {
        if (sesssions != null)
            sesssions.stream().forEach(sesssion -> {
                //准备hash操作
                BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
                sesssion.getRelationSkus().stream().forEach(seckillSkuVo -> {
                    //4、随机码？  seckill?skuId=1&key=dadlajldj； 复杂脚本攻击接口
                    String token = UUID.randomUUID().toString().replace("-", "");

                    if (!ops.hasKey(seckillSkuVo.getPromotionSessionId().toString() + "_" + seckillSkuVo.getSkuId().toString())) {
                        //缓存商品
                        SecKillSkuRedisTo redisTo = new SecKillSkuRedisTo();
                        //1、远程调用- 查询 sku的基本数据
                        R skuInfo = productFeignService.getSkuInfo(seckillSkuVo.getSkuId());
                        if (skuInfo.getCode() == 0) {
                            SkuInfoVo info = skuInfo.getData("skuInfo", new TypeReference<SkuInfoVo>() {
                            });
                            redisTo.setSkuInfo(info);
                        }

                        //2、sku的秒杀信息
                        BeanUtils.copyProperties(seckillSkuVo, redisTo);

                        //3、设置上当前商品的秒杀时间信息
                        redisTo.setStartTime(sesssion.getStartTime().getTime());
                        redisTo.setEndTime(sesssion.getEndTime().getTime());

                        redisTo.setRandomCode(token);
                        String jsonString = JSON.toJSONString(redisTo);
                        //TODO 每个商品的过期时间不一样。所以，我们在获取当前商品秒杀信息的时候，做主动删除，代码在 getSkuSeckillInfo 方法里面
                        ops.put(seckillSkuVo.getPromotionSessionId().toString() + "_" + seckillSkuVo.getSkuId().toString(), jsonString);
                        //如果当前这个场次的商品的库存信息已经上架就不需要上架
                        //5、使用库存作为分布式的信号量  限流；
                        RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + token);
                        //商品可以秒杀的数量作为信号量- 分布式锁，随机码 作信号量 扣库存
                         semaphore.trySetPermits(seckillSkuVo.getSeckillCount()); // 设置信号量的多少，= 商品的库存
                        //TODO 设置过期时间。
                        semaphore.expireAt(sesssion.getEndTime());
                    }
                });
            });
    }


}

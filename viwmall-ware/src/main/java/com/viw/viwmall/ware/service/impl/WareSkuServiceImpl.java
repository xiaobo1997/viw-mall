package com.viw.viwmall.ware.service.impl;

import com.viw.common.exception.NoStockException;
import com.viw.common.to.SkuHasStockVo;
import com.viw.common.utils.R;
import com.viw.viwmall.ware.entity.WareOrderTaskDetailEntity;
import com.viw.viwmall.ware.entity.WareOrderTaskEntity;
import com.viw.viwmall.ware.feign.ProductFeignService;
import com.viw.viwmall.ware.vo.OrderItemVo;
import com.viw.viwmall.ware.vo.WareSkuLockVo;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viw.common.utils.PageUtils;
import com.viw.common.utils.Query;

import com.viw.viwmall.ware.dao.WareSkuDao;
import com.viw.viwmall.ware.entity.WareSkuEntity;
import com.viw.viwmall.ware.service.WareSkuService;
import org.springframework.transaction.annotation.Transactional;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {


    @Autowired
    WareSkuDao wareSkuDao;

    @Autowired
    ProductFeignService productFeignService;

    /**
     * 为某个订单锁定库存,里面某一个商品都需要锁库存
     * <p>
     * (rollbackFor = NoStockException.class)
     * 默认只要是运行时异常都会回滚
     *
     * @param vo 库存解锁的场景
     *           1）、下订单成功，订单过期没有支付被系统自动取消、被用户手动取消。都要解锁库存
     *           <p>
     *           <p>
     *           2）、下订单成功，库存锁定成功，接下来的业务调用失败，导致订单回滚。
     *           之前锁定的库存就要自动解锁。
     * @return
     */
    @Transactional
    @Override
    public Boolean orderLockStock(WareSkuLockVo vo) {

        /**
         * 保存库存工作单的详情。
         * 追溯。
         */
        WareOrderTaskEntity taskEntity = new WareOrderTaskEntity();
        taskEntity.setOrderSn(vo.getOrderSn());
        orderTaskService.save(taskEntity);


        //1、按照下单的收货地址，找到一个就近仓库，锁定库存。
        //1、找到每个商品在哪个仓库都有库存
        List<OrderItemVo> locks = vo.getLocks();

        List<SkuWareHasStock> collect = locks.stream().map(item -> {
            SkuWareHasStock stock = new SkuWareHasStock();
            Long skuId = item.getSkuId();
            stock.setSkuId(skuId);
            stock.setNum(item.getCount());
            //查询这个商品在哪里有库存
            List<Long> wareIds = wareSkuDao.listWareIdHasSkuStock(skuId);
            stock.setWareId(wareIds);
            return stock;
        }).collect(Collectors.toList());

        //2、锁定库存
        for (SkuWareHasStock hasStock : collect) {
            Boolean skuStocked = false; // 商品库存是否都锁定了
            Long skuId = hasStock.getSkuId();
            List<Long> wareIds = hasStock.getWareId();
            if (wareIds == null || wareIds.size() == 0) {
                //没有任何仓库有这个商品的库存
                throw new NoStockException(skuId);
            }
            //1、如果每一个商品都锁定成功，将当前商品锁定了几件的工作单记录发给MQ
            //2、锁定失败。前面保存的工作单信息就回滚了。发送出去的消息，即使要解锁记录，由于去数据库查不到id，所以就不用解锁
            //     1： 1 - 2 - 1   2：2-1-2  3：3-1-1(x)
            for (Long wareId : wareIds) {
                //成功就返回1,否则就是0
                Long count = wareSkuDao.lockSkuStock(skuId, wareId, hasStock.getNum()); //锁库存
                if (count == 1) {
                    skuStocked = true;
                    //TODO 告诉MQ库存锁定成功
                    WareOrderTaskDetailEntity entity = new WareOrderTaskDetailEntity(null, skuId, "", hasStock.getNum(), taskEntity.getId(), wareId, 1);
                    orderTaskDetailService.save(entity);
                    StockLockedTo lockedTo = new StockLockedTo();
                    lockedTo.setId(taskEntity.getId());
                    StockDetailTo stockDetailTo = new StockDetailTo();
                    BeanUtils.copyProperties(entity, stockDetailTo);
                    //只发id不行，防止回滚以后找不到数据
                    lockedTo.setDetail(stockDetailTo);
//                    rabbitTemplate
                    rabbitTemplate.convertAndSend("stock-event-exchange", "stock.locked", lockedTo);
                    break; // 当前sku锁上了库存，下一个商品继续锁库存
                } else {
                    //当前仓库锁失败，重试下一个仓库


                }
            }
            if (skuStocked == false) {
                //当前商品所有仓库都没有锁住
                throw new NoStockException(skuId);
            }
        }


        //3、肯定全部都是锁定成功过的

        return true;
    }


    @Override
    public List<SkuHasStockVo> getSkusHasStock(List<Long> skuIds) {
        List<SkuHasStockVo> collect = skuIds.stream().map(skuId -> {
            SkuHasStockVo vo = new SkuHasStockVo();

            //查询当前sku的总库存量
            //SELECT SUM(stock-stock_locked) FROM `wms_ware_sku` WHERE sku_id=1
            Long count = baseMapper.getSkuStock(skuId);

            vo.setSkuId(skuId);
            vo.setHasStock(count == null ? false : count > 0);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 入库操作
     * 根据商品id给某一个仓库增加库存
     *
     * @param skuId
     * @param wareId
     * @param skuNum
     */
    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        //1、判断如果还没有这个库存记录新增
        List<WareSkuEntity> entities = wareSkuDao.selectList(new QueryWrapper<WareSkuEntity>().eq("sku_id", skuId).eq("ware_id", wareId));
        if (entities == null || entities.size() == 0) {
            WareSkuEntity skuEntity = new WareSkuEntity();
            skuEntity.setSkuId(skuId);
            skuEntity.setStock(skuNum);
            skuEntity.setWareId(wareId);
            skuEntity.setStockLocked(0);
            //TODO 远程查询sku的名字，如果失败，整个事务无需回滚
            //1、自己catch异常
            //TODO 还可以用什么办法让异常出现以后不回滚？高级
            try {
                R info = productFeignService.info(skuId);
                Map<String, Object> data = (Map<String, Object>) info.get("skuInfo");

                if (info.getCode() == 0) {
                    skuEntity.setSkuName((String) data.get("skuName"));
                }
            } catch (Exception e) {

            }
            wareSkuDao.insert(skuEntity);
        } else {
            wareSkuDao.addStock(skuId, wareId, skuNum);
        }

    }


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        /**
         * skuId: 1
         * wareId: 2
         */
        QueryWrapper<WareSkuEntity> queryWrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if (!StringUtils.isEmpty(skuId)) {
            queryWrapper.eq("sku_id", skuId);
        }

        String wareId = (String) params.get("wareId");
        if (!StringUtils.isEmpty(wareId)) {
            queryWrapper.eq("ware_id", wareId);
        }


        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }


    @Data
    class SkuWareHasStock {
        private Long skuId;
        private Integer num;
        private List<Long> wareId;
    }

}
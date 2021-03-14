package com.viw.viwmall.seckill.service;

import com.viw.viwmall.seckill.to.SecKillSkuRedisTo;

import java.util.List;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/14 22:32
 * @description:
 */
public interface SeckillService {
    void uploadSeckillSkuLatest3Days();

    List<SecKillSkuRedisTo> getCurrentSeckillSkus();

    SecKillSkuRedisTo getSkuSeckillInfo(Long skuId);
}

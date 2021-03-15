package com.viw.viwmall.product.feign;

import com.viw.common.utils.R;
import com.viw.viwmall.product.feign.failback.SeckillFeignServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/7 17:52
 * @description:
 */
@FeignClient(value = "viwmall-seckill",fallback = SeckillFeignServiceFallBack.class) // fallback = SeckillFeignServiceFallBack.class失败的调用类的执行方法
public interface SeckillFeignService {

    @GetMapping("/sku/seckill/{skuId}")
    R getSkuSeckillInfo(@PathVariable("skuId") Long skuId);
}

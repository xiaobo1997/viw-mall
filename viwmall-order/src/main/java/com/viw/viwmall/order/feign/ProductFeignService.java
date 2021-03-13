package com.viw.viwmall.order.feign;

import com.viw.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/13 12:49
 * @description:
 */
@FeignClient("viwmall-product")
public interface ProductFeignService {

    /**
     * 通过sku id 获取 spu 信息
     * @param skuId
     * @return
     */
    @GetMapping("/product/spuinfo/skuId/{id}")
    R getSpuInfoBySkuId(@PathVariable("id") Long skuId);


}

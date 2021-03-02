package com.viw.viwmall.product.feign;


import com.viw.common.to.SkuHasStockVo;
import com.viw.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:htps://gitee.com/xiaobo97
 * @Date: 2021/3/3 0:18
 * @description:
 */
@FeignClient("viwmall-ware")
public interface WareFeignService {


    /**
     * 1、R设计的时候可以加上泛型
     * 2、直接返回我们想要的结果
     * 3、自己封装解析结果
     * @param skuIds
     * @return
     */
    @PostMapping("/ware/waresku/hasstock")
    R getSkusHasStock(@RequestBody List<Long> skuIds);
}


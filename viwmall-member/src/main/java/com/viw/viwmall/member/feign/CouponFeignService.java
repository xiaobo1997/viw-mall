package com.viw.viwmall.member.feign;

import com.viw.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xhb
 * @Date: 2021/2/7 22:55
 */
@FeignClient("viwmall-coupon")
public interface CouponFeignService {

    @RequestMapping("/coupon/smscoupon/member/list")
    public R couponsMember();

}

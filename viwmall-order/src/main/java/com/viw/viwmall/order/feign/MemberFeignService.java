package com.viw.viwmall.order.feign;

import com.viw.viwmall.order.vo.MemberAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/13 1:28
 * @description: 订单服务 调用 会员服务
 */

@FeignClient("viwmall-member")
public interface MemberFeignService {

    /**
     * 返回会员的所有收货地址列表
     * @param memberId
     * @return
     */
    @GetMapping("/member/memberreceiveaddress/{memeberId}/addresses")
    List<MemberAddressVo> getAddress(@PathVariable("memeberId") Long memberId);
}

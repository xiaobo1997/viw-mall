package com.viw.viwmall.ware.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/13 13:14
 * @description: 收货地址和运费VO
 */
@Data
public class FareVo {
    private MemberAddressVo address;
    private BigDecimal fare;
}

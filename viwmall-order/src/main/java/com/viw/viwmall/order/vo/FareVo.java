package com.viw.viwmall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/13 15:30
 * @description: 费用
 */
@Data
public class FareVo {
    private MemberAddressVo address;
    private BigDecimal fare;
}

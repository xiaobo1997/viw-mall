package com.viw.viwmall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/13 14:27
 * @description: 封装订单提交的数据
 */

@Data
public class OrderSubmitVo {
    private Long addrId;//收货地址的id
    private Integer payType;//支付方式
    /**
     *  无需提交需要购买的商品，去购物车再获取一遍
     */

    //优惠，发票
    private String orderToken;//防重令牌
    private BigDecimal payPrice;//应付价格  验价
    private String note;//订单备注
    //用户相关信息，直接去session取出登录的用户
}

package com.viw.viwmall.order.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/13 0:37
 * @description: 订单确认页 VO模型数据
 * 可以发现订单结算页，包含以下信息：
 * 1. 收货人信息：有更多地址，即有多个收货地址，其中有一个默认收货地址
 * 2. 支付方式：货到付款、在线支付，不需要后台提供
 * 3. 送货清单：配送方式及商品列表（根据购物车选中的 skuId 到数据库中查询）
 * 4. 优惠：查询用户领取的优惠券及可用积分
 */
@Data
public class OrderConfirmVo {

    //// 收货地址，ums_member_receive_address表
    @Setter
    @Getter
    List<MemberAddressVo> address;

    //所有选中的购物项
    @Setter @Getter
    List<OrderItemVo> items;

//发票记录....

    //优惠券信息...
    @Setter @Getter
    Integer integration;


    @Setter @Getter
    Map<Long,Boolean> stocks; // 是否有库存


    //防重令牌
    @Setter @Getter
    String orderToken;

    public Integer getCount(){
        Integer i = 0 ;
        if(items!=null){
            for (OrderItemVo item : items) {
                i+=item.getCount();
            }
        }
        return i;
    }


    //    BigDecimal total;//订单总额
    public BigDecimal getTotal() {
        BigDecimal sum = new BigDecimal("0");
        if(items!=null){
            for (OrderItemVo item : items) {
                BigDecimal multiply = item.getPrice().multiply(new BigDecimal(item.getCount().toString()));
                sum = sum.add(multiply);
            }
        }
        return sum;
    }

    //  //应付价格    BigDecimal payPrice;
    public BigDecimal getPayPrice() {
        return  getTotal();
    }


}

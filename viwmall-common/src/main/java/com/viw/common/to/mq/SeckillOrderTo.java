package com.viw.common.to.mq;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/14 10:57
 * @description: 秒杀 MQ  TO
 */
@Data
public class SeckillOrderTo {

    private String orderSn; //订单号
    private Long promotionSessionId;  //活动场次id
    private Long skuId;  //商品id
    private BigDecimal seckillPrice; //秒杀价格
    private Integer num; //购买数量
    private Long memberId;//会员id；

}

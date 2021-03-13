package com.viw.viwmall.order.vo;

import com.viw.viwmall.order.entity.OrderEntity;
import lombok.Data;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/13 14:37
 * @description: 下单状态返回数据VO
 */
@Data
public class SubmitOrderResponseVo {
    private OrderEntity order;
    private Integer code;//0成功   错误状态码
}

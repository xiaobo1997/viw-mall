package com.viw.viwmall.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/2/27 14:36
 * @description:
 */
@Data
public class MemberPrice {

    private Long id;
    private String name;
    private BigDecimal price;

}

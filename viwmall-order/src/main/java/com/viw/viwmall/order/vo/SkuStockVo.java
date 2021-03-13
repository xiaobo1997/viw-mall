package com.viw.viwmall.order.vo;

import lombok.Data;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/13 12:52
 * @description:  查询是否有库存VO
 */
@Data
public class SkuStockVo {
    private Long skuId;
    private Boolean hasStock;
}

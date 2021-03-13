package com.viw.viwmall.ware.vo;

import lombok.Data;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/13 19:20
 * @description:  库存 锁定 结果
 */
@Data
public class LockStockResult {

    private Long skuId;// 哪个商品
    private Integer num;//
    private Boolean locked;// 是否锁定成功
}

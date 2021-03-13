package com.viw.common.exception;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/13 14:40
 * @description: 库存不足 exception
 */
public class NoStockException extends RuntimeException {
    public NoStockException(Long skuId){
        super("商品id:"+skuId+"；没有足够的库存了");
    }

    public NoStockException(String message){
        super(message);
    }
}

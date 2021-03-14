package com.viw.common.to.mq;

import lombok.Data;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/14 10:56
 * @description: 库存服务-发送消息-锁库存 TO  服务之间传输
 */
@Data
public class StockLockedTo {

    private Long id; //库存工作单的id
    private StockDetailTo detail;//工作详情的所有id
}

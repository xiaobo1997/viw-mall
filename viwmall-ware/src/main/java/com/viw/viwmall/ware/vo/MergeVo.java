package com.viw.viwmall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/2/28 22:56
 * @description:
 */

@Data
public class MergeVo {
    private Long purchaseId; //整单id
    private List<Long> items;//[1,2,3,4] //合并项集合
}


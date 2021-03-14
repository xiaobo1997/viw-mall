package com.viw.viwmall.seckill.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/14 22:44
 * @description:
 */
@Data
public class SeckillSesssionsWithSkus {

    private Long id;
    /**
     * 场次名称
     */
    private String name;
    /**
     * 每日开始时间
     */
    private Date startTime;
    /**
     * 每日结束时间
     */
    private Date endTime;

    private Integer status;

    private Date createTime;


    private List<SeckillSkuVo> relationSkus;
}


package com.viw.viwmall.cart.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/10 0:57
 * @description:
 */
@ToString
@Data
public class UserInfoTo {

    private Long userId;
    private String userKey; //一定封装

    private boolean tempUser = false;
}

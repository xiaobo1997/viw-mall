package com.viw.viwmall.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/7 17:37
 * @description:
 */
@ToString
@Data
public class SpuItemAttrGroupVo {

    private String groupName;
    private List<Attr> attrs;
}

package com.viw.viwmall.product.vo;

import lombok.Data;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/2/27 9:58
 * @description: 属性分组给属性关联关系 VO
 */
@Data
public class AttrGroupRelationVo {

    //"attrId":1,"attrGroupId":2
    private Long attrId;
    private Long attrGroupId;
}

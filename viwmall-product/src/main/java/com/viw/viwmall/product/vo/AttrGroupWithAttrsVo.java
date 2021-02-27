package com.viw.viwmall.product.vo;

import com.viw.viwmall.product.entity.AttrEntity;
import lombok.Data;

import java.util.List;

/**
 *
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/2/27 13:13
 * @description: 新增商品-分类和分类下所有分组和属性VO
 */
@Data
public class AttrGroupWithAttrsVo {


    /**
     * "data": [{
     * 		"attrGroupId": 1,
     * 		"attrGroupName": "主体",
     * 		"sort": 0,
     * 		"descript": "主体",
     * 		"icon": "dd",
     * 		"catelogId": 225,
     * 		"attrs": [{
     * 			"attrId": 7,
     * 			"attrName": "入网型号",
     * 			"searchType": 1,
     * 			"valueType": 0,
     * 			"icon": "xxx",
     * 			"valueSelect": "aaa;bb",
     * 			"attrType": 1,
     * 			"enable": 1,
     * 			"catelogId": 225,
     * 			"showDesc": 1,
     * 			"attrGroupId": null
     *                        }, {
     * 			"attrId": 8,
     * 			"attrName": "上市年份",
     * 			"searchType": 0,
     * 			"valueType": 0,
     * 			"icon": "xxx",
     * 			"valueSelect": "2018;2019",
     * 			"attrType": 1,
     * 			"enable": 1,
     * 			"catelogId": 225,
     * 			"showDesc": 0,
     * 			"attrGroupId": null
     *            }]* 		},
     *        {
     * 		"attrGroupId": 2,
     * 		"attrGroupName": "基本信息",
     * 		"sort": 0,
     * 		"descript": "基本信息",
     * 		"icon": "xx",
     * 		"catelogId": 225,
     * 		"attrs": [{
     * 			"attrId": 11,
     * 			"attrName": "机身颜色",
     * 			"searchType": 0,
     * 			"valueType": 0,
     * 			"icon": "xxx",
     * 			"valueSelect": "黑色;白色",
     * 			"attrType": 1,
     * 			"enable": 1,
     * 			"catelogId": 225,
     * 			"showDesc": 1,
     * 			"attrGroupId": null
     *        }]
     *    }]
     */

    /**
     * 分组id
     */
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catelogId;

    private List<AttrEntity> attrs;
}


package com.viw.viwmall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.viw.viwmall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.viw.viwmall.product.entity.AttrEntity;
import com.viw.viwmall.product.service.AttrService;
import com.viw.common.utils.PageUtils;
import com.viw.common.utils.R;



/**
 * 商品属性
 *
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-07 17:01:09
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;


    /**
     *  查询 商品下的规格参数
     *  product/attr/sale/list/0?
     *     /product/attr/base/list/{catelogId}
     *           "attrId": 0, //属性id
     * 			"attrName": "string", //属性名
     * 			"attrType": 0, //属性类型，0-销售属性，1-基本属性
     * 			"catelogName": "手机/数码/手机", //所属分类名字
     * 			"groupName": "主体", //所属分组名字
     * 			"enable": 0, //是否启用
     * 			"icon": "string", //图标
     * 			"searchType": 0,//是否需要检索[0-不需要，1-需要]
     * 			"showDesc": 0,//是否展示在介绍上；0-否 1-是
     * 			"valueSelect": "string",//可选值列表[用逗号分隔]
     * 			"valueType": 0//值类型[0-为单个值，1-可以选择多个值]
     * @param params
     * @param catelogId
     * @param type
     * @return
     */
//    @GetMapping("/{attrType}/list/{catelogId}")
    @GetMapping("/base/list/{catelogId}")
    public R baseAttrList(@RequestParam Map<String, Object> params,
                          @PathVariable("catelogId") Long catelogId ,
                          @PathVariable("attrType")String type){

        PageUtils page = attrService.queryBaseAttrPage(params,catelogId,type);
        return R.ok().put("page", page); // 分页信息
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId){
		AttrEntity attr = attrService.getById(attrId);

        return R.ok().put("attr", attr);
    }


    /**
     * 保存
     * @param attr 规格属性VO
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr){
		attrService.saveAttrVo(attr);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrEntity attr){
		attrService.updateById(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}

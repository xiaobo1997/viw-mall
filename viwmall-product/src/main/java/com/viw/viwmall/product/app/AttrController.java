package com.viw.viwmall.product.app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.viw.viwmall.product.entity.ProductAttrValueEntity;
import com.viw.viwmall.product.service.ProductAttrValueService;
import com.viw.viwmall.product.vo.AttrRespVo;
import com.viw.viwmall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    ProductAttrValueService productAttrValueService;

    /**
     * 修改商品规格
     * @param spuId
     * @param entities
     * @return
     */
    ///product/attr/update/{spuId}
    @PostMapping("/update/{spuId}")
    public R updateSpuAttr(@PathVariable("spuId") Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entities){

        productAttrValueService.updateSpuAttr(spuId,entities);

        return R.ok();
    }

    /**
     * 查询spu所有规格参数
     * @param spuId
     * @return
     */
    // /product/attr/base/listforspu/{spuId}
    @GetMapping("/base/listforspu/{spuId}")
    public R baseAttrlistforspu(@PathVariable("spuId") Long spuId){

        List<ProductAttrValueEntity> entities = productAttrValueService.baseAttrlistforspu(spuId);

        return R.ok().put("data",entities);
    }



//    "attrId": 4,
//            "attrName": "aad",
//            "searchType": 1,
//            "valueType": 1,
//            "icon": "qq",
//            "valueSelect": "v;q;w",
//            "attrType": 1,
//            "enable": 1,
//            "showDesc": 1,
//            "attrGroupId": 1, //分组id
//            "catelogId": 225, //分类id
//            "catelogPath": [2, 34, 225] //分类完整路径
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }

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
//    @GetMapping("/base/list/{catelogId}")  基本属性  base/xxx
    // 销售属性   发  sale/xxx 请求
    @GetMapping("/{attrType}/list/{catelogId}") //
    public R baseAttrList(@RequestParam Map<String, Object> params,
                          @PathVariable("catelogId") Long catelogId ,
                          @PathVariable("attrType")String type){
        PageUtils page = attrService.queryBaseAttrPage(params,catelogId,type);
// 分页信息
        return R.ok().put("page", page);
    }


    /**
     * 获取属性信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId){
        //AttrEntity attr = attrService.getById(attrId);
        AttrRespVo respVo = attrService.getAttrInfo(attrId);

        return R.ok().put("attr", respVo);
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
     * 修改规格属性
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attr){
        attrService.updateAttr(attr);
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


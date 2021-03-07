package com.viw.viwmall.product.vo;

import com.viw.viwmall.product.entity.SkuImagesEntity;
import com.viw.viwmall.product.entity.SkuInfoEntity;
import com.viw.viwmall.product.entity.SpuInfoDescEntity;
import lombok.Data;

import java.util.List;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/7 17:35
 * @description: 商品详情VO
 */

@Data
public class SkuItemVo {
    //1、sku基本信息获取  pms_sku_info
    SkuInfoEntity info;

    boolean hasStock = true; // 默认有货
    //2、sku的图片信息  pms_sku_images
    List<SkuImagesEntity> images;

    //3、获取spu的销售属性组合。
    List<SkuItemSaleAttrVo> saleAttr;

    //4、获取spu的介绍
    SpuInfoDescEntity desp;

    //5、获取spu的规格参数信息。
    List<SpuItemAttrGroupVo> groupAttrs;

    SeckillInfoVo seckillInfo;//当前商品的秒杀优惠信息

}

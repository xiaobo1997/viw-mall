package com.viw.viwmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viw.common.utils.PageUtils;
import com.viw.viwmall.product.entity.AttrEntity;
import com.viw.viwmall.product.vo.AttrVo;

import java.util.Map;

/**
 * 商品属性
 *
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-07 17:01:09
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttrVo(AttrVo attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);
}


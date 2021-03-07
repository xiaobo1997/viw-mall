package com.viw.viwmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viw.common.utils.PageUtils;
import com.viw.viwmall.product.entity.BrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌
 *
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-07 17:01:09
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateDetail(BrandEntity brand);

    List<BrandEntity> getBrandsByIds(List<Long> brandIds);
}


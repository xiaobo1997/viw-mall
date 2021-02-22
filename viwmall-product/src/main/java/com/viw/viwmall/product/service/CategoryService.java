package com.viw.viwmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viw.common.utils.PageUtils;
import com.viw.viwmall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-07 17:01:09
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeMenuByIds(List<Long> asList);
}


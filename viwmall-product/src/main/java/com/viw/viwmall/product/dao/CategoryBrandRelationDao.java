package com.viw.viwmall.product.dao;

import com.viw.viwmall.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌分类关联
 * 
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-07 17:01:08
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {
	
}
package com.viw.viwmall.product.service.impl;

import com.viw.viwmall.product.service.CategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viw.common.utils.PageUtils;
import com.viw.common.utils.Query;

import com.viw.viwmall.product.dao.BrandDao;
import com.viw.viwmall.product.entity.BrandEntity;
import com.viw.viwmall.product.service.BrandService;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author xhb
 */
@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {


    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public List<BrandEntity> getBrandsByIds(List<Long> brandIds) {


        return baseMapper.selectList(new QueryWrapper<BrandEntity>().in("brand_id",brandIds));
    }

    /**
     * 默认自带分页工具类，不带任何查询条件
     * 修改带查询条件
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //前端传的查询关键字
        String key = (String) params.get("key");
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        if (!Strings.isNotEmpty(key)) {
            queryWrapper.eq("brand_id",key).or().like("name",key);
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),queryWrapper
        );

        return new PageUtils(page);
    }

    /**
     * 保证修改的数据 在不同表中一致
     * @param brand 品牌
     */
    @Transactional
    @Override
    public void updateDetail(BrandEntity brand) {
        //保证冗余字段的数据一致
        this.updateById(brand);
        if(!StringUtils.isEmpty(brand.getName())){
            //同步更新其他关联表中的数据
            categoryBrandRelationService.updateBrand(brand.getBrandId(),brand.getName());

            //TODO 更新其他关联
        }
    }


}
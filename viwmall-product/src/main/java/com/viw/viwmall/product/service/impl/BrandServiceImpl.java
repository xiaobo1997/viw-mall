package com.viw.viwmall.product.service.impl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viw.common.utils.PageUtils;
import com.viw.common.utils.Query;

import com.viw.viwmall.product.dao.BrandDao;
import com.viw.viwmall.product.entity.BrandEntity;
import com.viw.viwmall.product.service.BrandService;


/**
 * @author xhb
 */
@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

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

}
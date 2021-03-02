package com.viw.viwmall.product.dao;

import com.viw.viwmall.product.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * spu信息
 * 
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-07 17:01:08
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {


    void updateSpuStatus(@Param("spuId") Long spuId, @Param("code") int code);


}

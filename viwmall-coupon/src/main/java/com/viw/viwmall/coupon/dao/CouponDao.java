package com.viw.viwmall.coupon.dao;

import com.viw.viwmall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-08 13:04:54
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}

package com.viw.viwmall.order.dao;

import com.viw.viwmall.order.entity.OrderSettingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单配置信息
 * 
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-08 13:13:32
 */
@Mapper
public interface OrderSettingDao extends BaseMapper<OrderSettingEntity> {
	
}

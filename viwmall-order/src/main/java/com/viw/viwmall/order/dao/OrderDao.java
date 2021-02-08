package com.viw.viwmall.order.dao;

import com.viw.viwmall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-08 13:13:32
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}

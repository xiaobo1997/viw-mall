package com.viw.viwmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viw.common.utils.PageUtils;
import com.viw.viwmall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-08 13:13:32
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


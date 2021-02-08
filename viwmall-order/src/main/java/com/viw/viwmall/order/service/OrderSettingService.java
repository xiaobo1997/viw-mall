package com.viw.viwmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viw.common.utils.PageUtils;
import com.viw.viwmall.order.entity.OrderSettingEntity;

import java.util.Map;

/**
 * 订单配置信息
 *
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-08 13:13:32
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


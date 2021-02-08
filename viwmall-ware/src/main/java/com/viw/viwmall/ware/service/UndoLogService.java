package com.viw.viwmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viw.common.utils.PageUtils;
import com.viw.viwmall.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-08 13:15:36
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


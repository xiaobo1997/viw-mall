package com.viw.viwmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viw.common.utils.PageUtils;
import com.viw.viwmall.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * 会员等级
 *
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-08 13:09:48
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


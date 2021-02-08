package com.viw.viwmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viw.common.utils.PageUtils;
import com.viw.viwmall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-08 13:09:48
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


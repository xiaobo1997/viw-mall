package com.viw.viwmall.member.dao;

import com.viw.viwmall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author xiaobo
 * @email xiaobo97@163.com
 * @date 2021-02-08 13:09:48
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}

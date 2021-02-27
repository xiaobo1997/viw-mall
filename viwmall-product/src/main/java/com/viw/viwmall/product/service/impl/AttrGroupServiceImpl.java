package com.viw.viwmall.product.service.impl;

import com.viw.viwmall.product.entity.AttrEntity;
import com.viw.viwmall.product.service.AttrService;
import com.viw.viwmall.product.vo.AttrGroupWithAttrsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viw.common.utils.PageUtils;
import com.viw.common.utils.Query;

import com.viw.viwmall.product.dao.AttrGroupDao;
import com.viw.viwmall.product.entity.AttrGroupEntity;
import com.viw.viwmall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {


    @Autowired
    AttrService attrService;

    /**
     * "data": [{
     * 		"attrGroupId": 1,
     * 		"attrGroupName": "主体",
     * 		"sort": 0,
     * 		"descript": "主体",
     * 		"icon": "dd",
     * 		"catelogId": 225,
     * 		"attrs": [{
     * 			"attrId": 7,
     * 			"attrName": "入网型号",
     * 			"searchType": 1,
     * 			"valueType": 0,
     * 			"icon": "xxx",
     * 			"valueSelect": "aaa;bb",
     * 			"attrType": 1,
     * 			"enable": 1,
     * 			"catelogId": 225,
     * 			"showDesc": 1,
     * 			"attrGroupId": null
     *                        }, {
     * 			"attrId": 8,
     * 			"attrName": "上市年份",
     * 			"searchType": 0,
     * 			"valueType": 0,
     * 			"icon": "xxx",
     * 			"valueSelect": "2018;2019",
     * 			"attrType": 1,
     * 			"enable": 1,
     * 			"catelogId": 225,
     * 			"showDesc": 0,
     * 			"attrGroupId": null
     *            }]* 		},
     *        {
     * 		"attrGroupId": 2,
     * 		"attrGroupName": "基本信息",
     * 		"sort": 0,
     * 		"descript": "基本信息",
     * 		"icon": "xx",
     * 		"catelogId": 225,
     * 		"attrs": [{
     * 			"attrId": 11,
     * 			"attrName": "机身颜色",
     * 			"searchType": 0,
     * 			"valueType": 0,
     * 			"icon": "xxx",
     * 			"valueSelect": "黑色;白色",
     * 			"attrType": 1,
     * 			"enable": 1,
     * 			"catelogId": 225,
     * 			"showDesc": 1,
     * 			"attrGroupId": null
     *        }]
     *    }]
     * 根据分类id查出所有的分组以及这些组里面的属性
     * @param catelogId
     * @return
     */
    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        //com.atguigu.gulimall.product.vo
        //1、查询分组信息
        List<AttrGroupEntity> attrGroupEntities = this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        //2、查询所有属性
        List<AttrGroupWithAttrsVo> collect = attrGroupEntities.stream().map(group -> {
            AttrGroupWithAttrsVo attrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(group,attrsVo);
            List<AttrEntity> attrs = attrService.getRelationAttr(attrsVo.getAttrGroupId());
            attrsVo.setAttrs(attrs);
            return attrsVo;
        }).collect(Collectors.toList());

        return collect;


    }


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }


    /**
     * 带三级分类id的分页查询
     * @param params
     * @param catelogId 三级分类查询id
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        String key = (String) params.get("key");
        //select * from pms_attr_group where catelog_id=? and (attr_group_id=key or attr_group_name like %key%)
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>();
        if(!StringUtils.isEmpty(key)){
            wrapper.and((obj)->{
                obj.eq("attr_group_id",key).or().like("attr_group_name",key);
            });
        }
        // 0 查询所有  否则查询指定
        if( catelogId == 0){
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    wrapper);
            return new PageUtils(page);
        }else {
            //有分类id 拼接条件
            wrapper.eq("catelog_id",catelogId);
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    wrapper);
            return new PageUtils(page);
        }

    }

}
package com.viw.viwmall.product.service.impl;

import com.viw.common.constant.ProductConstant;
import com.viw.viwmall.product.dao.AttrAttrgroupRelationDao;
import com.viw.viwmall.product.dao.AttrGroupDao;
import com.viw.viwmall.product.dao.CategoryDao;
import com.viw.viwmall.product.entity.AttrAttrgroupRelationEntity;
import com.viw.viwmall.product.entity.AttrGroupEntity;
import com.viw.viwmall.product.entity.CategoryEntity;
import com.viw.viwmall.product.service.CategoryService;
import com.viw.viwmall.product.vo.AttrRespVo;
import com.viw.viwmall.product.vo.AttrVo;
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

import com.viw.viwmall.product.dao.AttrDao;
import com.viw.viwmall.product.entity.AttrEntity;
import com.viw.viwmall.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    AttrAttrgroupRelationDao relationDao;

    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryService categoryService;

    /**
     * 分页查询 商品 属性规格 如 手机下面有什么属性
     * @param params
     * @param catelogId
     * @param type
     * @return
     */
    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type) {

        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>().eq("attr_type","base".equalsIgnoreCase(type)?ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode():ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());

        // catelogId == 0 是查询所有
        if(catelogId != 0){
            queryWrapper.eq("catelog_id",catelogId);
        }
        // 检索条件
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            //attr_id  分类id attr_name 分类名称
            queryWrapper.and((wrapper)->{
                wrapper.eq("attr_id",key).or().like("attr_name",key);
            });
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );

        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();
        // 封装响应结果 通过stream映射
        List<AttrRespVo> respVos = records.stream().map((attrEntity) -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrRespVo);

            //1、设置分类和分组的名字
            if("base".equalsIgnoreCase(type)){
                AttrAttrgroupRelationEntity attrId = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
                if (attrId != null && attrId.getAttrGroupId()!=null) {
                    AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrId.getAttrGroupId());
                    attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }

            }

            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            if (categoryEntity != null) {
                attrRespVo.setCatelogName(categoryEntity.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());

        pageUtils.setList(respVos);
        return pageUtils;
    }

    /**
     * 保存 属性和属性分组关系
     * pms_attr pms_attr_group
     * @param attr 属性规格VO
     */
    @Transactional
    @Override
    public void saveAttrVo(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
//        attrEntity.setAttrName(attr.getAttrName());
        // 复制属性 封装对象 前提VO和PO 属性一致
        BeanUtils.copyProperties(attr,attrEntity);
        //1、保存基本数据
        this.save(attrEntity);
        //2、保存关联关系
        if(attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() && attr.getAttrGroupId()!=null){
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attrEntity.getAttrId());
            relationDao.insert(relationEntity);
        }


    }


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }



}
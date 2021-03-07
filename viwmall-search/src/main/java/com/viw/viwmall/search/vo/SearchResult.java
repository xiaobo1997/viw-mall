package com.viw.viwmall.search.vo;

import com.viw.common.to.es.SkuEsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/6 20:48
 * @description: 检索商品信息的结果vo模型抽取
 */
@Data
public class SearchResult {

    //查询到的所有商品信息
    private List<SkuEsModel> products;

    /**
     * 以下是分页信息
     */
    private Integer pageNum;//当前页码
    private Long total;//总记录数
    private Integer totalPages;//总页码
    private List<Integer> pageNavs;

    private List<BrandVo> brands;//当前查询到的结果，所有涉及到的品牌  比如搜手机，显示苹果，小米等
    private List<CatalogVo> catalogs;//当前查询到的结果，所有涉及到的所有分类 比如搜手机，二手，合约机等
    private List<AttrVo> attrs;//当前查询到的结果，所有涉及到的所有属性 ，如 手机 的 分辨率，内存等

    //==========以上是返回给页面的所有信息============

    //面包屑导航数据
    private List<NavVo> navs = new ArrayList<>();
    private List<Long> attrIds = new ArrayList<>();


    @Data
    public static class NavVo{
        private String navName;
        private String navValue;
        private String link;
    }



    //用户首页搜索的品牌信息
    @Data
    public static class BrandVo{
        private Long brandId;
        private String brandName;

        private String brandImg;
    }

    @Data
    public static class CatalogVo{
        private Long catalogId;
        private String catalogName;

    }

    // 搜索时的属性
    @Data
    public static class AttrVo{
        private Long attrId;
        private String attrName;

        private List<String> attrValue;
    }
}

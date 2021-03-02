package com.viw.viwmall.search.service;

import com.viw.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/3 0:44
 * @description:
 */
public interface ProductSaveService {
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;

}

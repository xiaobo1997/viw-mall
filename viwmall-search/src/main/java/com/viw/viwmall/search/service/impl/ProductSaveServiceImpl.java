package com.viw.viwmall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.viw.common.to.es.SkuEsModel;
import com.viw.viwmall.search.config.ViwmallElasticSearchConfig;
import com.viw.viwmall.search.constant.EsConstant;
import com.viw.viwmall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/3 0:45
 * @description:
 */

@Slf4j
@Service
public class ProductSaveServiceImpl  implements ProductSaveService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {

        //保存到es
        //1、给es中建立索引。product，建立好映射关系。
        //2、给es中保存这些数据
        //BulkRequest bulkRequest, RequestOptions options
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel model : skuEsModels) {
            //1、构造保存请求
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(model.getSkuId().toString());
            String s = JSON.toJSONString(model);
            indexRequest.source(s, XContentType.JSON);

            bulkRequest.add(indexRequest);
        }
        //使用Bulk API执行批量请求。 参见elastic.co上的批量API
      BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, ViwmallElasticSearchConfig.COMMON_OPTIONS);

        //TODO 1、如果批量错误
        boolean b = bulk.hasFailures();// 执行中是否有任何失败。
        List<String> collect = Arrays.stream(bulk.getItems()).map(item -> {
            return item.getId();
        }).collect(Collectors.toList());
        log.info("商品上架完成：{}，返回数据：{}",collect,bulk.toString());
        return b;

    }
}
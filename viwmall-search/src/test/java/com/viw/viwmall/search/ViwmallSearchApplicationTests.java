package com.viw.viwmall.search;


import com.alibaba.fastjson.JSON;
import com.viw.viwmall.search.config.ViwmallElasticSearchConfig;
import lombok.Data;
import lombok.ToString;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ViwmallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;


    @Test
    public void contextLoads() {
        System.out.println(client);
    }


    /**
     * （1）、方便检索{
     *      skuId:1
     *      spuId:11
     *      skuTitle:华为xx
     *      price:998
     *      saleCount:99
     *      attrs:[
     *          {尺寸：5寸},
     *          {CPU：高通945},
     *          {分辨率：全高清}
     *      ]
     *  }
     * 冗余：
     *  100万*20=1000000*2KB=2000MB=2G 20
     * （2）、分别创建索引
     *    sku索引{
     *     skuId:1
     *     spuId:11
     *     xxxxx
     *    }
     *
     *    attr索引{
     *        spuId:11,
     *        attrs:[
     *              {尺寸：5寸},
     *              {CPU：高通945},
     *              {分辨率：全高清}
     *      ]
     *    }
     *
     *   搜索 小米； 粮食，手机，电器。
     *   10000个，4000个spu
     *   分步，4000个spu对应的所有可能属性；
     *   esClient： spuId:[4000个spuid] 4000*8=32000byte=32kb
     *
     *   32kb*10000=32000mb;=32GB
     *
     *
     * @throws IOException
     */


    /**
     * 复杂查询
     * https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302224432024.png
     * @throws IOException
     */
    @Test
    public void searchData() throws IOException {
        //1、创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        //指定索引名称   bank下
        searchRequest.indices("bank");
        //指定DSL，检索条件
        //SearchSourceBuilder sourceBuilde 封装的条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //1.1）、构造检索条件
//        sourceBuilder.query();
//        sourceBuilder.from();
//        sourceBuilder.size();
//        sourceBuilder.aggregation()
        sourceBuilder.query(QueryBuilders.matchQuery("address","mill"));

        //1.2）、按照年龄的值分布进行聚合
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        sourceBuilder.aggregation(ageAgg);

        //1.3）、计算平均薪资
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        sourceBuilder.aggregation(balanceAvg);

        System.out.println("检索条件"+sourceBuilder.toString());
        searchRequest.source(sourceBuilder);


        //2、执行检索；
        SearchResponse searchResponse = client.search(searchRequest, ViwmallElasticSearchConfig.COMMON_OPTIONS);

        //3、分析结果 searchResponse
        System.out.println(searchResponse.toString());
//        Map map = JSON.parseObject(searchResponse.toString(), Map.class);
        //3.1）、获取所有查到的数据  命中的数据记录
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        // 获取每一条数据
        for (SearchHit hit : searchHits) {
            /**
             * "_index": "bank",
             * 			"_type": "account",
             * 			"_id": "345",
             * 			"_score": 5.4032025,
             * 			"_source":
             */
//            hit.getIndex();hit.getType();hit.getId();
            String string = hit.getSourceAsString();
            Accout accout = JSON.parseObject(string, Accout.class);
            System.out.println("accout："+accout);
        }

        //3.2）、获取这次检索到的分析信息；
        Aggregations aggregations = searchResponse.getAggregations();
//        for (Aggregation aggregation : aggregations.asList()) {
//            System.out.println("当前聚合："+aggregation.getName());
////            aggregation.get
//
//        }
        Terms ageAgg1 = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : ageAgg1.getBuckets()) {
            String keyAsString = bucket.getKeyAsString();
            System.out.println("年龄："+keyAsString+"==>"+bucket.getDocCount());
        }

        Avg balanceAvg1 = aggregations.get("balanceAvg");
        System.out.println("平均薪资："+balanceAvg1.getValue());

//        Aggregation balanceAvg2 = aggregations.get("balanceAvg");


    }

    @ToString
    @Data
    static class  Accout {

        private int account_number;
        private int balance;
        private String firstname;
        private String lastname;
        private int age;
        private String gender;
        private String address;
        private String employer;
        private String email;
        private String city;
        private String state;

    }


    /**
     * 测试存储数据
     * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.7/java-rest-high-create-index.html
     */
    @Test
    public void indexDate() throws Exception{
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");//数据的id
//        indexRequest.source("userName","zhangsan","age",18,"gender","男");
        User user = new User();
        user.setUserName("zhangsan");
        user.setAge(18);
        user.setGender("男");
        String jsonString = JSON.toJSONString(user);
        indexRequest.source(jsonString, XContentType.JSON);//要保存的内容
        //执行操作
        IndexResponse index = client.index(indexRequest, ViwmallElasticSearchConfig.COMMON_OPTIONS);

        //提取有用的响应数据
        System.out.println(index);
        //IndexResponse[index=users,type=_doc,id=1,version=1,result=created,seqNo=0,primaryTerm=1,shards={"total":2,"successful":1,"failed":0}]
    }

    @Data
    class User{
        private String userName;
        private String gender;
        private Integer age;

    }

}

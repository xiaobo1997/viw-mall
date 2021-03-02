package com.viw.viwmall.search;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public class ViwmallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;


    @Test
    void contextLoads() {
        System.out.println(client);
    }

}

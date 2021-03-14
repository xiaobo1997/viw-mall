package com.viw.viwmall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.viw.viwmall.ware.dao")
@EnableRabbit
public class ViwmallWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViwmallWareApplication.class, args);
    }

}

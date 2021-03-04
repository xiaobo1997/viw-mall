package com.viw.viwmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@MapperScan("com.viw.viwmall.product.dao")
@SpringBootApplication
@EnableDiscoveryClient  //开启nacos注册中心
@EnableFeignClients(basePackages = "com.viw.viwmall.product.feign")
public class ViwmallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViwmallProductApplication.class, args);
    }

}

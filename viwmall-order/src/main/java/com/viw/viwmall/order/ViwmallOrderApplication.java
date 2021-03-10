package com.viw.viwmall.order;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableRabbit
@EnableDiscoveryClient
@EnableFeignClients
public class ViwmallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViwmallOrderApplication.class, args);
    }

}

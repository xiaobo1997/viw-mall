package com.viw.viwmall.order;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableAspectJAutoProxy(exposeProxy = true) //使用aop代理
@SpringBootApplication
@EnableRabbit
@EnableDiscoveryClient
@EnableFeignClients
@EnableRedisHttpSession
public class ViwmallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViwmallOrderApplication.class, args);
    }

}

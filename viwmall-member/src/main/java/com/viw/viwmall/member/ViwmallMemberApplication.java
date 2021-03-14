package com.viw.viwmall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableDiscoveryClient
@EnableRedisHttpSession
@EnableFeignClients(basePackages = {"com.viw.viwmall.member.feign"})
@ComponentScan(basePackages = {"com.viw"})
@SpringBootApplication
public class ViwmallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViwmallMemberApplication.class, args);
    }

}

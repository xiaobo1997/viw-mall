package com.viw.viwmall.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ViwmallThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViwmallThirdPartyApplication.class, args);
    }

}

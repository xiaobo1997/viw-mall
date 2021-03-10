package com.viw.viwmall.order;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class ViwmallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViwmallOrderApplication.class, args);
    }

}

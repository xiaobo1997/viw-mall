package com.viw.viwmall.order.controller;

import com.viw.viwmall.order.entity.OrderEntity;
import com.viw.viwmall.order.entity.OrderReturnReasonEntity;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/11 0:26
 * @description:
 */
public class RabbitController {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @GetMapping("/sendMq")
    public String sendMq(@RequestParam(value = "num",defaultValue = "10") Integer num){
        for (int i=0;i<num;i++){
            if(i%2 == 0){
                OrderReturnReasonEntity reasonEntity = new OrderReturnReasonEntity();
                reasonEntity.setId(1L);
                reasonEntity.setCreateTime(new Date());
                reasonEntity.setName("哈哈-"+i);
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", reasonEntity,new CorrelationData(UUID.randomUUID().toString()));
            }else {
                OrderEntity entity = new OrderEntity();
                entity.setOrderSn(UUID.randomUUID().toString());
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello22.java", entity,new CorrelationData(UUID.randomUUID().toString()));
            }
        }

        return "ok";
    }
}

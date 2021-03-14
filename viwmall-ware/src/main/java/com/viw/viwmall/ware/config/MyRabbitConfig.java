package com.viw.viwmall.ware.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/14 10:30
 * @description: MQ配置
 * 1，以JSON传输消息，指定序列化和反序列化
 * 2.延时队列  一个交换机 两个队列  两个bind
 */
@Configuration
public class MyRabbitConfig {


    /**
     * 使用JSON序列化机制，进行消息转换
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }



//    @RabbitListener(queues = "stock.release.stock.queue")
//    public void  handle(Message message){
//
//    }

    /**
     * 库存服务交换机 直接@Bean
     * @return
     */
    @Bean
    public Exchange stockEventExchange(){
        //String name, boolean durable, boolean autoDelete, Map<String, Object> arguments
        return  new TopicExchange("stock-event-exchange",true,false);
    }

    @Bean
    public Queue stockReleaseStockQueue(){
        //String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        return new Queue("stock.release.stock.queue",true,false,false);
    }

    /**
     * 死消队列
     * @return
     */
    @Bean
    public Queue stockDelayQueue(){
        /**
         * x-dead-letter-exchange: stock-event-exchange   死信路由
         * x-dead-letter-routing-key: order.release.order 死信路由key
         * x-message-ttl: 60000                             死信 TTL
         */
        Map<String,Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange","stock-event-exchange");
        args.put("x-dead-letter-routing-key","stock.release");
        args.put("x-message-ttl",120000);
        return new Queue("stock.delay.queue",true,false,false,args);
    }

    @Bean
    public Binding stockReleaseBinding(){
        /**
         * String destination, DestinationType destinationType, String exchange, String routingKey,
         * 			Map<String, Object> arguments
         */
        return  new Binding("stock.release.stock.queue",
                Binding.DestinationType.QUEUE,
                "stock-event-exchange",
                "stock.release.#",
                null);
    }

    @Bean
    public Binding stockLockedBinding(){
        /**
         * String destination, DestinationType destinationType, String exchange, String routingKey,
         * 			Map<String, Object> arguments
         */
        return  new Binding("stock.delay.queue",
                Binding.DestinationType.QUEUE,
                "stock-event-exchange",
                "stock.locked",
                null);
    }

}

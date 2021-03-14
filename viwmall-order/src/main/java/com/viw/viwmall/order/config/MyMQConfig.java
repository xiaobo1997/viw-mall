package com.viw.viwmall.order.config;

import com.viw.viwmall.order.entity.OrderEntity;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/14 2:38
 * @description: MQ配置  直接@Bean给容器中添加组件
 * MQ 延时队列
 * 流程图 ：https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314023615468.png
 * 两个队列  两个 bind  一个交换机
 */
@Configuration
public class MyMQConfig {


    @RabbitListener
    public void listenerOrder(OrderEntity entity, Channel channel, Message message){
        System.out.println("收到过期的消息，准备关闭订单："+entity);
    }

    /**
     //@Bean Binding，Queue，Exchange
     * 容器中的 Binding，Queue，Exchange 都会自动创建（RabbitMQ没有的情况）
     * RabbitMQ 只要有队列 重复启动。@Bean声明属性发生变化也不会覆盖
     * @return
     *
     * 死信队列
     */
    @Bean
    public Queue orderDelayQueue() {
        Map<String,Object> arguments = new HashMap<>();
        /**
         * 队列A的参数
         * x-dead-letter-exchange: order-event-exchange
         * x-dead-letter-routing-key: order.release.order
         * x-message-ttl: 60000
         */
        arguments.put("x-dead-letter-exchange","order-event-exchange");
        arguments.put("x-dead-letter-routing-key","order.release.order");
        arguments.put("x-message-ttl",60000);
        //String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        Queue queue = new Queue("order.delay.queue", true, false, false,arguments);
        return queue;
    }

    /**
     * 延时队列  普通队列B
     * @return
     */
    @Bean
    public Queue orderReleaseOrderQueue() {
        Queue queue = new Queue("order.release.order.queue", true, false, false);
        return queue;
    }

    /**
     * 延时交换机
     * @return Topic类型的交换机
     */
    @Bean
    public Exchange orderEventExchange() {
        //String name, boolean durable, boolean autoDelete, Map<String, Object> arguments
        return new TopicExchange("order-event-exchange",true,false);
    }

    /**
     * 延时队列中的  order.delay.queue 和 order-event-exchange 的 bind
     * @return
     */
    @Bean
    public Binding orderCreateOrderBingding() {
        //String destination, DestinationType destinationType, String exchange, String routingKey,
        //			Map<String, Object> arguments
        return new Binding("order.delay.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.create.order",
                null);
    }

    /**
     * 延时队列中的  order.release.order.queue 和 order-event-exchange 的 bind
     * @return
     */
    @Bean
    public Binding orderReleaseOrderBingding() {
        return new Binding("order.release.order.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.release.order",
                null);
    }




}

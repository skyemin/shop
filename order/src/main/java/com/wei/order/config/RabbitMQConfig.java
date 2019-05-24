package com.wei.order.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author weizz
 * @version 1.0
 * @create 2019-0-22 10:42
 **/
@Configuration
public class RabbitMQConfig {

    // 用户服务交换机
    public final static String USER_EXCHANGE = "order_exchange";
    // 队列名称
    public final static String USER_QUEUE = "order_queue";
    // 绑定的值
    public static final String USER_BIND_KEY = "order_bind_key";


    //  在RabbitMQ上创建queue,exchange,binding 方法一：通过@Bean实现 begin ===
    /**
     * 定义队列：
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue(USER_QUEUE, false);
    }
    /**
     * 定义交换机
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(USER_EXCHANGE);
    }

    /**
     * 定义绑定
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(USER_BIND_KEY );
    }

    /*
     * 定义消息转换实例
     * @return
     */
    @Bean
    MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}

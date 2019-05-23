package com.wei.user.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
    public final static String USER_EXCHANGE = "user_exchange";
    // 队列名称
    public final static String USER_QUEUE = "user_queue";
    // 绑定的值
    public static final String USER_BIND_KEY = "user_bind_key";

    // 队列名称
    public final static String USER_QUEUE1 = "user_queue1";
    // 绑定的值
    public static final String USER_BIND_KEY1 = "user_bind_key1";
    //  在RabbitMQ上创建queue,exchange,binding 方法一：通过@Bean实现 begin ===
    /**
     * 定义队列：
     * @return
     */
    @Bean("user_queue")
    Queue queue() {
        return new Queue(USER_QUEUE, false);
    }
    @Bean("user_queue1")
    Queue queue1() {
        return new Queue(USER_QUEUE1, false);
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
    Binding binding(@Qualifier("user_queue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(USER_BIND_KEY );
    }

    @Bean
    Binding binding1(@Qualifier("user_queue1")Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(USER_BIND_KEY1 );
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

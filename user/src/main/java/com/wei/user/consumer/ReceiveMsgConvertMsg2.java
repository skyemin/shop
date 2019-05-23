package com.wei.user.consumer;

import com.rabbitmq.client.Channel;
import com.wei.user.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
// @RabbitListener除了可以作用在方法，也可以作用在类上。在后者的情况下，需要在处理的方法使用@RabbitHandler。一个类可以配置多个@RabbitHandler
@RabbitListener(queues = RabbitMQConfig.USER_QUEUE1)
public class ReceiveMsgConvertMsg2 {

    @RabbitHandler
    public void process(String msg, Channel channel, Message message) throws IOException {
        System.out.println("OrderReceiver收到  : ==" + msg +"==收到时间"+new Date());
        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("receiver success");
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            System.out.println("receiver fail");
        }

    }

}

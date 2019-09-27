package com.wei.mq.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: weizz
 * @Date: 2019/7/21 12:09
 * @Description:
 * @Version:1.0
 */
@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {
    @Autowired
    private DefaultMQProducer mqProducer;

    @RequestMapping("")
    public String async(){
        String msg = "测试异步消息";
        Message message = new Message("QuhaoTopic","QuhaoTag",msg.getBytes());
        try {
            mqProducer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送成功");
                }
                @Override
                public void onException(Throwable throwable) {
                    System.out.println("发送失败");
                }
            });
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "发送成功";
    }
}

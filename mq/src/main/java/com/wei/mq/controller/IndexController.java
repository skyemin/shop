package com.wei.mq.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: weizz
 * @Date: 2019/7/21 11:45
 * @Description:
 * @Version:1.0
 */
@Slf4j
@RestController
public class IndexController {

    @Autowired
    private DefaultMQProducer mqProducer;

    @RequestMapping("")
    public String index(){
        String msg = "测试消息";
        Message message = new Message("QuhaoTopic","QuhaoTag",msg.getBytes());
        try {
            SendResult sendResult = mqProducer.send(message);
            log.info("消息发送结果:{}",sendResult);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "发送成功";
    }
}

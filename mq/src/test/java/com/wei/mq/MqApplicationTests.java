package com.wei.mq;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqApplicationTests {

    /**使用RocketMq的生产者*/
    @Autowired
    private DefaultMQProducer defaultMQProducer;


    @Test
    public void send(){
        String msg = "demo msg test";
        log.info("开始发送消息："+msg);
        Message sendMsg = new Message("QuhaoTopic","QuhaoTag",msg.getBytes());
        //默认3秒超时
        SendResult sendResult = null;
        try {
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("消息发送响应信息：{}", sendResult);
    }

}

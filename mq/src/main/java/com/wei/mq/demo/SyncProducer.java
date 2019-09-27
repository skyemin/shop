package com.wei.mq.demo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Author: weizz
 * @Date: 2019/7/19 19:07
 * @Description:
 * @Version:1.0
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("192.168.1.10:9876");
        producer.start();

        for (int i = 1; i < 5; i++) {
            try {
                //Message(String topic, String tags, String keys, byte[] body)
                Message msg = new Message("TopicTest", "TagA", "OrderID188", ("Hello world" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        producer.shutdown();
    }
}


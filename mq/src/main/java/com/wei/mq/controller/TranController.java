/*
package com.wei.mq.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;

*/
/**
 * @Author: weizz
 * @Date: 2019/7/21 12:09
 * @Description:
 * @Version:1.0
 *//*

@Slf4j
@RestController
@RequestMapping("/tran")
public class TranController {

    @Autowired
    private DefaultMQProducer transactionMQProducer;

    @RequestMapping("")
    public void tran() throws MQClientException{
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });
       */
/* transactionMQProducer.setExecutorService(executorService);
        transactionMQProducer.setTransactionListener(new TransactionListenerImpl());*//*

        transactionMQProducer.start();
        try {
            String msg = "测试消息";
            Message message = new Message("QuhaoTopic","QuhaoTag",msg.getBytes());
            transactionMQProducer.sendMessageInTransaction(message,null);
        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }
}*/

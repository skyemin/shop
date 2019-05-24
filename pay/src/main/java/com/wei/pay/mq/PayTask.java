package com.wei.pay.mq;

import com.wei.model.Pay;
import com.wei.model.XcTask;
import com.wei.model.XcTaskHis;
import com.wei.pay.config.RabbitMQConfig;
import com.wei.pay.service.PayService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @version 1.0
 **/
@Component
public class PayTask {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private PayService payService;

    @RabbitListener(queues = "order_queue")
    public void receiveTask(XcTask xcTask){
       String content = xcTask.getRequestBody();
       String[] split = content.split(",");
       Pay pay = new Pay();
       pay.setOrder(split[0]);
       pay.setMoney(split[1]);
        XcTaskHis xcTaskHis = new XcTaskHis();
        BeanUtils.copyProperties(xcTask,xcTaskHis);
       //插入支付表和历史记录
        payService.insertPay(pay,xcTaskHis);
        //发送消息告诉订单服务
        rabbitTemplate.convertAndSend(RabbitMQConfig.USER_EXCHANGE,RabbitMQConfig.USER_BIND_KEY,xcTask);
    }
}

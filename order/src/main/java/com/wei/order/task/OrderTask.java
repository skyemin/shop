package com.wei.order.task;

import com.wei.model.XcTask;
import com.wei.order.config.RabbitMQConfig;
import com.wei.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: weizz
 * @Date: 2019/5/23 18:06
 * @Description:
 * @Version:1.0
 */
@Component
public class OrderTask {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "pay_queue")
    public void receiveTask(XcTask xcTask){
        orderService.deleteXcTaskById(xcTask.getId());
    }


    @Scheduled(cron = "0 */1 * * * ?")
    //@Scheduled(cron = "*/5 * * * * ?")
    public void task(){
        //每一分钟执行一次
        List<XcTask> xcTasks = orderService.selectTaskBeforeTime();
        //发送mq
        xcTasks.forEach(
                new Consumer<XcTask>() {
                    @Override
                    public void accept(XcTask xcTask) {
                        if(orderService.updateVersion(xcTask)>0){
                            rabbitTemplate.convertAndSend(RabbitMQConfig.USER_EXCHANGE,RabbitMQConfig.USER_BIND_KEY,xcTask);
                            //更新任务时间
                            xcTask.setUpdateTime(new Date());
                            orderService.updateXcTask(xcTask);
                        }
                    }
                }
        );
    }
}

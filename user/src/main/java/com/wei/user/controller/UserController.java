package com.wei.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wei.api.service.UserServiceApi;
import com.wei.model.User;
import com.wei.user.config.ConfigInfoProperties;
import com.wei.user.config.RabbitMQConfig;
import com.wei.user.service.UserService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: weizz
 * @Date: 2019/5/21 10:25
 * @Description:
 * @Version:1.0
 */
@RestController
public class UserController implements UserServiceApi,RabbitTemplate.ReturnCallback {

    @Autowired
    private UserService userService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private ConfigInfoProperties configInfoProperties;

    @Override
    @RequestMapping(value = "/getUserList")
    @HystrixCommand(fallbackMethod = "test")
    public List<User> getUserList(){
        List<User> userList = userService.findUserList();
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("OrderSender消息发送失败" + cause + correlationData.toString());
            } else {
                System.out.println("OrderSender 消息发送成功 ");
            }
        });
        rabbitTemplate.convertAndSend(RabbitMQConfig.USER_EXCHANGE,RabbitMQConfig.USER_BIND_KEY1,"我进来了");
        return userList;
    }

    //降级方法
    public List<User> test(){
        List<User> userList = new ArrayList<>();
        User u = new User();
        u.setId(9);
        u.setName("苏大强");
        u.setAge(61);
        userList.add(u);
        return userList;
    }
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }

    @RequestMapping("/testInsert")
    public void testInsert(){
        try {
            User user = new User();
            user.setName("测试");
            user.setAge(20);
            userService.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/testConfig")
    public String testConfig(){
        return configInfoProperties.getConfig();
    }
}

package com.wei.web.controller;

import com.wei.model.User;
import com.wei.web.client.RedisService;
import com.wei.web.client.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: weizz
 * @Date: 2019/5/21 11:32
 * @Description:
 * @Version:1.0
 */
@Controller
public class indexController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/index")
    public String index(Model model){
        List<User> userList = userService.getUserList();
       String[] list = {"123","456"};
        redisService.setObjectKey("key1",list);
        model.addAttribute("userList",userList);
        return "/user";
    }
}

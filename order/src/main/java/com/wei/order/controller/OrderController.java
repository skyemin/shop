package com.wei.order.controller;

import com.wei.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: weizz
 * @Date: 2019/5/24 11:42
 * @Description:
 * @Version:1.0
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("/buy")
    public String buy(){
        orderService.buy();

        return "下单成功";
    }
}

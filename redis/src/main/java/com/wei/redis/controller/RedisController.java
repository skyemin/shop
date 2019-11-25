package com.wei.redis.controller;

import com.wei.model.User;
import com.wei.redis.service.RedisService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: weizz
 * @Date: 2019/5/21 17:12
 * @Description:
 * @Version:1.0
 */
@RestController
public class RedisController {

  /*  @Autowired
    private RedisService redisService;*/
    @Autowired
    private RedisService1 redisService1;

   /* @RequestMapping("/setKey")
    public void setKey(String key,String value){
        redisService.setStr(key,value);
    }
    @RequestMapping("/getKey")
    public String getKey(String key){
        return redisService.getStr(key);
    }
    @RequestMapping("/setObjectKey")
    public void setObjectKey(String key,String[] value){
        redisService.setObj(key,value);
    }
    @RequestMapping("/getObjectKey")
    public Object getObjectKey(Object key){
        return redisService.getObj(key);
    }
*/
    @RequestMapping("/setKey1")
    public void setKey1(String key,String value){
        redisService1.saveLiked2Redis(key,value);
    }
}

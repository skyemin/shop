package com.wei.configclient.controller;

import com.wei.configclient.config.ConfigInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: weizz
 * @Date: 2019/5/30 18:12
 * @Description:
 * @Version:1.0
 */
@RestController
public class TestController {

    @Autowired
    ConfigInfoProperties configInfoProperties;
    @RequestMapping("/test")
    public String test(){
        return configInfoProperties.getConfig();
    }
}

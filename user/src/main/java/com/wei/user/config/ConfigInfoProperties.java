package com.wei.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: skye
 * @date: 2019/6/17
 * @time: 23:55
 * @description : 配置信息
 */
@Component
public class ConfigInfoProperties {

    @Value("${cn.springcloud.book.config}")
    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}

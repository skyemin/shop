package com.wei.web.client;

import com.wei.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "redisService")
public interface RedisService {

    @RequestMapping("/setKey")
    public void setKey(@RequestParam("key") String key, @RequestParam("value") String value);

    @RequestMapping("/getKey")
    public String getKey(String key);

    @RequestMapping("/setObjectKey")
    public void setObjectKey(@RequestParam("key") String key,@RequestBody String[] value);

    @RequestMapping("/getObjectKey")
    public Object getObjectKey(Object key);
}

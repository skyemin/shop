package com.wei.web.client;

import com.wei.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "userService")
public interface UserService {

    @RequestMapping("/getUserList")
    public List<User> getUserList();
}

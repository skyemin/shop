package com.wei.user.service;


import com.wei.model.User;

import java.util.List;

/**
 * @Author: weizz
 * @Date: 2019/5/21 10:35
 * @Description:
 * @Version:1.0
 */
public interface UserService {
    //查询用户列表
    List<User> findUserList();
}

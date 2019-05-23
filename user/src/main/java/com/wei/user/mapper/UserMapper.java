package com.wei.user.mapper;


import com.wei.model.User;

import java.util.List;

/**
 * @Author: weizz
 * @Date: 2019/5/21 10:36
 * @Description:
 * @Version:1.0
 */
public interface UserMapper {

    List<User> findUserList();
}

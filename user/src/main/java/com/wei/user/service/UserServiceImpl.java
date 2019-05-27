package com.wei.user.service;

import com.wei.model.User;
import com.wei.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: weizz
 * @Date: 2019/5/21 10:35
 * @Description:
 * @Version:1.0
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findUserList() {
        return userMapper.findUserList();
    }

    @Override
    @Transactional
    public void insertUser(User user){
        userMapper.insertUser(user);
        int i = 1/0;
    }
}

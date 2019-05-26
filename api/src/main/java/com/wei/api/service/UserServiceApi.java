package com.wei.api.service;

import com.wei.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value="用户管理接口",description = "用户管理接口，提供用户的增、删、改、查")
public interface UserServiceApi {
    @ApiOperation("查询用户列表")
    public List<User> getUserList();
    @ApiOperation("根据id查询")
    public User selectUserById(String id);
}

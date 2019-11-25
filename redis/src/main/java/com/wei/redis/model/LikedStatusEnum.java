package com.wei.redis.model;

import lombok.Getter;

/**
 * @Author: skye
 * @Date: 2019/11/25 11:02
 * @Description:
 * @Version:1.0
 */
@Getter
public enum LikedStatusEnum {
    LIKE(1, "点赞"),
    UNLIKE(0, "取消点赞/未点赞"),
    ;

    private Integer code;

    private String msg;

    LikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

package com.wei.redis.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @Author: skye
 * @Date: 2019/11/25 11:01
 * @Description:
 * @Version:1.0
 */
@Data
public class UserLike {

    //主键id
    private Integer id;

    //被点赞的用户的id
    private String likedUserId;

    //点赞的用户的id
    private String likedPostId;

    //点赞的状态.默认未点赞
    private Integer status = LikedStatusEnum.UNLIKE.getCode();

    public UserLike() {
    }

    public UserLike(String likedUserId, String likedPostId, Integer status) {
        this.likedUserId = likedUserId;
        this.likedPostId = likedPostId;
        this.status = status;
    }
}

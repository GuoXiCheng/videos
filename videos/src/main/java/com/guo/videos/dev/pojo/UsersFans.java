package com.guo.videos.dev.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

//用户-粉丝表

@Table(name="users_fans")
@Data
public class UsersFans {
    @Id
    private String id;

    //用户
    @Column(name = "user_id")
    private String userId;

    //粉丝
    @Column(name = "fan_id")
    private String fanId;

}

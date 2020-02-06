package com.guo.videos.dev.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

//用户喜爱的视频表

@Data
@Table(name = "users_like_videos")
public class UsersLikeVideos {
    @Id
    private String id;

    //用户
    @Column(name = "user_id")
    private String userId;

    //视频
    @Column(name = "video_id")
    private String videoId;
}

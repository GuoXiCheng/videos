package com.videos.pojo;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

//用户喜爱的视频表

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}

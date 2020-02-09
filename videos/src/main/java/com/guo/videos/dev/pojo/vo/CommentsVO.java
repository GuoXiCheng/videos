package com.guo.videos.dev.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentsVO {
    private String id;
    private String videoId;
    private String fromUserId;
    private Date createTime;
    private String comment;
    private String faceImage;
    private String nickname;
    private String toNickname;
    private String timeAgoStr;
}

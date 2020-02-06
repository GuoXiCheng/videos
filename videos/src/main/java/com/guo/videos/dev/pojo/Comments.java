package com.guo.videos.dev.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

//评论表

@Data
public class Comments {
    @Id
    private String id;

    @Column(name = "father_comment_id")
    private String fatherCommentId;

    @Column(name = "to_user_id")
    private String toUserId;

    //视频id
    @Column(name = "video_id")
    private String videoId;

    //留言者，评论的用户id
    @Column(name = "from_user_id")
    private String fromUserId;

    @Column(name = "create_time")
    private Date createTime;

    //评论内容
    private String comment;
}

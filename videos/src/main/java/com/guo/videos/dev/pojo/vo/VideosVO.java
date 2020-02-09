package com.guo.videos.dev.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class VideosVO {
    //主键id
    private String id;
    //发布者id
    private String userId;
    //音频id
    private String audioId;
    //视频描述
    private String videoDesc;
    //视频存放路径
    private String videoPath;
    //视频秒数
    private Float videoSeconds;
    //视频宽度
    private Integer videoWidth;
    //视频高度
    private Integer videoHeight;
    //视频封面路径
    private String coverPath;
    //喜欢、赞美的数量
    private Long likeCounts;
    //视频状态
    private Integer status;
    //创建时间
    private Date createTime;
    //头像路径
    private String faceImage;
    //昵称
    private String nickname;
}

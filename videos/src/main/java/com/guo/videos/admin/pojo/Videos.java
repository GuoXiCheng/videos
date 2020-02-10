package com.guo.videos.admin.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Videos {
    private String id;

    private String userId;

    private String audioId;

    private String videoDesc;

    private String videoPath;

    private Float videoSeconds;

    private Integer videoWidth;

    private Integer videoHeight;

    private String coverPath;

    private Long likeCounts;

    private Integer status;

    private Date createTime;

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public void setAudioId(String audioId) {
        this.audioId = audioId == null ? null : audioId.trim();
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc == null ? null : videoDesc.trim();
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath == null ? null : videoPath.trim();
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath == null ? null : coverPath.trim();
    }
}

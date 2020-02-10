package com.guo.videos.admin.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class UsersReport {
    private String id;

    private String dealUserId;

    private String dealVideoId;

    private String title;

    private String content;

    private String userid;

    private Date createDate;

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId == null ? null : dealUserId.trim();
    }

    public void setDealVideoId(String dealVideoId) {
        this.dealVideoId = dealVideoId == null ? null : dealVideoId.trim();
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}

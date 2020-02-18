package com.videos.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class UsersReport {
    @Id
    private String id;

    //被举报用户id
    @Column(name = "deal_user_id")
    private String dealUserId;

    //被举报视频id
    @Column(name = "deal_video_id")
    private String dealVideoId;

    //类型标题，让用户选择，详情见 枚举
    private String title;

    //举报内容
    private String content;

    //举报人的id
    private String userId;

    //举报时间
    @Column(name = "create_date")
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId;
    }

    public String getDealVideoId() {
        return dealVideoId;
    }

    public void setDealVideoId(String dealVideoId) {
        this.dealVideoId = dealVideoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

package com.guo.videos.admin.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Reports {
    private String id;
    private String title;
    private String content;
    private Date createDate;

    private String dealUsername;

    private String dealVideoId;
    private String videoPath;
    private Integer status;

    private String submitUsername;
}

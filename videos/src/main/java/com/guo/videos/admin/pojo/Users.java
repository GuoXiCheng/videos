package com.guo.videos.admin.pojo;

import lombok.Data;

@Data
public class Users {
    private String id;

    private String username;

    private String password;

    private String faceImage;

    private String nickname;

    private Integer fansCounts;

    private Integer followCounts;

    private Integer receiveLikeCounts;

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage == null ? null : faceImage.trim();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }
}

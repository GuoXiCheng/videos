package com.videos.pojo;

import javax.persistence.Id;

public class Bgm {
    @Id
    private String id;

    //作者
    private String author;

    //歌曲名
    private String name;

    //播放地址
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

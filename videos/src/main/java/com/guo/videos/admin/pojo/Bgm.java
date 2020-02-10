package com.guo.videos.admin.pojo;

import lombok.Getter;

@Getter
public class Bgm {
    private String id;

    private String author;

    private String name;

    private String path;

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}

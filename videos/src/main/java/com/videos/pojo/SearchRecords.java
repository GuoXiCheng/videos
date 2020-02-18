package com.videos.pojo;


import javax.persistence.Id;
import javax.persistence.Table;

//搜索记录表

@Table(name = "search_records")
public class SearchRecords {
    @Id
    private String id;

    //搜索的内容
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.guo.videos.dev.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

//搜索记录表

@Data
@Table(name = "search_records")
public class SearchRecords {
    @Id
    private String id;

    //搜索的内容
    private String content;
}

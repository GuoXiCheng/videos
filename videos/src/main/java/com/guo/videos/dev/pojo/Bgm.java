package com.guo.videos.dev.pojo;


import lombok.Data;

import javax.persistence.Id;

//歌曲表

@Data
public class Bgm {

    @Id
    private String id;

    //作者
    private String author;

    //歌曲名
    private String name;

    //播放地址
    private String path;
}

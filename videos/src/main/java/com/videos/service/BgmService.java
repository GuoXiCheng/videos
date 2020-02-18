package com.videos.service;

import com.videos.pojo.Bgm;

import java.util.List;

public interface BgmService {
    //查询背景音乐列表
    List<Bgm> queryBgmList();

    //根据bgmId查询信息
    Bgm queryBgmById(String bgmId);
}

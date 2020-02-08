package com.guo.videos.dev.service;

import com.guo.videos.dev.pojo.Bgm;

import java.util.List;

public interface BgmService {
    //查询背景音乐列表
    List<Bgm> queryBgmList();

    //根据bgmId查询信息
    Bgm queryBgmById(String bgmId);
}

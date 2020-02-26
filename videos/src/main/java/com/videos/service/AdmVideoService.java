package com.videos.service;

import com.videos.Utils.PagedResult;
import com.videos.pojo.Bgm;

public interface AdmVideoService {
    PagedResult queryReportList(Integer page,Integer pageSize);

    void updateVideoStatus(String videoId,Integer statusCode);

    void addBgm(Bgm bgm);

    PagedResult queryBgmList(Integer page, Integer pageSize);

    void deleteBgm(String bgmId);
}

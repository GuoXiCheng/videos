package com.guo.videos.admin.service;

import com.guo.videos.Utils.PagedResult;
import com.guo.videos.admin.pojo.Bgm;

public interface VideoAdminService {
    PagedResult queryReportList(Integer page,Integer pageSize);

    void updateVideoStatus(String videoId,Integer statusCode);

    void addBgm(Bgm bgm);

    PagedResult queryBgmList(Integer page,Integer pageSize);

    void deleteBgm(String bgmId);
}

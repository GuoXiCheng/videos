package com.guo.videos.dev.service;

import com.guo.videos.Utils.PagedResult;
import com.guo.videos.dev.pojo.Comments;
import com.guo.videos.dev.pojo.Videos;

import java.util.List;

public interface VideoService {
    //保存视频
    String saveVideo(Videos video);

    //修改视频的封面
    void updateVideo(String videoId,String coverPath);

    //分页查询视频列表
    PagedResult getAllVideos(Videos video, Integer isSaveRecord, Integer page, Integer pageSize);

    //获取热搜词列表
    List<String> getHotWords();

    //用户点赞视频
    void userLikeVideo(String userId,String videoId,String videoCreaterId);

    //用户取消点赞视频
    void userUnLikeVideo(String userId, String videoId, String videoCreaterId);

    PagedResult queryMyLikeVideos(String userId, Integer page, Integer pageSize);


    PagedResult queryMyFollowVideos(String userId, Integer page, int pageSize);

    //用户留言
    void saveComment(Comments comment);

    //留言分页
    PagedResult getAllComments(String videoId, Integer page, Integer pageSize);
}

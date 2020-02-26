package com.videos.service;

import com.videos.Utils.PagedResult;
import com.videos.pojo.Comments;
import com.videos.pojo.Videos;
import com.videos.vo.CommentsVO;
import com.videos.vo.VideosVO;

import java.util.List;

public interface WxVideoService {

    //查询一条视频数据
    Videos selectOneVideo();

    //保存视频
    String saveVideo(Videos video);

    //修改视频的封面
    void updateVideo(String videoId,String coverPath);

    //分页查询视频列表
//    PagedResult getAllVideos(Videos video, Integer isSaveRecord, Integer page, Integer pageSize);


    //分页查询所有ship
    PagedResult queryAllVideos(Integer page,Integer pageSize);

    //获取热搜词列表
    List<String> getHotWords();

    //根据搜索内容查询视频
    List<VideosVO> getVideoListByDesc(String videoDesc);

    //用户点赞视频
    void userLikeVideo(String userId,String videoId,String videoCreaterId);

    //用户取消点赞视频
    void userUnLikeVideo(String userId, String videoId, String videoCreaterId);

    PagedResult queryMyWorkVideos(String userId,Integer page,Integer pageSize);

    PagedResult queryMyLikeVideos(String userId, Integer page, Integer pageSize);

    PagedResult queryMyFollowVideos(String userId, Integer page, Integer pageSize);

    //用户留言
    void saveComment(Comments comment);

    //留言
    PagedResult getAllComments(String videoId,Integer page,Integer pageSize);
}

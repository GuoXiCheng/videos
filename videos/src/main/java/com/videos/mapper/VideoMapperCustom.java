package com.videos.mapper;

import com.videos.vo.VideosVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoMapperCustom {
//    List<VideosVO> queryAllVideos(@Param("videoDesc") String videoDesc, @Param("userId") String userId);

    List<VideosVO> queryAllVideos(@Param("page") Integer page,@Param("pageSize") Integer pageSize);

    List<VideosVO> getVideoListByDesc(@Param("videoDesc") String videoDesc);

//    void insertHotWord(@Param("videoDesc") String videoDesc);

    Integer queryCount();

    Integer queryCountMyWork(String userId);

    Integer queryCountMyLike(String userId);

    Integer queryCountMyFollow(String userId);

    List<VideosVO> queryMyWorkVideos(@Param("userId") String userId,@Param("page") Integer page,@Param("pageSize") Integer pageSize);

    List<VideosVO> queryMyLikeVideos(@Param("userId") String userId,@Param("page") Integer page,@Param("pageSize") Integer pageSize);

    List<VideosVO> queryMyFollowVideos(@Param("userId") String userId,@Param("page") Integer page,@Param("pageSize") Integer pageSize);
}

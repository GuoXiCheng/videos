package com.videos.mapper;

import com.videos.vo.VideosVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoMapperCustom {
    List<VideosVO> queryAllVideos(@Param("videoDesc") String videoDesc, @Param("userId") String userId);

    List<VideosVO> queryMyLikeVideos(@Param("userId") String userId);

    List<VideosVO> queryMyFollowVideos(String userId);
}

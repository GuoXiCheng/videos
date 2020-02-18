package com.videos.mapper;

import com.videos.pojo.Videos;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideosMapper {
    void insertSelective(Videos video);

    void updateByPrimaryKeySelective(Videos video);

    void addVideoLikeCount(String videoId);

    void reduceVideoLikeCount(String videoId);

    Videos selectOneVideo();
}

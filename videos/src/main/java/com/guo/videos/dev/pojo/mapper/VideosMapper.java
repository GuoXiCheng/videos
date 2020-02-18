package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.dev.pojo.Videos;
import com.guo.videos.dev.pojo.vo.VideosVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideosMapper {
    void insertSelective(Videos video);

    void updateByPrimaryKeySelective(Videos video);

    void addVideoLikeCount(String videoId);

    void reduceVideoLikeCount(String videoId);

    Videos selectOneVideo();
}

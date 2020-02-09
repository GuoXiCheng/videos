package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.dev.pojo.Videos;
import com.guo.videos.dev.pojo.vo.VideosVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideosMapper {
    void insertSelective(Videos video);

    void updateByPrimaryKeySelective(Videos video);

    List<VideosVO> queryAllVideos(String desc,String userId);

    void addVideoLikeCount(String videoId);

    void reduceVideoLikeCount(String videoId);

    List<VideosVO> queryMyLikeVideos(String userId);

    List<VideosVO> queryMyFollowVideos(String userId);
}

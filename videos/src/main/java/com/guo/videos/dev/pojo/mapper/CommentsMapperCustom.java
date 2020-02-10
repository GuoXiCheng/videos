package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.dev.pojo.vo.CommentsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapperCustom {
    List<CommentsVO> queryComments(String vidoeId);
}

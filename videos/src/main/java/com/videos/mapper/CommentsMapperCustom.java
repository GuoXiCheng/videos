package com.videos.mapper;

import com.videos.vo.CommentsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapperCustom {
    List<CommentsVO> queryComments(String vidoeId);
}

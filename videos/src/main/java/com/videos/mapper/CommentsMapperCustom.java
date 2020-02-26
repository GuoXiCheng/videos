package com.videos.mapper;

import com.videos.vo.CommentsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentsMapperCustom {
    List<CommentsVO> queryComments(@Param("videoId") String vidoeId,@Param("page") Integer page,@Param("pageSize") Integer pageSize);

    Integer queryCommentsCount();
}

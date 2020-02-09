package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.dev.pojo.Comments;
import com.guo.videos.dev.pojo.vo.CommentsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapper {
    void insertOne(Comments comment);

    List<CommentsVO> queryComments(String vidoeId);
}

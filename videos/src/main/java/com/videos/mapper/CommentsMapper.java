package com.videos.mapper;

import com.videos.pojo.Comments;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentsMapper {
    void insertOne(Comments comment);

}

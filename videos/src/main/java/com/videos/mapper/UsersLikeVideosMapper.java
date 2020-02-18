package com.videos.mapper;

import com.videos.pojo.UsersLikeVideos;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersLikeVideosMapper {
    List<UsersLikeVideos> selectByEntity(UsersLikeVideos usersLikeVideos);

    void insertOne(UsersLikeVideos usersLikeVideos);

    void deleteByEntity(UsersLikeVideos usersLikeVideos);
}

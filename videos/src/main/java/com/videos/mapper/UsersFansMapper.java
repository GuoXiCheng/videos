package com.videos.mapper;

import com.videos.pojo.UsersFans;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersFansMapper {
    void insertOne(UsersFans usersFans);

    void deleteByEntity(UsersFans usersFans);

    List<UsersFans> selectByEntity(UsersFans usersFans);
}

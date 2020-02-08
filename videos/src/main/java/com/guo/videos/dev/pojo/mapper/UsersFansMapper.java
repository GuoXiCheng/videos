package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.dev.pojo.UsersFans;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersFansMapper {
    void insertOne(UsersFans usersFans);

    void deleteByEntity(UsersFans usersFans);

    List<UsersFans> selectByEntity(UsersFans usersFans);
}

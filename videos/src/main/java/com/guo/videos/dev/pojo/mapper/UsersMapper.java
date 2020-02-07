package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.dev.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    Users selectOne(Users user);

    void insertOne(Users user);


}

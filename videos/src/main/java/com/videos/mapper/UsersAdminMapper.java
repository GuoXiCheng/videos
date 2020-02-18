package com.videos.mapper;

import com.videos.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersAdminMapper {
    List<Users> selectAll(@Param("page") Integer page, @Param("pageSize") Integer pageSize);

    Integer selectCount();
}

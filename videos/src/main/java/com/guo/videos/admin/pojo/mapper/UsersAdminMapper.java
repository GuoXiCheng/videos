package com.guo.videos.admin.pojo.mapper;

import com.guo.videos.admin.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersAdminMapper {
    List<Users> selectAll(@Param("page") Integer page,@Param("pageSize") Integer pageSize);

    Integer selectCount();
}

package com.guo.videos.admin.pojo.mapper;

import com.guo.videos.admin.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersAdminMapper {
    List<Users> selectByEntity(Users user);
}

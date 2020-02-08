package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.dev.pojo.UsersReport;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersReportMapper {
    void insertOne(UsersReport usersReport);
}

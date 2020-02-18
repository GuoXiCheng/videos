package com.videos.mapper;

import com.videos.pojo.UsersReport;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersReportMapper {
    void insertOne(UsersReport usersReport);
}

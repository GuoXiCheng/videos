package com.guo.videos.admin.pojo.mapper;

import com.guo.videos.admin.pojo.vo.Reports;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoAdminMapper {
    List<Reports> queryReportList(@Param("page") Integer page, @Param("pageSize") Integer pageSize);

    Integer selectCounts();

    void updateVideoStatus(@Param("videoId") String videoId,@Param("statusCode") Integer statusCode);
}

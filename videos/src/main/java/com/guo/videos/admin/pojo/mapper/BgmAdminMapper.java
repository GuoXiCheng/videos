package com.guo.videos.admin.pojo.mapper;

import com.guo.videos.admin.pojo.Bgm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BgmAdminMapper {
    void addBgm(Bgm bgm);

    List<Bgm> selectAll(@Param("page") Integer page, @Param("pageSize") Integer pageSize);

    Integer selectCount();

    void deleteBgm(String bgmId);
}

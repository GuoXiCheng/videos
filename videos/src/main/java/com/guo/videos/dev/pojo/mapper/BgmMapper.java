package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.dev.pojo.Bgm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BgmMapper {
    List<Bgm> selectAll();

    Bgm selectByPrimaryKey(String BgmId);
}

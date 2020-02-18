package com.videos.mapper;

import com.videos.pojo.SearchRecords;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchRecordsMapper {
    void insertOne(SearchRecords searchRecords);

    List<String> getHotWords();
}

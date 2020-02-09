package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.Utils.KeyUtil;
import com.guo.videos.dev.pojo.SearchRecords;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchRecordsMapperTest {

    @Autowired(required = false)
    private SearchRecordsMapper searchRecordsMapper;

    @Test
    public void insertOne() {
        SearchRecords searchRecord = new SearchRecords();
        String id = KeyUtil.genUniqueKey();
        searchRecord.setId(id);
        searchRecord.setContent("哇哈哈");
        searchRecordsMapper.insertOne(searchRecord);
    }
}
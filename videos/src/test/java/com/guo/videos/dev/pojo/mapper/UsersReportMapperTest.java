package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.Utils.KeyUtil;
import com.guo.videos.dev.pojo.UsersReport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersReportMapperTest {

    @Autowired(required = false)
    private UsersReportMapper usersReportMapper;

    @Test
    public void insertOne() {
        UsersReport usersReport = new UsersReport();
        String id = KeyUtil.genUniqueKey();
        usersReport.setId(id);
        usersReport.setCreateDate(new Date());
        usersReport.setContent("hahaha");
        usersReport.setDealUserId("111111");
        usersReport.setTitle("heieheieh");
        usersReport.setUserId("3333333");
        usersReport.setDealVideoId("4444444");
        usersReportMapper.insertOne(usersReport);
    }
}
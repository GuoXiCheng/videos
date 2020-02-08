package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.Utils.KeyUtil;
import com.guo.videos.dev.pojo.UsersFans;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersFansMapperTest {

    @Autowired(required = false)
    private UsersFansMapper usersFansMapper;

    @Test
    public void insertOne() {
        UsersFans usersFans = new UsersFans();
        String id = KeyUtil.genUniqueKey();
        usersFans.setId(id);
        usersFans.setFanId("123123");
        usersFans.setUserId("456456");
        usersFansMapper.insertOne(usersFans);
    }

    @Test
    public void deleteByEntity(){
        UsersFans usersFans = new UsersFans();
        usersFans.setUserId("456456");
        usersFans.setFanId("123123");
        usersFansMapper.deleteByEntity(usersFans);
    }

    @Test
    public void selectByEntity(){
        UsersFans usersFans = new UsersFans();
        usersFans.setUserId("180425CFA4RB6T0H");
        usersFans.setFanId("2001307F0HW6M614");
        List<UsersFans> result = usersFansMapper.selectByEntity(usersFans);
        Assert.assertEquals(1,result.size());
    }
}
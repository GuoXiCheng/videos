package com.guo.videos.dev.service.impl;

import com.guo.videos.dev.pojo.Users;
import com.guo.videos.dev.pojo.mapper.UsersMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Table;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    public void queryUsernameIsExist(){
        boolean result = userServiceImpl.queryUsernameIsExist("imooc");
        Assert.assertEquals(true,result);
    }

    @Test
    public void saveUser(){
        Users user = new Users();
        user.setUsername("hahaha");
        user.setPassword("hahaha");
        user.setNickname("hahaha");
        user.setFansCounts(0);
        user.setFollowCounts(0);
        user.setReceiveLikeCounts(0);
        userServiceImpl.saveUser(user);
    }
}
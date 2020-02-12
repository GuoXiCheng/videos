package com.guo.videos.admin.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersServiceImplTest {

    @Autowired(required = false)
    private UsersServiceImpl usersServiceImpl;

    @Test
    public void queryUsers() {
        usersServiceImpl.queryUsers(1,10);
    }
}
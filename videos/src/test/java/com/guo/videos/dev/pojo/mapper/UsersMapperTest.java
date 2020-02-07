package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.dev.pojo.Users;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersMapperTest {

    @Autowired(required = false)
    private UsersMapper usersMapper;

    @Test
    public void selectOne() {
        Users user = new Users();
        user.setUsername("test-imooc-1111");
        user.setPassword("9999");
        Users result = usersMapper.selectOne(user);
        Assert.assertNotNull(result);
    }
}
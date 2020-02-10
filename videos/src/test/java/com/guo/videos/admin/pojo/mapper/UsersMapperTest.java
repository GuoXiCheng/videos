package com.guo.videos.admin.pojo.mapper;

import com.guo.videos.admin.pojo.Users;
import com.guo.videos.dev.pojo.mapper.UsersMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersMapperTest {

    @Autowired(required = false)
    private UsersAdminMapper usersAdminMapper;

    @Test
    public void selectByEntity() {
        Users user = new Users();
        user.setUsername("imooc1");
        user.setNickname("imooc1");
        List<Users> result = usersAdminMapper.selectByEntity(user);
        Assert.assertNotNull(result);
    }
}
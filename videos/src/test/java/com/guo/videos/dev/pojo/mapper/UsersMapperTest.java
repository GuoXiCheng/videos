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
//        Users user = new Users();
//        user.setUsername("test-imooc-1111");
//        user.setPassword("9999");
//        Users result = usersMapper.selectOne(user);
//        Assert.assertNotNull(result);
    }

    @Test
    public void updateBySelective(){
        Users user = new Users();
        user.setId("1581066029673843868");
        user.setFaceImage("haha.jpg");
//        user.setUsername("guoxicheng");
//        user.setNickname("xicheng");
        usersMapper.updateBySelective(user);
    }

    @Test
    public void selectOneById(){
        Users user = usersMapper.selectOneById("1581066029673843868");
        Assert.assertNotNull(user);
    }

    @Test
    public void addFansCount(){
        usersMapper.addFansCount("1581066029673843868");
    }

    @Test
    public void addFollersCount(){
        usersMapper.addFollersCount("1581066029673843868");
    }

    @Test
    public void reduceFansCount(){
        usersMapper.reduceFansCount("1581066029673843868");
    }

    @Test
    public void reduceFollersCount(){
        usersMapper.reduceFollersCount("1581066029673843868");
    }

    @Test
    public void addReceiveLikeCount(){
        usersMapper.addReceiveLikeCount("1581066029673843868");
    }

    @Test
    public void reduceReceiveLikeCount(){
        usersMapper.reduceReceiveLikeCount("1581066029673843868");
    }

}
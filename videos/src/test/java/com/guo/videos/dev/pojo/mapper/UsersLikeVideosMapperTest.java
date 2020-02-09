package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.Utils.KeyUtil;
import com.guo.videos.dev.pojo.UsersLikeVideos;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersLikeVideosMapperTest {

    @Autowired(required = false)
    private UsersLikeVideosMapper usersLikeVideosMapper;

    @Test
    public void selectByEntity() {
        UsersLikeVideos usersLikeVideos = new UsersLikeVideos();
        usersLikeVideos.setUserId("180425CFA4RB6T0H");
        usersLikeVideos.setVideoId("200127HGC54CB1GC");
        List<UsersLikeVideos> result = usersLikeVideosMapper.selectByEntity(usersLikeVideos);
        Assert.assertEquals(1,result.size());
    }

    @Test
    public void insertOne(){
        UsersLikeVideos usersLikeVideos = new UsersLikeVideos();
        String id = KeyUtil.genUniqueKey();
        usersLikeVideos.setId(id);
        usersLikeVideos.setUserId("111222");
        usersLikeVideos.setVideoId("333444");
        usersLikeVideosMapper.insertOne(usersLikeVideos);
    }

    @Test
    public void deleteByEntity(){
        UsersLikeVideos usersLikeVideos = new UsersLikeVideos();
        usersLikeVideos.setUserId("111222");
        usersLikeVideos.setVideoId("333444");
        usersLikeVideosMapper.deleteByEntity(usersLikeVideos);
    }
}
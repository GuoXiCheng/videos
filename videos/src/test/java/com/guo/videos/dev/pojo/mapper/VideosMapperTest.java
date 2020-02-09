package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.Utils.KeyUtil;
import com.guo.videos.dev.pojo.Videos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VideosMapperTest {

    @Autowired(required = false)
    private VideosMapper videosMapper;

    @Test
    public void insertSelective() {
        Videos video = new Videos();
        String id = KeyUtil.genUniqueKey();
        video.setId(id);
        video.setUserId("111");
        video.setAudioId("222");
        video.setVideoDesc("哈哈哈");
        video.setVideoPath("/hahaha.avi");
        video.setVideoSeconds(new Float(9.99));
        video.setVideoWidth(1920);
        video.setVideoHeight(1280);
        video.setCoverPath("/hahaha.jpg");
        video.setLikeCounts(new Long(1000));
        video.setStatus(0);
        video.setCreateTime(new Date());
        videosMapper.insertSelective(video);
    }

    @Test
    public void updateByPrimaryKeySelective(){
        Videos video = new Videos();
        video.setId("1581236640044971436");
        video.setCoverPath("/heiheihei.mp4");
        videosMapper.updateByPrimaryKeySelective(video);
    }

    @Test
    public void addVideoLikeCount(){
        videosMapper.addVideoLikeCount("1581236640044971436");
    }

    @Test
    public void reduceVideoLikeCount(){
        videosMapper.reduceVideoLikeCount("1581236640044971436");
    }
}
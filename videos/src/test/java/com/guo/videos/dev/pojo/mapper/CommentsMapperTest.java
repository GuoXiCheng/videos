package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.Utils.KeyUtil;
import com.guo.videos.dev.pojo.Comments;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentsMapperTest {

    @Autowired(required = false)
    private CommentsMapper commentsMapper;

    @Test
    public void insertOne() {
        Comments comment = new Comments();
        String id = KeyUtil.genUniqueKey();
        comment.setId(id);
        comment.setVideoId("200127H16319MS14");
        comment.setFromUserId("oaVkc5D5a22Ydk_jY_KheX1W-xkM");
        comment.setComment("111111");
        comment.setCreateTime(new Date());
        commentsMapper.insertOne(comment);
    }
}
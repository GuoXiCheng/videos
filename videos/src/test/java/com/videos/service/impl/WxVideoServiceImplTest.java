package com.videos.service.impl;

import com.videos.Utils.PagedResult;
import com.videos.mapper.VideoMapperCustom;
import com.videos.service.WxVideoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WxVideoServiceImplTest {

    @Autowired(required = false)
    private WxVideoService wxVideoService;

    @Test
    public void queryMyWorkVideos() {
        PagedResult pagedResult = wxVideoService.queryMyWorkVideos("A111111111111111111111",1,5);
        Assert.assertEquals(5,pagedResult.getRows().size());
    }

    @Test
    public void queryAllVideos(){
        PagedResult pagedResult = wxVideoService.queryAllVideos(1,10);
        Assert.assertEquals(10,pagedResult.getRows().size());
    }
}
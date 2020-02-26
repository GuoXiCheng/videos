package com.videos.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VideoMapperCustomTest {

    @Autowired(required = false)
    private VideoMapperCustom videoMapperCustom;

    @Test
    public void queryCount(){
        Integer count = videoMapperCustom.queryCount();
        Assert.assertEquals(java.util.Optional.of(50),count);
    }
}
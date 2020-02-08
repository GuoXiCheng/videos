package com.guo.videos.dev.pojo.mapper;

import com.guo.videos.dev.pojo.Bgm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BgmMapperTest {

    @Autowired(required = false)
    private BgmMapper bgmMapper;

    @Test
    public void selectAll() {
        List<Bgm> bgms = bgmMapper.selectAll();
        Assert.assertEquals(2,bgms.size());
    }

    @Test
    public void selectByPrimaryKey() {
        Bgm bgm = bgmMapper.selectByPrimaryKey("200201DRBNH1X4ZC");
        Assert.assertNotNull(bgm);
    }
}
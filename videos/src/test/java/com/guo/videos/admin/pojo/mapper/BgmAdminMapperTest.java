package com.guo.videos.admin.pojo.mapper;

import com.guo.videos.admin.pojo.Bgm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BgmAdminMapperTest {

    @Autowired(required = false)
    private BgmAdminMapper bgmAdminMapper;

    @Test
    public void addBgm() {
        Bgm bgm = new Bgm();
        bgm.setId("111222333");
        bgm.setAuthor("gugu");
        bgm.setName("dodo");
        bgm.setPath("/faefa/faef.mp3");
        bgmAdminMapper.addBgm(bgm);
    }
}
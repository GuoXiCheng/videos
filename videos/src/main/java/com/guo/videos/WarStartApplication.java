package com.guo.videos;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//继承SpringBootServletInitializer相当于使用web.xml形式部署
public class WarStartApplication extends SpringBootServletInitializer {

    //重写configure
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //使用web.xml运行应用程序，指向Application，最后启动springboot
        return builder.sources(VideosApplication.class);
    }
}

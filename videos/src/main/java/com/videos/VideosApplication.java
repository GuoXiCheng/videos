package com.videos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VideosApplication {
//WebMvcConfigurationSupport
    public static void main(String[] args) {
        SpringApplication.run(VideosApplication.class, args);
    }

    //这里配置静态资源文件的路径导包都是默认的直接导入就可以
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
//        super.addResourceHandlers(registry);
//    }

}

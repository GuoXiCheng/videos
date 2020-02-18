package com.guo.videos;

import com.guo.videos.dev.controller.interceptor.MiniInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("file:C:/guoxicheng_videos_dev/");
		registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
	}

	@Bean
	public MiniInterceptor miniInterceptor() {
		return new MiniInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(miniInterceptor()).addPathPatterns("/user/**")
//				.addPathPatterns("/video/upload","/video/uploadCover",
//						"/video/userLike","/video/userUnLike",
//						"/video/saveComment")
//				.addPathPatterns("/bgm/**")
//				.excludePathPatterns("/user/queryPublisher");
//		super.addInterceptors(registry);
	}
	

}

package com.inzent.medialibrary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/video_view/**")
                .addResourceLocations("file:///C:/Users/bit-user/Desktop/media_storage"); //리눅스 root에서 시작하는 폴더 경로
    }
}

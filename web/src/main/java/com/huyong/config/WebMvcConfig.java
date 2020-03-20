package com.huyong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述: web mvc config
 *
 * @author huyong
 * @date 2020-03-20 9:08 下午
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String url = "/Users/weidian/Documents/huyong/huyong-boke-front/springboot-mybatis-/huyong-boke-front/src/img/";
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://" + url);
    }
}

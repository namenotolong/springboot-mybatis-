package com.huyong.config;

import com.huyong.constant.CommonConstant;
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
        registry.addResourceHandler(CommonConstant.VISIT_FILE_LOCATION + "**")
                .addResourceLocations("file://" + CommonConstant.FILE_LOCATION);
    }
}

package com.banxia.music.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 * @since 2021/4/8/0008
 * 解决跨域问题
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //哪些目录可以跨域访问
        registry.addMapping("/**")
                //允许哪些来源可以访问
                .allowedOriginPatterns("*")
                //允许哪些方法可以进行访问
                .allowedMethods("*")
                //访问时是否需要验证
                .allowCredentials(true);
    }

}

package com.lly.config;

import com.lly.interceptor.CheckLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 登录拦截器
 *
 * @author Joker-lly
 * @since 2020-06-07
 */
//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    AnnotationConfigApplicationContext annotationConfigApplicationContext;

    // 注册自己的拦截器
    @Bean
    CheckLoginInterceptor checkLoginInterceptor(){
        return new CheckLoginInterceptor();
    }

    /**
     * 配置拦截文件
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/apptab/login.html");
    }

}

package com.lly.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @EnableAspectJAutoProxy(proxyTargetClass = true)
 *      proxyTargetClass true 是用的cjlb
 *      proxyTargetClass false 是jdk动态代理
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = false )// 支持aspectJ语法
@ComponentScan("com.lly")

//@EnableWebMvc
public class AppConfig {
}

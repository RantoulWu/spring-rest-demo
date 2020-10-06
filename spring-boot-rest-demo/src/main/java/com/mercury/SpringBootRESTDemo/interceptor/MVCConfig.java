package com.mercury.SpringBootRESTDemo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Autowired
    WhoAccessedInterceptor whoAccessedInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注入
        registry.addInterceptor(whoAccessedInterceptor)
                .addPathPatterns("/products", "/orders");
    }
}

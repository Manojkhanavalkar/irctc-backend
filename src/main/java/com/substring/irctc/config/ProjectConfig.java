package com.substring.irctc.config;

import com.substring.irctc.interceptors.MyCustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {
    @Autowired
    private MyCustomInterceptor myCustomInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myCustomInterceptor)
                .addPathPatterns("/trains/**","/stations/**")
                .excludePathPatterns("/trains/list");
    }
}

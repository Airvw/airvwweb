package com.airvwweb.springboot.config;

import com.airvwweb.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
//      HandlerMethodArgumentResolver은 항상 WebMvcConfigurer의 addArgumentResolvers()을 통해 추가해야 함.
        argumentResolvers.add(loginUserArgumentResolver);
    }
}

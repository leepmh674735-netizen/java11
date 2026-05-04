package com.winter.yubin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.yubin.interceptors.LoginCheckInterceptor;
import com.winter.yubin.interceptors.WriterCheckInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;
    
    @Autowired
    private WriterCheckInterceptor writerCheckInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        // 1. 로그인 체크 인터셉터
        // 로그인하지 않은 사용자는 아예 접근조차 못하게 막는 첫 번째 관문입니다.
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/member/**")
                .addPathPatterns("/qna/**")
                .excludePathPatterns("/member/login", "/member/join")
                .excludePathPatterns("/qna/list");
        
        // 2. 작성자 체크 인터셉터
        // 로그인은 했지만, '남의 글'을 수정/삭제하려는 행위를 막는 두 번째 관문입니다.
        // 수정(update), 삭제(delete) 경로에만 적용하는 것이 일반적입니다.
        registry.addInterceptor(writerCheckInterceptor)
                .addPathPatterns("/notice/update", "/notice/delete")
                .addPathPatterns("/qna/update", "/qna/delete");
    }
}
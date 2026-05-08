package com.winter.yubin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration; // annotation 오타 수정
import org.springframework.web.servlet.config.annotation.InterceptorRegistry; // Interceptor 오타 수정
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.winter.yubin.interceptors.LoginCheckInterceptor;
import com.winter.yubin.interceptors.WriterCheckInterceptor; // yubin 오타 수정

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor; // 변수명은 소문자로 시작하는 것이 관례입니다.

    @Autowired
    private WriterCheckInterceptor writerCheckInterceptor;

    @Autowired
    private LocaleChangeInterceptor localeChangeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) { // publiv -> public 수정

        // 1. 로그인 체크 인터셉터
        registry.addInterceptor(loginCheckInterceptor) // addInterceptors -> addInterceptor
                .addPathPatterns("/member/**")          // addPathPatters -> addPathPatterns
                .addPathPatterns("/qna/**")
                .excludePathPatterns("/member/login", "/member/join") // exculdePatterns -> excludePathPatterns
                .excludePathPatterns("/qna/list");      // qua -> qna 오타 수정

        // 2. 작성자 체크 인터셉터
        registry.addInterceptor(writerCheckInterceptor)
                .addPathPatterns("/notice/update", "/notice/delete")
                .addPathPatterns("/qna/update", "/qna/delete");

        // 3. 언어 설정 변경 인터셉터
        registry.addInterceptor(localeChangeInterceptor)
                .addPathPatterns("/**"); // 보통 전체 경로에 적용합니다.
    }
}

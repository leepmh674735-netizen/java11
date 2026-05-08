package com.winter.yubin.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MessageMappingConfig implements WebMvcConfigurer {

    // 1. LocaleResolver 설정: 사용자의 지역 설정을 어떻게 저장할지 결정
    @Bean
    public LocaleResolver localeResolver() {
        // Cookie 방식을 사용할 경우 (브라우저를 닫아도 설정 유지 가능)
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.KOREAN); // 기본값: 한국어
        resolver.setCookieName("lang");           // 쿠키 이름 설정
        return resolver;
        
        /* 
        // 세션 방식을 사용하고 싶다면 아래 주석을 해제하고 위 코드를 주석 처리하세요.
        SessionLocaleResolver sessionResolver = new SessionLocaleResolver();
        sessionResolver.setDefaultLocale(Locale.KOREAN);
        return sessionResolver;
        */
    }

    // 2. LocaleChangeInterceptor 설정: 파라미터로 언어를 변경할 수 있게 함
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang"); // URL 뒤에 ?lang=en 등으로 접근 시 변경됨
        return interceptor;
    }

    // 3. 인터셉터를 스프링 MVC 설정에 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
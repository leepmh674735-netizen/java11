package com.winter.yubin.config;

import org.apache.catalina.valves.JsonAccessLogValve;
import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.winter.yubin.member.MemberServiceImpl;

@Configuration
public class MessageMappingConfig implements WebMvcConfigurer{
	
	//사용자가 원하는 언어 설정을 받아서 변환 하는 클래스
	//session, Cookie
	
	@Bean
    LocalResolver localResolver {
	   
	    	//1. Session
	    	SessionLocaleResolver resolver = new SessionLocaleResolver();
	    	resolver.serDefaultLocal(Locale.KOREAN);
	    	
	    	//2.Cookie
	    	CookieLocaleResolver resolver2 = new CookieLocaleResolver();
	    	resolver2.setDefalutLocale(Locale.KOREAN);
	    	
	    	return resolver2;
	    }
	
	
	LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		return changeInterceptor;
	}
	
}



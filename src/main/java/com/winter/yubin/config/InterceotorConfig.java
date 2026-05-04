package com.winter.yubin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.yubin.interceptors.LoginCheckInterceptor;
import com.winter.yubin.interceptors.TestInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private TestInterceptor testInterceptor;
	
	@Autowired
	private LoginCheckInterceptor loginCheckInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//member로 시작하는 모든 url, login, join
		//qna list를 제외한 나머지들은 회원만 사용 가능
		registry.addInterceptor(loginCheckInterceptor)
				.addPathPatterns("/member/*")
				.addPathPatterns("/qna/*")
				.excludePathPatterns("/member/login", "/member/join")
				.excludePathPatterns("/qna/list")
				;
		
		
		//적용할 Interceptor등로
		registry.addInterceptor(testInterceptor)
		
		//Interceptor를 사용할 URL 패턴 작성, addPathPatterns 여러번 호출 가능
				.addPathPatterns("/notice/*", "/qna/*")
		//Interceptor를 제외할 URL 패턴 작성, excludePathPatterns 여러번 호출 가능		
				.excludePathPatterns("/notice/detail")
				;
		
	
		
				
		
	}
	
	

}
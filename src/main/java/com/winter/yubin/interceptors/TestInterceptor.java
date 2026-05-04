package com.winter.yubin.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestInterceptor implements HandlerInterceptor {
	
    // 1. Controller 진입 전 실행
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // return true: 컨트롤러로 진행
        // return false: 컨트롤러 진입을 차단 (응답 종료)
        log.info("================ [Step 1] Pre Handle: 컨트롤러 진입 전 ================");
        return true;
    }
	
    // 2. Controller 로직 수행 후, View 렌더링 전 실행
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        log.info("================ [Step 2] Post Handle: 컨트롤러 작업 완료 ================");
        // 여기서 modelAndView를 조작하여 공통 데이터를 추가할 수 있습니다.
    }
	
    // 3. View(JSP, Thymeleaf 등)가 HTML로 렌더링이 완료된 후 실행
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("================ [Step 3] After Completion: 응답 프로세스 완료 ================");
        // 에러 로그를 남기거나 리소스 정리 시 사용합니다.
    }
}
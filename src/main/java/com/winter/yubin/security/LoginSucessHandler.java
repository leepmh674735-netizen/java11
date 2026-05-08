package com.winter.yubin.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie; // Cookie 임포트 필요
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginSucessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        try {
            // 1. 로그인 성공 로그 출력 (포맷 문구 수정)
            log.info("Login Success: {}", authentication.getName());

            // 2. 쿠키 생성 (기억하기 아이디 등)
            // request.getParameter("remember") 등으로 체크박스 확인 후 로직을 넣기도 합니다.
            Cookie cookie = new Cookie("rememberId", authentication.getName());
            cookie.setMaxAge(60 * 60 * 24); // 예: 24시간 유지 (초 단위)
            cookie.setPath("/"); // 모든 경로에서 쿠키 유효
            response.addCookie(cookie);

            // 3. 성공 후 페이지 리다이렉트
            response.sendRedirect("/");

        } catch (Exception e) {
            log.error("LoginSuccessHandler 에러: {}", e.getMessage());
            response.sendRedirect("/login?error"); // 에러 발생 시 로그인 페이지로 이동
        }
    }
}
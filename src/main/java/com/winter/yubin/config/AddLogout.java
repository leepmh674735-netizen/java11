package com.winter.yubin.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AddLogout implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        
        // 로그아웃 시 추가로 실행하고 싶은 로직을 작성합니다.
        if (authentication != null) {
            log.info("사용자 {} 가 로그아웃을 요청했습니다.", authentication.getName());
        }

        // 예: 특정 세션 속성 제거 또는 외부 서비스 로그아웃 처리 등
        log.info("커스텀 로그아웃 핸들러 작동 완료");
    }
}
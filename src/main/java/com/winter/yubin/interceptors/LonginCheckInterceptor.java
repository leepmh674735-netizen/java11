package com.winter.yubin.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component 
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        HttpSession session = request.getSession();
        package com.winter.yubin.interceptors;

        import org.springframework.stereotype.Component;
        import org.springframework.web.servlet.HandlerInterceptor;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;
        import jakarta.servlet.http.HttpSession;

        @Component 
        public class LoginCheckInterceptor implements HandlerInterceptor {

            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                    throws Exception {
                
                // 1. 세션에서 로그인 정보("member") 확인
                HttpSession session = request.getSession();
                Object obj = session.getAttribute("member");

                // 2. 로그인 상태라면 컨트롤러 진입 허용
                if (obj != null) {
                    return true;
                }

                // 3. 비로그인 상태라면 로그인 페이지로 리다이렉트
                // getContextPath()를 사용하여 절대 경로를 유지하는 것이 가장 안전합니다.
                response.sendRedirect(request.getContextPath() + "/member/login");
                
                // 4. 컨트롤러 진입 차단
                return false;
            }
        }      
        Object obj = session.getAttribute("member");

        if (obj != null) {
            return true;
        }

        response.sendRedirect(request.getContextPath() + "/member/login");
        
        return false;
    }
}
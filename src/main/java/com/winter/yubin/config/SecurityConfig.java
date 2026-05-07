package com.winter.yubin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. 정적 리소스 보안 필터 제외 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers("/css/**", "/images/**", "/js/**", "/vendor/**", "/files/**", "/fileDown/**", "/favicon.ico");
    }

    // 2. 인증 및 인가 상세 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (개발 및 학습 단계용)
            
            .authorizeHttpRequests(auth -> auth
                // 공지사항 관리 권한 설정
                .requestMatchers("/notice/create", "/notice/update", "/notice/delete").hasRole("ADMIN")
                // 그 외 모든 요청은 허용
                .anyRequest().permitAll()
            )
            
            // 3. 로그인 설정
            .formLogin(form -> form
                .loginPage("/member/login")           // 커스텀 로그인 페이지 경로
                .loginProcessingUrl("/member/login")    // 폼의 action 경로
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)          // 로그인 성공 후 메인으로 이동
                .permitAll()
            )
            
            // 4. 로그아웃 설정
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) // 로그아웃 경로 설정
                .logoutSuccessUrl("/")                // 로그아웃 후 이동할 경로
                .invalidateHttpSession(true)          // 세션 무효화
                .deleteCookies("JSESSIONID")          // 쿠키 삭제
            );

        return http.build(); // 변수명을 파라미터인 'http'와 맞춤
    }

    // 5. 비밀번호 암호화 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
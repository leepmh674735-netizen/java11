package com.winter.yubin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
            .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (개발 단계)
            
            .authorizeHttpRequests(auth -> auth
                // 공지사항 관리 권한 설정
                .requestMatchers("/notice/create", "/notice/update", "/notice/delete").hasRole("ADMIN")
                // 그 외 모든 요청은 허용
                .anyRequest().permitAll()
            )
            
            // 3. 로그인 설정 (끊겼던 부분 완성)
            .formLogin(form -> form
                .loginPage("/member/login")       // 커스텀 로그인 페이지 경로
                .loginProcessingUrl("/member/login") // 폼의 action 경로 (Security가 낚아챔)
                .usernameParameter("username")    // MemberDTO의 username 필드와 매칭
                .passwordParameter("password")    // MemberDTO의 password 필드와 매칭
                .defaultSuccessUrl("/")           // 로그인 성공 후 이동할 메인 페이지
                .permitAll()
            )
            
            // 4. 로그아웃 설정 (추가 권장)
            .logout(logout -> logout
                .logoutUrl("/member/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true) // 세션 무효화
            );

        return http.build();
    }

    // 5. 비밀번호 암호화 빈 등록 (로그인 시 필수)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
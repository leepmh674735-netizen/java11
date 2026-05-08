package com.winter.yubin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.winter.yubin.security.LoginSucessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private LoginSucessHandler loginSucessHandler;

    @Autowired
    private AddLogout addLogout;

    // rememberMe 기능을 위해 반드시 필요합니다.
    @Autowired
    private UserDetailsService memberService; 

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers("/css/**", "/images/**", "/js/**", "/vendor/**", "/files/**", "/fileDown/**", "/favicon.ico");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. CSRF (운영 환경에서는 가급적 enable 권장)
            .csrf(csrf -> csrf.disable())
            
            // 2. 권한 설정
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/notice/create", "/notice/update", "/notice/delete").hasRole("ADMIN")
                .anyRequest().permitAll()
            )
            
            // 3. 로그인 설정
            .formLogin(form -> form
                .loginPage("/member/login")
                .loginProcessingUrl("/member/login")
                .successHandler(loginSucessHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
            )
            
            // 4. 로그아웃 설정
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .addLogoutHandler(addLogout)
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me") // 쿠키 이름 확인 필요
            )

            // 5. Remember Me 설정
            .rememberMe(remember -> remember
                .rememberMeParameter("remember")
                .tokenValiditySeconds(60 * 60 * 24) // 24시간
                .key("uniqueAndSecretKey")
                .userDetailsService(memberService) // null 대신 실제 서비스 주입
                .authenticationSuccessHandler(loginSucessHandler)
            )

            // 6. 세션 관리
            .sessionManagement(session -> session
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/member/login?expired=true")
            );

        // return security.build(); -> 오타 수정
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    HttpSessionEventPublisher httpSessionPublisher() {
    	return new HttpSession
    }
}
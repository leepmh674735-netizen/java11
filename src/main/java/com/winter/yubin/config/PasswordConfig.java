package com.winter.yubin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt 알고리즘을 사용하여 비밀번호를 해시화하는 빈(Bean) 등록
        return new BCryptPasswordEncoder();
    }
}
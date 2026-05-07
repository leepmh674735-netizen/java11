package com.winter.yubin;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j // 로그를 확인하기 위해 추가
public class MemberTest {
    
    @Autowired
    private PasswordEncoder passwordEncoder; // 오타 수정 (passoword -> password)
    
    @Test
    void setPassword() throws Exception {
        // 1. 암호화할 원본 비밀번호 (평문)
        String rawPassword = "myPassword123!";
        
        // 2. 암호화 진행
        String encodedPassword = passwordEncoder.encode(rawPassword); // 변수명 오타 수정
        
        // 3. 로그 출력 (콘솔에서 암호화된 결과 확인)
        log.info("원본 비밀번호: {}", rawPassword);
        log.info("암호화된 비밀번호: {}", encodedPassword);
        
        // 4. 검증 (JUnit Assertion)
        assertNotNull(encodedPassword); // 결과가 null이 아니어야 함
        assertNotEquals(rawPassword, encodedPassword); // 원본과 암호문은 달라야 함
    }
}
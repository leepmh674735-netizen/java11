package com.winter.yubin.rest;

import org.junit.jupiter.api.Test; // JUnit 5 사용
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // 스프링 부트 컨테이너를 로드하여 빈(Bean) 주입을 가능하게 함
public class RestTemplateTest {
    
    @Autowired
    private RestTest restTest;
    
    @Test
    void detailTest() {
        try {
            // 이전에 만든 restTest의 detail() 메서드 호출
            restTest.detail();
            
            // 테스트가 에러 없이 끝까지 실행되면 성공으로 간주
            System.out.println("테스트 성공: 외부 API 호출에 성공했습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            // 테스트 실패 처리 (JUnit의 에러 발생 방식)
            org.junit.jupiter.api.Assertions.fail("API 호출 중 예외가 발생했습니다: " + e.getMessage());
        }
    }
}
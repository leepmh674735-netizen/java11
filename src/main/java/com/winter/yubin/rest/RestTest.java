package com.winter.yubin.rest;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j 
public class RestTest {
    
    /**
     * 단일 포스트 상세 조회
     */
    public void detail() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        
        // getForObject(URL, 반환타입)
        String response = restTemplate.getForObject(url, String.class);
        
        log.info("단일 조회 결과: {}", response);
    }
    
    /**
     * 포스트 목록 전체 조회
     */
    public void list() throws Exception {
        // 2. 인스턴스 생성
        RestTemplate restTemplate = new RestTemplate();
        
        // 3. URL 문자열 처리 
        String url = "https://jsonplaceholder.typicode.com/posts";
        
        // 4. 호출 방식 수정: RestTemplate.getForObject(...)는 정적 메서드가 아닙니다.
        // 생성한 인스턴스(restTemplate)를 통해 호출해야 하며, 응답 클래스 타입을 명시해야 합니다.
        String response = restTemplate.getForObject(url, String.class);
        
        log.info("목록 조회 결과: {}", response);
    }
}
package com.winter.yubin.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // @Scheduled 기능을 활성화합니다.
public class AppApplication {
    
    public static void main(String[] args) {
        // 1. 스프링 애플리케이션을 실행하는 핵심 코드입니다.
        SpringApplication.run(AppApplication.class, args);
        
        // 2. 실행 확인을 위한 로그 (선택 사항)
        System.out.println("=== 스케줄러 애플리케이션이 시작되었습니다! ===");
    }

}
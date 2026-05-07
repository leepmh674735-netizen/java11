package com.winter.yubin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication // 스프링 부트의 자동 설정, 컴포넌트 스캔 등을 활성화함
//@EnableScheduling      // @Scheduled 스케줄러 기능을 사용하기 위해 필요
public class KosmoPost1Application {

    public static void main(String[] args) {
        // 애플리케이션을 구동함
        SpringApplication.run(KosmoPost1Application.class, args);
    }

}
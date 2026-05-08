package com.winter.yubin.schedule;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling // 1. 스케줄링 기능 활성화
@Component        // 2. 스프링 빈으로 등록
public class TestSchedule {
	
    // 3. 고정 주기 실행 (작업 시작 시점 기준)
    @Scheduled(fixedRateString = "2000", initialDelay = 1000)
    public void useFixRate() throws Exception {
        System.out.println("고정 간격(Rate)으로 반복 할 코드");
    } 
	
    // 4. 고정 지연 실행 (작업 종료 시점 기준)
    @Scheduled(fixedDelay = 2000, initialDelayString = "2000")
    public void userFixDelay() throws Exception {
        System.out.println("고정 지연(Delay)으로 반복 코드");
    }
}
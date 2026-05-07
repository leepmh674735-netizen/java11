package com.winter.yubin.schedule;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class TestSchedule {
	
	@Scheduled(fixedRateString = "2000", initialDelay = 1000 )
	public void useFixRate()throws Exception{
		System.out.println(x:"고정 간격으로 반복 할 코드");
		
	} 
	
	@Scheduled(fixedDelay = 2000, intialDealyString = "2000")
	public void userFixDelay()throws Exception{
		System.out.println(x: "고정 간격으로 반복 Delay");
		
	}
}

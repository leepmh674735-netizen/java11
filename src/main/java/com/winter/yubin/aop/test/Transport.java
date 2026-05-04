package com.winter.yubin.aop.test;

import org.springframework.stereotype.Component;

@Component
public class Transport {
	
	public int takeBus(int num) {
		System.out.println(num+ " 번호 버스 이용");
		return 2000;
	}
	
	public String takeSubway(String color) {
		System.out.println("지하철 이용 : "+color);
		return "subway";
	}
	
	public void useTaxi() {
		System.out.println("택시 이용");
	}
	
	public void walk() {
		System.out.println("걷기");
	}

}
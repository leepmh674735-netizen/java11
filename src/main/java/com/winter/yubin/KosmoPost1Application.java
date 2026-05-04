package com.winter.yubin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class KosmoPost1Application {

	public static void main(String[] args) {
		SpringApplication.run(KosmoPost1Application.class, args);
	}

}

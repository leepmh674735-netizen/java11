package com.winter.yubin.account;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountDTO {
	
	private String accountNum;   //계좌번호
	private String username;     //사용자 ID
	private Long productNum;     //상품 번호
	private Long accountPw;      //계좌 비밀번호
	private Long accountBalance; //계좌잔액
	private LocalDate accountDate; //계좌 개설일

}
package com.winter.yubin.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	
	@Autowired
	private AccountMapper accountMapper;
	
	
	public int create(AccountDTO accountDTO)throws Exception{
		long time = System.currentTimeMillis();
		
		accountDTO.setAccountNum(String.valueOf(time));
		
		int result = accountMapper.create(accountDTO);
		
		return result;
	}
	
	

}
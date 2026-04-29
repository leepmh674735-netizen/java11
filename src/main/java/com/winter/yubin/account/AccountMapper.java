package com.winter.yubin.account;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
	
	public int create(AccountDTO accountDTO)throws Exception;

}
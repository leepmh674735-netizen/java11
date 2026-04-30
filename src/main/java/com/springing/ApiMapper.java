package com.springing;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiMapper {
	
	public int setApiData(ApiDTO apiDTO) throws Exception;
	}

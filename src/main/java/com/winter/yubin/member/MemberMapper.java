package com.winter.yubin.member;

import org.apache.ibatis.annotations.Mapper;

import com.winter.yubin.product.ProductFileDTO;

@Mapper
public interface MemberMapper {
	
	public int join(MemberDTO memberDTO)throws Exception;
	
	public int addProfile(ProductFileDTO profileDTO)throws Exception;
	
	public MemberDTO detail(MemberDTO memberDTO)throws Exception;
}

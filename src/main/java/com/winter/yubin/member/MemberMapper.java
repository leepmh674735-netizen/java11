package com.winter.yubin.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	public int update(MemberDTO memberDTO)throws Exception;

	public int join(MemberDTO memberDTO)throws Exception;
	
	public int addProfile(ProfileDTO profileDTO)throws Exception;
	
	public MemberDTO detail(MemberDTO memberDTO)throws Exception;
}
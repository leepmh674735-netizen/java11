package com.winter.yubin.member;

import org.springframework.web.multipart.MultipartFile;

public interface MemberService {
	
	public int join(MemberDTO memberDTO,MultipartFile file)throws Expetion;
	
	public MemberDTO detail(MemberDTO memberDTO)throws Exception;
	
	public MemberDTO idCheck(MemberDTO memberDTO)throws Exception;
	}

package com.winter.yubin.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
    // 회원 정보 수정
    public int update(MemberDTO memberDTO) throws Exception;

    // 회원 가입 (기본 정보)
    public int join(MemberDTO memberDTO) throws Exception;
	
    // 프로필 이미지 정보 저장
    public int addProfile(ProfileDTO profileDTO) throws Exception;
	
    // 회원 상세 정보 조회 (로그인, 중복체크 등)
    // UsernameNotFoundException 대신 일반 Exception을 던지거나 생략하는 것이 관례입니다.
    public MemberDTO detail(MemberDTO memberDTO) throws Exception;
    
}

package com.winter.yubin.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.yubin.member.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/account/*")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("create")
	public void create() throws Exception {
		// return type이 void이면 mapping 경로인 /account/create.jsp를 찾습니다.
	}
	
	@PostMapping("create")
	public String create(AccountDTO accountDTO, HttpSession session) throws Exception {
		// 1. 세션에서 로그인 정보 가져오기
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		
		// 2. 로그인 여부 확인 (로그아웃 상태 시 로그인 페이지로 리다이렉트)
		// 로그인 여부확인 (예외처리)
		if (memberDTO == null) {
			return "redirect:/member/login"; 
		}
		
		// 3. 현재 로그인한 사용자의 ID를 계정 정보에 설정
		//사용자ID연결
		accountDTO.setUsername(memberDTO.getUsername());
		
		// 4. 비즈니스 로직 실행 DB 저장
		int result = accountService.create(accountDTO);
		
		// 5. 성공 후 메인 페이지 또는 계좌 목록 등으로 이동
		return "redirect:/"; //성공 후 리다이렉트
	}
}
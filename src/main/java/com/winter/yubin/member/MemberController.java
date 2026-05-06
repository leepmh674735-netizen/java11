package com.winter.yubin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("mypage")
	public void mypage()throws Exception{};
	
	@GetMapping("update")
	public void update(HttpSession session, Model model)throws Exception{
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		log.info("{}", memberDTO);
		model.addAttribute("memberDTO", memberDTO);
	}
	
	@PostMapping("update")
	public String update(@Validated(GroupUpdate.class) MemberDTO memberDTO, BindingResult bindingResult , HttpSession session, Model model)throws Exception{
		
		if(bindingResult.hasErrors()) {
			return "member/update";
		}
		
		MemberDTO s = (MemberDTO)session.getAttribute("member");
		memberDTO.setUsername(s.getUsername());
		
		int result = memberService.update(memberDTO);
		if(result>0) {
			s = memberService.detail(s);
			session.setAttribute("member", s);
		}
		
		return "redirect:/";
		
	}
	
	
	
	@GetMapping("idCheck")
	public String idCheck(MemberDTO memberDTO, Model model)throws Exception{
		memberDTO = memberService.idCheck(memberDTO);
		int result=0;
		if(memberDTO == null) {
			result=1;
		}
		
		model.addAttribute("result", result);
		
		return "commons/ajaxResult";
	}
	
	@GetMapping("join")
	public void join()throws Exception{}
	
	@PostMapping("join")
	public String join(@Validated(GroupAdd.class) MemberDTO memberDTO,BindingResult bindingResult ,@RequestParam("attach") MultipartFile attach)throws Exception{
		
		if(memberService.doubleCheck(memberDTO, bindingResult)) {
			return "member/join";
		}
		
		
		//int result = memberService.join(memberDTO, attach);
		
		return "redirect:/";
		
	}
	
	@GetMapping("login")
	public void login(@ModelAttribute MemberDTO memberDTO)throws Exception{
		
	}

	@PostMapping("login")
	public String login(MemberDTO memberDTO, HttpSession session)throws Exception{
		
		memberDTO = memberService.detail(memberDTO);
		
		if(memberDTO != null) {
			session.setAttribute("member", memberDTO);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:/";
	}

}
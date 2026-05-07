package com.winter.yubin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    // 1. 마이페이지
    @GetMapping("mypage")
    public void mypage() throws Exception {
        log.info("MyPage 접속");
        // Security Context에 인증 정보가 있다면, 
        // 뷰(Thymeleaf 등)에서 sec:authentication으로 직접 접근 가능하므로 별도 Model 작업 생략 가능
    }
    
    // 2. 회원정보 수정 폼
    @GetMapping("update")
    public void update(HttpSession session, Model model) throws Exception {
        // 세션에서 회원 정보를 가져옴
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        model.addAttribute("memberDTO", memberDTO);
    }
    
    // 3. 회원정보 수정 처리
    @PostMapping("update")
    public String update(@Validated(GroupUpdate.class) MemberDTO memberDTO, 
                         BindingResult bindingResult, 
                         HttpSession session) throws Exception {
        
        if(bindingResult.hasErrors()) {
            log.warn("회원수정 유효성 검사 실패 : {}", bindingResult.getAllErrors());
            return "member/update";
        }
        
        // 보안상 세션의 Username 강제 세팅
        MemberDTO sessionMember = (MemberDTO) session.getAttribute("member");
        memberDTO.setUsername(sessionMember.getUsername());
        
        int result = memberService.update(memberDTO);
        
        if(result > 0) {
            // 수정 성공 시 최신 정보로 세션 갱신
            sessionMember = memberService.detail(memberDTO);
            session.setAttribute("member", sessionMember);
        }
        
        return "redirect:/member/mypage";
    }
    
    // 4. 아이디 중복 체크 (AJAX)
    @GetMapping("idCheck")
    public String idCheck(MemberDTO memberDTO, Model model) throws Exception {
        memberDTO = memberService.idCheck(memberDTO);
        
        // null이면 중복 없음(1), 아니면 중복(0)
        int result = (memberDTO == null) ? 1 : 0;
        
        model.addAttribute("result", result); // 문법 수정: attributeName 제거
        return "commons/ajaxResult";
    }
    
    // 5. 회원가입 폼
    @GetMapping("join")
    public void join(MemberDTO memberDTO) throws Exception {
        // 폼 바인딩용 빈 객체
    }
    
    // 6. 회원가입 처리
    @PostMapping("join")
    public String join(@Validated(GroupAdd.class) MemberDTO memberDTO, 
                       BindingResult bindingResult, 
                       @RequestParam(value = "attach", required = false) MultipartFile attach) throws Exception {
        
        // Validation 에러 및 서비스 단 중복 체크 검사
        if(bindingResult.hasErrors() || memberService.doubleCheck(memberDTO, bindingResult)) {
            log.warn("회원가입 실패: {}", bindingResult.getAllErrors());
            return "member/join";
        }
        
        int result = memberService.join(memberDTO, attach);
        log.info("회원가입 성공 여부: {}", result);
        
        return "redirect:/";
    }
    
    // 7. 로그인 폼
    @GetMapping("login")
    public void login() throws Exception {
        // Spring Security 설정을 따름
    }
}
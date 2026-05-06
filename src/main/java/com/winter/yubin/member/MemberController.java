package com.winter.yubinS.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/member/*")
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    // 1. 아이디 중복 체크 (삼항 연산자로 간소화)
    @GetMapping("idCheck")
    public String idCheck(MemberDTO memberDTO, Model model) throws Exception {
        memberDTO = memberService.idCheck(memberDTO);
        // memberDTO가 null이면 사용 가능(1), 아니면 중복(0)
        model.addAttribute("result", (memberDTO == null) ? 1 : 0);
        return "commons/ajaxResult";
    }
    
    @GetMapping("join")
    public void join(@ModelAttribute MemberDTO memberDTO) throws Exception {
        // @ModelAttribute를 추가하면 view에서 th:object 등으로 접근하기 좋습니다.
    }
    
    @PostMapping("join")
    public String join(@Valid MemberDTO memberDTO, BindingResult bindingResult, 
                       @RequestParam("attach") MultipartFile attach) throws Exception {
        
        if (bindingResult.hasErrors()) {
            return "member/join";
        }
        
        int result = memberService.join(memberDTO, attach);
        return "redirect:/";
    }
    
    @GetMapping("login")
    public void login(@ModelAttribute MemberDTO memberDTO) throws Exception {
    }

    @PostMapping("login")
    public String login(@Valid MemberDTO memberDTO, BindingResult bindingResult, 
                        HttpSession session, Model model) throws Exception {
        
        // 1. 입력값 검증 실패 시 (아이디 미입력 등)
        if (bindingResult.hasErrors()) {
            return "member/login";
        }
        
        // 2. 서비스 레이어에서 로그인 로직 처리 (ID/PW 일치 확인)
        MemberDTO loginResult = memberService.login(memberDTO);
        
        if (loginResult != null) {
            session.setAttribute("member", loginResult);
            return "redirect:/";
        } else {
            // 3. 로그인 실패 시 메시지 전달
            model.addAttribute("loginError", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "member/login";
        }
    }
    
    @GetMapping("logout")
    public String logout(HttpSession session) throws Exception {
        session.invalidate(); // 세션 무효화
        return "redirect:/";
    }
}
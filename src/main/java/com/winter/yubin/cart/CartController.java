package com.winter.yubin.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.yubin.member.MemberDTO;
import com.winter.yubin.product.ProductDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart/*")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	// 1. 장바구니 리스트 조회
	@GetMapping("list")
	public void list()throws Exception{}
	
	@GetMapping("cartlist")
	public void list(HttpSession session, Model model)throws Exception {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		// 로그인 체크 로직 추가 권장 (memberDTO != null)
		List<ProductDTO> ar = cartService.list(memberDTO);
		model.addAttribute("list", ar);
	}
	
	// 2. 장바구니 상품 담기 (Ajax 처리용)
	@PostMapping("create")
	public String create(HttpSession session, CartDTO cartDTO, Model model) throws Exception {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		cartDTO.setUsername(memberDTO.getUsername());
		
		int result = cartService.create(cartDTO);
		model.addAttribute("result", result);
		
		return "commons/ajaxResult";
	}
	
	// 3. 장바구니 선택 삭제 (수정된 부분)
	@PostMapping("delete")
	public String delete(HttpSession session, Long[] productNum, Model model) throws Exception {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		// 여러 개의 상품 번호와 사용자 ID를 묶어서 처리하기 위해 List 생성
		List<CartDTO> ar = new ArrayList<>();
		
		for(Long 1 : productNum) {
			CartDTO cartDTO = new CartDTO();
			cartDTO.setProductNum(1);
			cartDTO.setUsername(memberDTO.getUsername());
			ar.add(cartDTO);
		}
		
		// 서비스에 리스트를 전달하여 일괄 삭제 요청
		int result = cartService.delete(ar);
		
		model.addAttribute("result", result);
		return "commons/ajaxResult";
	}
}
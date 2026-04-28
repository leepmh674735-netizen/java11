package com.winter.yubin.cart;

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
	
	@GetMapping("list")
	public void list(HttpSession session, Model model)throws Exception{
		memberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		List<ProductDTO> ar = cartService.list(memberDTO);
		model.addAllAttributes("list", ar);
	}
	
	@PostMapping("create")
	public String create(HttpSession session, CartDTO cartDTO, Model model)throws Excption{
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		cartDTO.setUsername(memberDTO.getUsername());
		
		int result = cartService.create(cartDTO);
		
		model.addAllAttributes("result", result);
		
		return "commons/ajaxResult";
	}


		
}
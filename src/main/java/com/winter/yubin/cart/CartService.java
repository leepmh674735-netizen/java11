package com.winter.yubin.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.member.MemberDTO;
import com.winter.app.product.ProductDTO;

@Service
public class CartService {
	
	@Autowired
	private CartMapper cartMapper;
	
	public List<ProductDTO> list(MemberDTO memberDTO)throws Exception{
		return cartMapper.list(memberDTO);
	}
	
	public int create(CartDTO cartDTO)throws Exception{
		return cartMapper.create(cartDTO);
	}

}

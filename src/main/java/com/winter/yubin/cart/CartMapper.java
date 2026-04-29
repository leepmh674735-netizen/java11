package com.winter.yubin.cart;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.yubin.member.MemberDTO;
import com.winter.yubin.productDTO;

@Mapper
public interface CartMapper {
	
	public int delete(CartDTO cartDTO)
	
	public int create(CartDTO cartDTO)throws Excption;
	
	public List<ProductDTO> list (MemberDTO memberDTO)throws Exception;
	
}
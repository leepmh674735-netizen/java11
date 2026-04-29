package com.winter.yubin.cart;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.winter.yubin.member.MemberDTO;
import com.winter.yubin.product.ProductDTO; // 패키지 경로 확인 필요

@Mapper
public interface CartMapper {
    
    // 장바구니 삭제
    public int delete(CartDTO cartDTO) throws Exception;
    
    // 장바구니 추가
    public int create(CartDTO cartDTO) throws Exception;
    
    // 특정 회원의 장바구니 목록 조회
    public List<ProductDTO> list(MemberDTO memberDTO) throws Exception;
}
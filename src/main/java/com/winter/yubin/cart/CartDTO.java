package com.winter.yubin.cart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDTO {
	
	private Long cartId;    // 장바구니 번호
    private String userId;  // 회원 아이디
    private Long productId; // 상품 번호
    private Integer amount; // 수량
}
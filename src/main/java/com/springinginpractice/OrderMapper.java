package com.springinginpractice;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    // 1. 주문 생성
    void insertOrder(OrderRequestDTO orderRequestDTO);
    
    // 2. 재고 확인
    int getStockQuantity(Long productId);
    
    // 3. 재고 차감 (성공 시 1 반환, 실패 시 0)
    int updateStock(Long productId, int quantity);
}
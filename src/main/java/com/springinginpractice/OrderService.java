package com.springinginpractice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    @Transactional
    public void placeOrder(OrderRequestDTO orderRequest) {
        // 1. 재고 충분 여부 확인
        int currentStock = orderMapper.getStockQuantity(orderRequest.getProductId());
        if (currentStock < orderRequest.getQuantity()) {
            throw new RuntimeException("재고가 부족합니다.");
        }

        // 2. 재고 차감 실행
        int updatedRows = orderMapper.updateStock(orderRequest.getProductId(), orderRequest.getQuantity());
        if (updatedRows == 0) {
            // 동시성 문제 등으로 업데이트 실패 시
            throw new RuntimeException("주문 처리 중 오류가 발생했습니다.");
        }

        // 3. 주문 내역 생성
        orderMapper.insertOrder(orderRequest);
    }
}
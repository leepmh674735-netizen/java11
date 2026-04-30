package com.springinginpractice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        try {
            orderService.placeOrder(orderRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("주문이 성공적으로 완료되었습니다.");
        } catch (RuntimeException e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

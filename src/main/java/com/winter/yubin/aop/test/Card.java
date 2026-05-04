package com.winter.yubin.aop.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Card {

    /**
     * @Around: 대상 메서드의 실행 전(Before), 후(After), 예외 발생(AfterThrowing) 시점을 모두 제어
     * Pointcut: com.winter.yubin.aop.test 패키지 내 Transport 클래스의 take로 시작하는 모든 메서드
     */
    @Around("execution(* com.winter.yubin.aop.test.Transport.take*(..))")
    public Object cardCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        
        // [Before] 공통 로직: 승차 처리
        log.info("--- [START] 승차: 카드 체크 ---");
        long startTime = System.currentTimeMillis(); // 실행 시간 측정 시작
        
        // 타겟 메서드의 매개변수 확인
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            log.info("전달 인자(Parameter): {}", arg);
        }

        Object result = null;
        try {
            // [Proceed] 실제 핵심 비즈니스 로직 실행 (예: takeBus, takeSubway 호출)
            result = proceedingJoinPoint.proceed();
            
            // [AfterReturning] 성공적으로 반환되었을 때 실행
            log.info("정상 처리 결과: {}", result);
            
        } catch (Exception e) {
            // [AfterThrowing] 예외 발생 시 로직 (예: 카드 잔액 부족 등)
            log.error("에러 발생: {}", e.getMessage());
            throw e; // 예외를 다시 던져서 서비스단에서 처리하게 함
        } finally {
            // [After] 공통 로직: 하차 처리 (성공/실패 무관 실행)
            long endTime = System.currentTimeMillis();
            log.info("총 소요 시간: {}ms", (endTime - startTime));
            log.info("--- [END] 하차: 카드 체크 ---");
        }

        return result; 
    }
}
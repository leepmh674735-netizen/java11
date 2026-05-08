package com.springinpractice.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 모든 DAO의 공통 기능을 정의한 Generic DAO 인터페이스입니다.
 * @param <T> 엔티티 타입
 */
public interface Dao<T extends Object> {

    // 데이터 저장
    void create(T t);

    // 단일 항목 조회
    T get(Serializable id);

    // 1. 오타 수정: getAll 뒤에 메서드 괄호() 추가
    List<T> getAll();

    // 2. 오타 수정: delte -> delete
    void delete(T t);

    // ID 기반 삭제
    void deleteById(Serializable id);

    // 전체 삭제
    void deleteAll();

    // 데이터 총 개수 반환
    long count();

    // 존재 여부 확인
    boolean exists(Serializable id);
}

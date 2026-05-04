package com.springing;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winter.yubin.review.ReviewDTO;
import com.winter.yubin.review.ReviewMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class) // AOP 라이브러리를 통한 트랜잭션 관리
public class ApiService {

    @Autowired
    private ReviewMapper reviewMapper;

    /**
     * 실시간 데이터 호출 로직 (ApiController에서 사용)
     */
    public List<ApiDTO> getRealTimeData() throws Exception {
        log.info("실시간 데이터 호출 시작");
        
        // 실제로는 외부 API(RestTemplates 등)를 호출하는 로직이 들어갑니다.
        // 여기서는 예시로 null 혹은 빈 리스트를 반환하거나 특정 로직을 수행합니다.
        List<ApiDTO> externalData = null; 
        
        // 외부 통신 로직 추가 필요...
        
        return externalData;
    }

    /**
     * 리뷰 목록 조회
     */
    @Transactional(readOnly = true)
    public List<ReviewDTO> getReviewList(ReviewDTO reviewDTO) throws Exception {
        return reviewMapper.getList();
    }

    /**
     * 리뷰 작성
     */
    public int setReviewCreate(ReviewDTO reviewDTO) throws Exception {
        // 비즈니스 로직: 내용이 비어있는지 확인 등
        if(reviewDTO.getContent() == null || reviewDTO.getContent().trim().isEmpty()) {
            throw new Exception("내용이 없습니다.");
        }
        return reviewMapper.setCreate(reviewDTO);
    }

    /**
     * 리뷰 수정
     */
    public int setReviewUpdate(ReviewDTO reviewDTO) throws Exception {
        return reviewMapper.setUpdate(reviewDTO);
    }

    /**
     * 리뷰 삭제
     */
    public int setReviewDelete(ReviewDTO reviewDTO) throws Exception {
        return reviewMapper.setDelete(reviewDTO);
    }
}
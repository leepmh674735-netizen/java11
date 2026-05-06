package com.winter.yubin;

import com.winter.yubin.review.ReviewDTO;
import com.winter.yubin.review.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional // 1. 테스트 완료 후 DB 데이터를 자동으로 롤백함
class KosmoPostApplicationTests {

    @Autowired
    private ReviewService reviewService;

    @Test
    @DisplayName("리뷰 등록 서비스 테스트") // 2. 테스트 결과창에 표시될 이름 설정
    void testReviewService() throws Exception {
        // [Given] 테스트 데이터 준비
        ReviewDTO dto = new ReviewDTO();
        dto.setBookNum(1L); // bno 대신 실제 DTO 필드명에 맞춰 수정 (예: bookNum)
        dto.setContents("테스트 리뷰 내용입니다."); // 필드명 확인 (content -> contents)
        dto.setWriter("yubin");

        // [When] 실제 로직 실행
        int result = reviewService.setAdd(dto); // 서비스 메서드명 확인 (register -> setAdd)

        // [Then] 결과 검증
        // 반환값이 성공한 행의 개수(int)라면 1과 같은지 비교
        assertThat(result).isEqualTo(1);
    }
}
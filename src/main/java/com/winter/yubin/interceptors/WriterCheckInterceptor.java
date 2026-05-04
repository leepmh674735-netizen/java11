package com.winter.yubin.interceptors;

import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.winter.yubin.board.BoardDTO;
import com.winter.yubin.member.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WriterCheckInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
        // 1. GET 방식이 아니거나 modelAndView가 없는 경우 처리하지 않음
        if (modelAndView == null || request.getMethod().equalsIgnoreCase("POST")) {
            return;
        }

        // 2. 세션에서 로그인 정보 가져오기
        HttpSession session = request.getSession();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");

        // 3. 컨트롤러가 모델에 담은 게시글 데이터 가져오기
        Map<String, Object> model = modelAndView.getModel();
        BoardDTO boardDTO = (BoardDTO) model.get("dto");

        log.info("권한 체크 - 로그인 유저: {}, 작성자: {}", 
                 (memberDTO != null ? memberDTO.getUsername() : "비로그인"), 
                 (boardDTO != null ? boardDTO.getBoardWriter() : "데이터없음"));

        // 4. 권한 검증 로직 (방어 코드 포함)
        boolean isOwner = false;
        if (memberDTO != null && boardDTO != null) {
            isOwner = memberDTO.getUsername().equals(boardDTO.getBoardWriter());
        }

        // 5. 작성자가 아닐 경우 결과 페이지로 강제 이동
        if (!isOwner) {
            modelAndView.setViewName("commons/result");
            modelAndView.addObject("result", "작성자 본인만 접근 가능합니다.");
            modelAndView.addObject("url", "./list");
        }
    }
}
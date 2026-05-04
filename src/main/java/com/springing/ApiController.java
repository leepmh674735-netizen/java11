package com.springing;

import java.util.List; // 1. List 사용을 위해 필수
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 만약 ApiDTO와 ApiService가 다른 패키지에 있다면 import가 필요합니다.
// import com.springing.api.ApiDTO;
// import com.springing.api.ApiService;

@Controller
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/api/realtime")
    public String getApiList(Model model) { // 2. 예외 처리는 서비스 내부나 GlobalException으로 관리하는 것이 좋습니다.
        try {
            // 3. 실시간 데이터 호출
            List<ApiDTO> list = apiService.getRealTimeData();
            model.addAttribute("data", list);
        } catch (Exception e) {
            // 에러 발생 시 로그를 남기고 적절한 처리를 합니다.
            model.addAttribute("error", "데이터를 불러오는 중 오류가 발생했습니다.");
        }
        
        return "api/display"; // 데이터를 보여줄 JSP/HTML 경로
    }
}
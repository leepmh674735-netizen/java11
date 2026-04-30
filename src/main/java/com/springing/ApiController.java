package com.springing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/api/realtime")
    public String getApiList(Model model) throws Exception {
        // 실시간 데이터 호출
        List<ApiDTO> list = apiService.getRealTimeData();
        model.addAttribute("data", list);
        
        return "api/display"; // 데이터를 보여줄 JSP/HTML 경로
    }
}

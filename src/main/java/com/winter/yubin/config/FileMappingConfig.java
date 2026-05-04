package com.winter.yubin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileMappingConfig implements WebMvcConfigurer {

    // 브라우저에서 접근할 URL 패턴 (예: /files/**)
    @Value("${yubin.upload.url}")
    private String urlPath;
    
    // 실제 서버 내의 물리적 저장 경로 (예: C:/upload/ 또는 /home/upload/)
    @Value("${yubin.upload.path}")
    private String resourcePath;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 1. registry에 리소스 핸들러를 등록합니다.
        registry.addResourceHandler(urlPath)
                // 2. 물리적 경로를 지정할 때, 'file:///' 접두어가 없다면 추가해주는 것이 안전합니다.
                .addResourceLocations(resourcePath);
        
        // 참고: 정적 리소스(캐싱) 설정 등을 추가로 붙일 수 있습니다.
    }
}
package com.springinpractice.ch01.service; // 보통 Service는 별도 패키지에 둡니다.

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import com.springinpractice.ch01.dao.jdbc.JdbcAccountDao;

public class AccountService {
    private JdbcAccountDao accountDao;
    
    public AccountService() {
        try {
            // 1. 설정 파일(Properties) 로드
            Properties props = new Properties();
            InputStream inputStream = this.getClass().getClassLoader()
                 .getResourceAsStream("dataSource.properties"); // 오타 수정
            
            if (inputStream == null) {
                throw new RuntimeException("설정 파일을 찾을 수 없습니다: dataSource.properties");
            }
            props.load(inputStream);
            
            // 2. DataSource 설정
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(props.getProperty("driverClassName"));
            dataSource.setUrl(props.getProperty("url"));
            dataSource.setUsername(props.getProperty("username"));
            dataSource.setPassword(props.getProperty("password"));
            
            // 3. DAO 생성 및 의존성 주입
            accountDao = new JdbcAccountDao();
            accountDao.setDataSource(dataSource);
            
        } catch (Exception e) {
            throw new RuntimeException("AccountService 초기화 중 에러 발생", e);
        }
    }

    // 서비스 메서드 (예시)
    public JdbcAccountDao getAccountDao() {
        return accountDao;
    }
}
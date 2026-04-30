package com.springinginpractice.ch01.dao.jdbc;
// Source project:sip 01, branch: 01 (Maven Project)

import org.apache.commons.dbcp.BasicDataSource; 
import com.springinpractice.ch01.dao.AccountDao;

public class JdbcAccountDao implements AccountDao {

    private BasicDataSource dataSource;
    
    public JdbcAccountDao() {
        dataSource = new BasicDataSource();
        
        // JDBC 연결 정보 설정
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // 최신 드라이버 경로
        dataSource.setUrl("jdbc:mysql://localhost:3306/springbook?autoReconnect=true");
        dataSource.setUsername("root");
        dataSource.setPassword(""); // 실제 DB 비밀번호 입력
    }
}
}

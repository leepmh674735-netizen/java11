package com.springinginpractice.ch01.dao.jdbc;
//Source project: sip01, branch: 02 (Maven Project)

import javax.sql.DataSource;
import com.springpraction.ch01.dao.AccountDao;

public class JdbcAccountDao {
	
	private DataSource dataSource;
	
	public JdbcAccountDao() {}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
	}

}

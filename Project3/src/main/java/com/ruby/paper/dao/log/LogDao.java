package com.ruby.paper.dao.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogDao {

	// JDBC Template 로드
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public LogDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addLog(String method, String sqlstring, boolean success) {
		String sqlString = "insert into dblog (method,sqlstring,success) values (?,?,?)";
		try {
			jdbcTemplate.update(sqlString, method, sqlstring, success);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
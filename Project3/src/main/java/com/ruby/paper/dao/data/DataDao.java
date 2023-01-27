package com.ruby.paper.dao.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ruby.paper.domain.DataVO;

@Repository
public class DataDao implements DataInterface {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public DataDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		System.out.println("JDBCTemplate이용");
	}

	@Override
	public Map<String, Object> getList() {
		String sqlString = "select * from car";
		Map<String, Object> ret = new HashMap<>();
		ret.put("sql", sqlString);
		try {
			List<DataVO> list = jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<DataVO>(DataVO.class));
			ret.put("data", list);
		} catch (Exception e) {
			ret.put("data", null);
		}
		return ret;
	}

	@Override
	public Map<String, Object> getSearch(String car_num) {
		Map<String, Object> ret = new HashMap<>();
		String sqlString = String.format("select * from car where car_num=%s", car_num);
		ret.put("sql", sqlString);
		try {
			DataVO d = jdbcTemplate.queryForObject(sqlString, new BeanPropertyRowMapper<DataVO>(DataVO.class));
			ret.put("data", d);
		} catch (Exception e) {
			ret.put("data", null);
		}
		return ret;
	}

}
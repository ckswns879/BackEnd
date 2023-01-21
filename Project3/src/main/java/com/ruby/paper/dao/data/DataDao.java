package com.ruby.paper.dao.data;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ruby.paper.domain.DataVO;

@Repository
public class DataDao implements DataInterface {

	private Connection con = null;

	private String sqlString;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public DataDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		System.out.println("JDBCTemplate이용");
	}

	@Override
	public List<DataVO> getList() {

		Statement st = null;
		ResultSet rs = null;

		try {
			String sql = "select * from car";
			sqlString = sql;
			st = con.createStatement();
//			rs = st.executeQuery(sql);
			List<DataVO> list = jdbcTemplate.query(
					sql,
					new RowMapper<DataVO() {
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				DataVO m = new DataVO();
				// rs가 가진 값을 m에 넣어주기
				m.setCar_num(rs.getString("car_num"));
				m.setDistance(rs.getInt("distance"));
				m.setDistance_cum(rs.getInt("distance_cum"));
				m.setSpeed_av(rs.getDouble("speed_av"));
				m.setSpeed_max(rs.getInt("speed_max"));
				m.setOpertating_time(rs.getInt("operating_time"));
				m.setStop_num(rs.getInt("stop_num"));
				m.setStop_time(rs.getInt("stop_time"));
				m.setStop_rate(rs.getDouble("stop_rate"));
				m.setDrive_time(rs.getInt("drive_time"));
				m.setRpm_max(rs.getInt("rpm_max"));
				m.setRpm_av(rs.getInt("rpm_av"));
				m.setResult(false);
				list.add(m);
			});
	// rs의 다음값이 있으면 list에 rs 추가
	while(rs.next())

	{
		// list에 넣을 DataVO 객체 m 선언
	}}catch(
	Exception e)
	{
		e.printStackTrace();
	}finally
	{
		try {
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}System.out.println("DataDao - getMembers Success");return list;
	}

	@Override
	public DataVO getSearch(String car_num) {
		DataVO m = new DataVO();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from car where car_num=?";
			sqlString = String.format("select * from car where car_num=%s", car_num);

			// sql문 실행 객체
			ps = con.prepareStatement(sql);
			// query문의 파라미터 값 설정
			ps.setString(1, car_num);
			// sql문 실행 결과
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("car_num").equals(car_num)) {
					m.setCar_num(rs.getString("car_num"));
					m.setDistance(rs.getInt("distance"));
					m.setDistance_cum(rs.getInt("distance_cum"));
					m.setSpeed_av(rs.getDouble("speed_av"));
					m.setSpeed_max(rs.getInt("speed_max"));
					m.setOpertating_time(rs.getInt("operating_time"));
					m.setStop_num(rs.getInt("stop_num"));
					m.setStop_time(rs.getInt("stop_time"));
					m.setStop_rate(rs.getDouble("stop_rate"));
					m.setDrive_time(rs.getInt("drive_time"));
					m.setRpm_max(rs.getInt("rpm_max"));
					m.setRpm_av(rs.getInt("rpm_av"));
					m.setResult(false);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("getSearch Success");
		return m;
	}

	@Override
	public String getSql() {
		return sqlString;
	}
}
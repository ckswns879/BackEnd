package com.ruby.paper.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruby.paper.dao.data.DataInterface;
import com.ruby.paper.dao.log.LogDao;
import com.ruby.paper.domain.DataVO;

@Service
public class DataService {

	private DataInterface dao;
	private LogDao log;
	
	@Autowired
	public DataService(DataInterface dao, LogDao log) {
		this.dao = dao;
		this.log = log;
}

	@SuppressWarnings("unchecked")
	public List<DataVO> getList(){
		Map<String, Object> map = dao.getList();
		List<DataVO> list = (List<DataVO>) map.get("data");
		if(list!=null) log.addLog("get", (String)map.get("sql"), true);
		else			log.addLog("get", (String)map.get("sql"), false);
		return list;
	}
	
	public DataVO getSearch(String car_num) {
		Map<String, Object> map = dao.getSearch(car_num);
		DataVO d = (DataVO) map.get("data");
		if(d!=null) log.addLog("get", (String)map.get("sql"), true);
		else        log.addLog("get", (String)map.get("sql"), false);
		return d;
	}
	
}

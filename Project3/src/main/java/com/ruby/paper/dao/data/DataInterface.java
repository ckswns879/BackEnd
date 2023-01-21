package com.ruby.paper.dao.data;

import java.util.List;

import com.ruby.paper.domain.DataVO;

public interface DataInterface {

	List<DataVO> getList();

	DataVO getSearch(String car_num);

	String getSql();

}
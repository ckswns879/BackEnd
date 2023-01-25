package com.ruby.paper.dao.data;

import java.util.List;
import java.util.Map;

import com.ruby.paper.domain.DataVO;

public interface DataInterface {

	Map<String, Object> getList();

	Map<String, Object> getSearch(String car_num);

}
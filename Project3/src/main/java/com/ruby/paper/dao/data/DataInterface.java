package com.ruby.paper.dao.data;

import java.util.Map;

public interface DataInterface {

	Map<String, Object> getList();

	Map<String, Object> getSearch(String car_num);

}
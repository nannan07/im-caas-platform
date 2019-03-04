package com.allmsi.plugin.open.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.plugin.open.model.OpenPo;

public interface OpenMapper {
	
	int insertBatch(List<OpenPo> list);

	int updateByPrimaryKey(String id);

	OpenPo openMoreSelect(Map<String, Object> map);

	List<OpenPo> openSelect(Map<String, Object> map);

	int openCount(Map<String, Object> map);

	OpenPo selectByTokenCode(Map<String, Object> map);
}
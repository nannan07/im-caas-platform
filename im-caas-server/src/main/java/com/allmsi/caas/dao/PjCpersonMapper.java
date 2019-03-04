package com.allmsi.caas.dao;

import java.util.List;

import com.allmsi.caas.model.PjCperson;

public interface PjCpersonMapper {
  
	List<PjCperson> selectByPjId(String pjid);

	int deleteByPjid(String pjid);

	int insertBatch(List<PjCperson> list);
}
package com.allmsi.caas.dao;

import java.util.List;

import com.allmsi.caas.model.PjPperson;

public interface PjPpersonMapper {

	int insertBatch(List<PjPperson> pjPersonnel);

	List<PjPperson> selectByPjId(String pjid);

	int deleteByPjid(String pjid);
}
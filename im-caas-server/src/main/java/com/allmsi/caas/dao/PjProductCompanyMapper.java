package com.allmsi.caas.dao;

import java.util.List;

import com.allmsi.caas.model.PjProductCompany;

public interface PjProductCompanyMapper {

	int insertBatch(List<PjProductCompany> list);

	int deleteByPjid(String pjid);

	List<PjProductCompany> selectProductCompanyList(String pjid);
}
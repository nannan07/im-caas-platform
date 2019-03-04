package com.allmsi.caas.service;

import java.util.List;

import com.allmsi.caas.model.vo.PjProductCompanyVo;

public interface ProductCompanyService {

	boolean insertBatch(String pjid, List<PjProductCompanyVo> productCompanyList);

	int deleteByPjid(String pjid);

	List<PjProductCompanyVo> selectProductCompanyList(String pjid);

}

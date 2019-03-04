package com.allmsi.caas.service;

import java.util.List;

import com.allmsi.caas.model.vo.PjPlatGormVo;

public interface PjPlatgormService {
	
	List<PjPlatGormVo> selectPlat();
	
	List<PjPlatGormVo> selectByPjid(String pjId);

	int insertBatch(String pjid, List<String> deptList);

	int deleteByPjid(String pjid);

}

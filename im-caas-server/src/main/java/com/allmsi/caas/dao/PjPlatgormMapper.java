package com.allmsi.caas.dao;

import java.util.List;

import com.allmsi.caas.model.PjPlatgorm;

public interface PjPlatgormMapper {

	int insertBatch(List<PjPlatgorm> list);

	int deleteByPjid(String pjid);

	List<PjPlatgorm> selectByPjid(String pjid);
}
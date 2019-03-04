package com.allmsi.caas.dao;

import java.util.List;

import com.allmsi.caas.model.PjCpType;

public interface PjCpTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(PjCpType record);

    int insertSelective(PjCpType record);

    PjCpType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PjCpType record);

    int updateByPrimaryKey(PjCpType record);

	int deleteFlag(String pjid);

	int insertBatch(List<PjCpType> pjCpTypeList);

	List<PjCpType> selectByPjid(String pjid);
}
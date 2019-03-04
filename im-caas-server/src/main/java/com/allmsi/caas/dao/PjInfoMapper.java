package com.allmsi.caas.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.caas.model.PjInfo;

public interface PjInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(PjInfo record);

    int insertSelective(PjInfo record);

    PjInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PjInfo record);

    int updateByPrimaryKey(PjInfo record);

	List<PjInfo> selectList(Map<String, Object> map);

	int selectListCount(Map<String, Object> map);

	List<PjInfo> selectMyList(Map<String, Object> map);

	int selectMyListCount(Map<String, Object> map);
	
	List<PjInfo> selectByFlowNode(Map<String, Object> map);

	int selectByFlowNodeCount(Map<String, Object> param);
}
package com.allmsi.plugin.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.plugin.flow.model.FlowState;

public interface FlowStateMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(FlowState record);

    int insertSelective(FlowState record);

    FlowState selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FlowState record);

    int updateByPrimaryKey(FlowState record);

	FlowState selectByObjectId(String objectid);

	FlowState selectFlowState(Map<String, Object> param);

	int insertBatch(List<FlowState> list);

	FlowState nodeSelect(Map<String, Object> param);

	int delectFlag(Map<String, Object> param);

}
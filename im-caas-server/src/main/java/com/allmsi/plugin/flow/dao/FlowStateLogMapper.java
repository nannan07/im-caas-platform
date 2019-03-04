package com.allmsi.plugin.flow.dao;

import java.util.List;

import com.allmsi.plugin.flow.model.FlowState;
import com.allmsi.plugin.flow.model.FlowStateLog;

public interface FlowStateLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(FlowStateLog record);

    int insertSelective(FlowStateLog record);

    FlowStateLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FlowStateLog record);

    int updateByPrimaryKey(FlowStateLog record);

	int insertBatch(List<FlowState> list);
	
	List<FlowStateLog> selectByObjectId(FlowStateLog fs);
}
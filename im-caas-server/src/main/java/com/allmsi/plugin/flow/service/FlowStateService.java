package com.allmsi.plugin.flow.service;

import java.util.List;

import com.allmsi.plugin.flow.model.FlowTransactor;
import com.allmsi.plugin.flow.model.vo.FlowStateLogVo;
import com.allmsi.plugin.flow.model.vo.FlowStateVo;

public interface FlowStateService {

	FlowStateVo selectFlowState(String objectid, String userId, String deptId);

	boolean flowOperation(FlowStateVo flowStateVo);

	List<FlowTransactor> receiverSelect(FlowStateVo flowStateVo);
	
	List<FlowStateLogVo> stateList(String objectId);

}

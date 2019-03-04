package com.allmsi.plugin.flow.service;

import java.util.List;

import com.allmsi.plugin.flow.model.FlowState;
import com.allmsi.plugin.flow.model.FlowTransactor;
import com.allmsi.plugin.flow.model.vo.FlowStateVo;

public interface FlowRule {
	
	public boolean isNextNode(FlowState flowState, FlowStateVo flowStateVo);
	
	public boolean isSubmit(FlowState flowState, FlowStateVo flowStateVo);
	
	public List<FlowTransactor> nextNodeTransactor(FlowState flowState, FlowStateVo flowStateVo);

}

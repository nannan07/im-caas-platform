package com.allmsi.plugin.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.plugin.flow.dao.FlowRuleMapper;
import com.allmsi.plugin.flow.model.FlowState;
import com.allmsi.plugin.flow.model.FlowTransactor;
import com.allmsi.plugin.flow.model.vo.FlowStateVo;
import com.allmsi.plugin.flow.service.FlowRule;
import com.allmsi.sys.model.vo.UserVo;
import com.allmsi.sys.service.UserService;

@Service("com.allmsi.plugin.flow.service.impl.FlowRulePjImpl")
public class FlowRulePjImpl implements FlowRule {

	@Resource
	private FlowRuleMapper flowRuleDao;

	@Resource
	private UserService userService;

	@Override
	public boolean isNextNode(FlowState flowState, FlowStateVo flowStateVo) {
		if (flowState == null) {
			flowState = new FlowState();
			flowState.setNode(1);
		}
		int currentNode = flowState.getNode();
		int nextNode = flowStateVo.getNode();
		if (nextNode > 0 && nextNode < 4 && (currentNode + 1 == nextNode || currentNode - 1 == nextNode)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isSubmit(FlowState flowState, FlowStateVo flowStateVo) {
		if (flowState == null) {
			flowState = new FlowState();
			flowState.setNode(1);
		}
		if (isNextNode(flowState, flowStateVo)) {
			int currentNode = flowState.getNode();
			int nextNode = flowStateVo.getNode();
			if (currentNode - 1 != nextNode || (flowStateVo.getTransactorList().size() == 1
					&& flowStateVo.getTransactorList().get(0).getId().equals(flowState.getSenderid()))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<FlowTransactor> nextNodeTransactor(FlowState flowState, FlowStateVo flowStateVo) {
		if (flowState == null) {
			flowState = new FlowState();
			flowState.setNode(1);
		}
		if (isNextNode(flowState, flowStateVo)) {
			Map<String, Object> param = new HashMap<String, Object>();
			List<FlowTransactor> list = new ArrayList<FlowTransactor>();
			if (flowStateVo.getNode() == flowState.getNode() - 1) {
				FlowTransactor flowTransactor = new FlowTransactor();
				flowTransactor.setId(flowState.getSenderid());
				UserVo userVo = userService.selectByPrimaryKey(flowState.getSenderid());
				flowTransactor.setName(userVo.getUserName());
				flowTransactor.setType(1);
				list.add(flowTransactor);
			} else {
				param.put("objectId", flowStateVo.getObjectid());
				param.put("receiver", flowStateVo.getSenderid());
				param.put("node", flowStateVo.getNode());
				list = flowRuleDao.selectNodeReceiverPj(param);
			}
			return list;
		}
		return null;
	}

}

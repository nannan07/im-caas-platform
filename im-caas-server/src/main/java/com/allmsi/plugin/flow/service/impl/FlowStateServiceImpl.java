package com.allmsi.plugin.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allmsi.plugin.flow.dao.FlowStateLogMapper;
import com.allmsi.plugin.flow.dao.FlowStateMapper;
import com.allmsi.plugin.flow.model.FlowState;
import com.allmsi.plugin.flow.model.FlowStateLog;
import com.allmsi.plugin.flow.model.FlowTransactor;
import com.allmsi.plugin.flow.model.vo.FlowStateLogVo;
import com.allmsi.plugin.flow.model.vo.FlowStateVo;
import com.allmsi.plugin.flow.service.FlowRule;
import com.allmsi.plugin.flow.service.FlowStateService;
import com.allmsi.sys.config.PropertyConfig;
import com.allmsi.sys.config.SpringContextRegister;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowStateServiceImpl implements FlowStateService {

	@Resource
	private FlowStateMapper flowStateDao;

	@Resource
	private FlowStateLogMapper flowStateLogDao;

	@Autowired
	private PropertyConfig properties;

	@Autowired
	private SpringContextRegister springContextRegister;

	@Override
	public FlowStateVo selectFlowState(String objectid, String userId, String deptId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("objectId", objectid);
		param.put("userId", userId);
		param.put("deptId", deptId);
		FlowState fs = flowStateDao.selectFlowState(param);
		return new FlowStateVo(fs);
	}

	@Override
	public boolean flowOperation(FlowStateVo flowStateVo) {
		if (StrUtil.isEmpty(flowStateVo.getObjectid())) {
			return false;
		}
		String className = properties.getProperty(flowStateVo.getFlowcode());
		if (StrUtil.isEmpty(className)) {
			return false;
		}
		if (flowStateVo.getTransactorList() != null && flowStateVo.getTransactorList().size() > 0) {// 有接收者
			List<FlowState> flowStateList = transactorList(flowStateVo);
			if (flowStateList.size() > 0) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("objectId", flowStateVo.getObjectid());
				param.put("userId", flowStateVo.getSenderid());
				param.put("deptId", flowStateVo.getSenderDept());
				FlowState flowState = flowStateDao.nodeSelect(param);
				FlowRule flowRule = springContextRegister.getServiceImpl(FlowRule.class, className);
				if(flowRule.isSubmit(flowState, flowStateVo)){
					flowStateDao.delectFlag(param);
					flowStateDao.insertBatch(flowStateList);
					flowStateLogDao.insertBatch(flowStateList);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<FlowTransactor> receiverSelect(FlowStateVo flowStateVo) {
		if (StrUtil.isEmpty(flowStateVo.getObjectid())) {
			return null;
		}
		String className = properties.getProperty(flowStateVo.getFlowcode());
		if (StrUtil.isEmpty(className)) {
			return null;
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("objectId", flowStateVo.getObjectid());
		param.put("userId", flowStateVo.getSenderid());
		param.put("deptId", flowStateVo.getSenderDept());
		FlowState flowState = flowStateDao.nodeSelect(param);// 当前流程状态
		FlowRule flowRule = springContextRegister.getServiceImpl(FlowRule.class, className);
		List<FlowTransactor> list =flowRule.nextNodeTransactor(flowState, flowStateVo);
		return list;
	}

	// 获取接收者的信息
	private List<FlowState> transactorList(FlowStateVo flowStateVo) {
		List<FlowState> flowStateList = new ArrayList<FlowState>();
		for (FlowTransactor transactor : flowStateVo.getTransactorList()) {
			if (transactor == null || StrUtil.isEmpty(transactor.getId())) {
				continue;
			}
			FlowState flowState = new FlowState();
			flowState.setId(UUIDUtil.getUUID());
			flowState.setNode(flowStateVo.getNode());
			flowState.setObjectid(flowStateVo.getObjectid());
			flowState.setSenderid(flowStateVo.getSenderid());
			flowState.setReceiveerid(transactor.getId());
			flowState.setType(transactor.getType());
			flowState.setOpinion(flowStateVo.getOpinion());
			flowStateList.add(flowState);
		}
		return flowStateList;
	}

	@Override
	public List<FlowStateLogVo> stateList(String objectId) {
		FlowStateLog fs = new FlowStateLog();
		fs.setObjectid(objectId);
		List<FlowStateLog> list = flowStateLogDao.selectByObjectId(fs);
		List<FlowStateLogVo>  flowStateLogVoList = new ArrayList<FlowStateLogVo>();
		for (FlowStateLog flowStateLog : list) {
			flowStateLogVoList.add(new FlowStateLogVo(flowStateLog));
		}
		return flowStateLogVoList;
	}
}

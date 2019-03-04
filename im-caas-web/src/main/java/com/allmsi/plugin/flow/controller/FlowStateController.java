package com.allmsi.plugin.flow.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.cache.CacheKeyPrefix;
import com.allmsi.plugin.flow.model.vo.FlowStateVo;
import com.allmsi.plugin.flow.service.FlowStateService;
import com.allmsi.sys.config.CacheInstenceSubclass;
import com.allmsi.sys.model.TokenBean;
import com.allmsi.sys.model.protocol.ErrorProtocol;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.model.vo.UserVo;
import com.allmsi.sys.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Controller
@RequestMapping("flow")
public class FlowStateController {

	@Resource
	private FlowStateService flowStateService;

	@Resource
	private CacheInstenceSubclass cacheInstenceSubclass;

	@RequestMapping(value = "/operation", method = RequestMethod.POST)
	@ResponseBody
	public Object flowOperation(TokenBean tokenBean) {
		String data = tokenBean.getData();
		if (StrUtil.isEmpty(data)) {
			return new WarnProtocol();
		}

		Gson gson = new Gson();
		FlowStateVo flowStateVo = new FlowStateVo();
		try {
			flowStateVo = gson.fromJson(data, FlowStateVo.class);
		} catch (JsonSyntaxException e) {
			return new ErrorProtocol(400);
		}
		UserVo userVo = (UserVo) cacheInstenceSubclass.getObject(CacheKeyPrefix.TOKEN.getValue() + tokenBean.getToken());
		flowStateVo.setSenderid(userVo.getId());
		flowStateVo.setSenderDept(userVo.getDeptId());
		return flowStateService.flowOperation(flowStateVo) ? new SuccessProtocol() : new WarnProtocol("流程流转失败!");
	}

	@RequestMapping(value = "receiver/select", method = RequestMethod.GET)
	@ResponseBody
	public Object receiverSelect(FlowStateVo flowStateVo, String token) {
		if (flowStateVo == null) {
			return new WarnProtocol();
		}
		UserVo userVo = (UserVo) cacheInstenceSubclass.getObject(CacheKeyPrefix.TOKEN.getValue() + token);
		flowStateVo.setSenderid(userVo.getId());
		flowStateVo.setSenderDept(userVo.getDeptId());
		return new SuccessProtocol(flowStateService.receiverSelect(flowStateVo));
	}

	@RequestMapping(value = "/list/select", method = RequestMethod.GET)
	@ResponseBody
	public Object stateList(String objectId) {
		//int page,int pageSize
		if (StrUtil.isEmpty(objectId)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowStateService.stateList(objectId));
	}

}
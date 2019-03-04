package com.allmsi.caas.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.caas.model.vo.RegisterVo;
import com.allmsi.caas.service.RegisterService;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping("customer")
public class CustomerController {
	
	@Resource
	private RegisterService registerService;

	@RequestMapping(value = "/select", method = RequestMethod.GET) // 查看所有注册信息
	@ResponseBody
	public Object registerSelect(String data,Integer page,Integer pageSize) {
		RegisterVo registerVo = new RegisterVo();
		if(StrUtil.notEmpty(data)){
			registerVo.setCpyName(data);
			registerVo.setRealName(data);
		}
		return new SuccessProtocol(registerService.selectPage(registerVo,page,pageSize));
	}

	@RequestMapping(value = "/info/select", method = RequestMethod.GET)
	@ResponseBody
	public Object registerInfoSelect(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(registerService.selectRegister(id));
	}

	@RequestMapping(value = "/auditing", method = RequestMethod.POST)
	@ResponseBody
	public Object auditingRegister(String id, Integer state, String opinion) {
		if (StrUtil.isEmpty(id) || state == null) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(registerService.auditingRegister(id, state, opinion));
	}
	
}

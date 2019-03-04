package com.allmsi.caas.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.allmsi.caas.model.vo.RegisterVo;
import com.allmsi.caas.service.RegisterService;
import com.allmsi.plugin.file.service.FileService;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping(value = "register")
public class RegisterController {

	@Resource
	private RegisterService registerService;

	@Resource
	private FileService fileService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Object register(RegisterVo registerVo) {
		if (registerVo == null) {
			return new WarnProtocol("注册信息提交失败，请核对信息准确性!");
		}
		String password = registerVo.getPassword();
		if (StrUtil.isEmpty(password)) {
			return new WarnProtocol("密码不能为空");
		}
		if (StrUtil.isEmpty(registerVo.getPhone())) {
			return new WarnProtocol("电话不能为空");
		}
		if (StrUtil.isEmpty(registerVo.getEmail())) {
			return new WarnProtocol("邮箱不能为空");
		}
		if (StrUtil.isEmpty(registerVo.getCpyName())) {
			return new WarnProtocol("公司名称不能为空");
		}
		Object info = registerService.insert(registerVo);
		return new SuccessProtocol(info);
	}

	@RequestMapping(value = "cpy/check", method = RequestMethod.GET)
	@ResponseBody
	public Object registerCheckCpy(String cpyName) {
		if (StrUtil.isEmpty(cpyName)) {
			return new WarnProtocol("公司名称不能为空!");
		}
		int flag = registerService.registerCheckCpy(cpyName);
		return new SuccessProtocol((Object)flag);
	}

	@RequestMapping(value = "phone/check", method = RequestMethod.GET)
	@ResponseBody
	public Object registerCheckPhone(String phone) {
		if (StrUtil.isEmpty(phone)) {
			return new WarnProtocol("手机不能为空!");
		}
		return new SuccessProtocol(registerService.registerCheckPhone(phone));
	}

	@RequestMapping(value = "email/check", method = RequestMethod.GET)
	@ResponseBody
	public Object registerCheckEmail(String email) {
		if (StrUtil.isEmpty(email)) {
			return new WarnProtocol("电子邮箱不能为空!");
		}
		return new SuccessProtocol(registerService.registerCheckEmail(email));
	}

	@RequestMapping(value = "/licence/add", method = RequestMethod.POST)
	@ResponseBody
	public Object licenceAdd(MultipartFile file) {
		if (file == null) {
			return new WarnProtocol();
		}
		return fileService.upload(file);
	}

}
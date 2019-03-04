package com.allmsi.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.cache.CacheKeyPrefix;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.vo.LoginVo;
import com.allmsi.sys.model.vo.UserVo;
import com.allmsi.sys.service.LoginService;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping()
public class LoginController extends BaseController{

	@Resource
	private LoginService loginService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(LoginVo loginVo) {
		if (StrUtil.notEmpty(loginVo.getLoginName(), loginVo.getPassword())) {
			UserVo userVo = loginService.login(loginVo);
			if (userVo == null || StrUtil.isEmpty(userVo.getId())) {
				return new SuccessProtocol("用户名/密码错误！");
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userinfo", userVo);
				map.put("menu", getString(CacheKeyPrefix.MENU.getValue() + userVo.getDeptType()));
				return new SuccessProtocol(map);
			}
		} else {
			return new SuccessProtocol("用户名/密码为空！");
		}
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public Object logout(String token) {
		del(CacheKeyPrefix.TOKEN.getValue() + token);
		return new SuccessProtocol("退出系统", true);
	}
}

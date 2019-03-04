package com.allmsi.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.allmsi.cache.CacheKeyPrefix;
import com.allmsi.sys.config.CacheInstenceSubclass;
import com.allmsi.sys.dao.UserMapper;
import com.allmsi.sys.model.po.User;
import com.allmsi.sys.model.vo.LoginVo;
import com.allmsi.sys.model.vo.UserVo;
import com.allmsi.sys.service.LoginService;
import com.allmsi.sys.util.MD5Util;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class LoginServiceImpl implements LoginService{

	@Resource
	private UserMapper userDao;

	@Resource
	private CacheInstenceSubclass cacheInstenceSubclass;
	
	@Value("${im.session.out.value:null}")
	private String SESSION_OUT;

	private final int DEFAULT_SESSION_OUT = 1800;
	
	@Override
	public UserVo login(LoginVo loginVo) {
		User user = new User();
		user.setLoginName(loginVo.getLoginName());
		user.setPassword(MD5Util.encode(loginVo.getPassword()));
		UserVo userVo = new UserVo(userDao.login(user));
		userVo.setToken(UUIDUtil.getUUID());
		String sessionOut = SESSION_OUT;
		int iSessionOut = StrUtil.isNumeric(sessionOut) ? Integer.valueOf(sessionOut) : DEFAULT_SESSION_OUT;
		cacheInstenceSubclass.setObject(CacheKeyPrefix.TOKEN.getValue() + userVo.getToken(), userVo, iSessionOut);
		return userVo;
	}
	
}

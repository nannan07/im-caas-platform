package com.allmsi.sys.controller;

import javax.annotation.Resource;

import com.allmsi.cache.CacheKeyPrefix;
import com.allmsi.sys.config.CacheInstenceSubclass;
import com.allmsi.sys.model.vo.UserVo;

public class BaseController {

	@Resource
	private CacheInstenceSubclass cacheInstenceSubclass;

	public UserVo getUser(String token) {
		return (UserVo) cacheInstenceSubclass.getObject(CacheKeyPrefix.TOKEN.getValue() + token);
	}

	public String getString(String key) {
		return cacheInstenceSubclass.getString(key);
	}

	public boolean del(String key) {
		return cacheInstenceSubclass.del(key);
	}

}

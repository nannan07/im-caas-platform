package com.allmsi.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.allmsi.cache.CacheKeyPrefix;
import com.allmsi.sys.config.CacheInstenceSubclass;
import com.allmsi.sys.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private Environment env;
	
	@Resource
	private CacheInstenceSubclass cacheInstenceSubclass;

	private final static int count = 3;

	@Override
	public void setRedis() {
		for (int i = 1; i <= count; i++) {
			String key = CacheKeyPrefix.MENU.getValue() + String.valueOf(i);
			String value = env.getProperty(key);
			cacheInstenceSubclass.setString(key, value);
		}
	}
}

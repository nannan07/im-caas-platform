package com.allmsi.sys.config;

import org.springframework.stereotype.Component;

import com.allmsi.cache.CacheFactory;
import com.allmsi.cache.CacheInstence;

@Component
public class CacheInstenceSubclass extends CacheInstence {

	public CacheInstenceSubclass(CacheFactory cacheFactory) {
		super(cacheFactory);
	}

	public void setString(String key, String value) {
		getCache().setString(key, value);
	}

	public void setString(String key, String value, int iSessionOut) {
		getCache().setString(key, value, iSessionOut);
	}

	public String getString(String key) {
		return getCache().getString(key);
	}

}

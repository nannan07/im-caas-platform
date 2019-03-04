package com.allmsi.sys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertyConfig {

	@Autowired
	private Environment env;
	
	public String getProperty(String key) {
		return this.env.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		return this.env.getProperty(key, defaultValue);
	}

}

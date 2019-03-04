package com.allmsi.caas.config;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.allmsi.sys.service.MenuService;

public class SysInitManager{
	
	@Resource
	private MenuService menuService;
	
    /** 
     * 构造方法执行后，初始化， 
     */  
    @PostConstruct
    public void init() {
    	menuService.setRedis();
    }  

}

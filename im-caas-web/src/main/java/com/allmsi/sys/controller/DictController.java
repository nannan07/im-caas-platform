package com.allmsi.sys.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.service.DictService;

@Controller
@RequestMapping("/system/dict")
public class DictController {
	
	@Resource
	private DictService dictService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object dictSelect(String type){
		return new SuccessProtocol(dictService.selectDictList(type));
	}
	
}

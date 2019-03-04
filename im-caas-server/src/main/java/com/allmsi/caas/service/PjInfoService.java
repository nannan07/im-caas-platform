package com.allmsi.caas.service;

import com.allmsi.caas.model.vo.PjMoreInfoBean;
import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.model.vo.UserVo;
import com.allmsi.caas.model.vo.PjInfoVo;

public interface PjInfoService {

	ListBean selectList(PjInfoVo pjInfoVo, int page, int pageSize);

	ListBean selectMyList(PjInfoVo pjInfoVo, int page, int pageSize);
	
	ListBean selectByFlowNode(Integer node,int page,int pageSize);

	PjInfoVo selectInfo(String id, String userId, String deptId);

	PjMoreInfoBean selectPjInfo(String pjid);

	String saveInfo(PjInfoVo pjInfoVo, UserVo userVo);

}

package com.allmsi.caas.service;

import java.util.List;

import com.allmsi.caas.model.vo.RegisterVo;
import com.allmsi.sys.model.ListBean;

public interface RegisterService {

	Object insert(RegisterVo registerVo);

	RegisterVo selectRegister(String id);
	
	List<RegisterVo> selectList(RegisterVo registerVo);
	
	ListBean selectPage(RegisterVo registerVo,Integer page,Integer pageSize);

	int registerCheckCpy(String cpyName);

	boolean registerCheckPhone(String phone);

	boolean registerCheckEmail(String email);

	boolean auditingRegister(String id, int state, String opinion);

}
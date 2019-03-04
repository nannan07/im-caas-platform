package com.allmsi.caas.dao;

import java.util.List;

import com.allmsi.caas.model.Register;

public interface RegisterMapper {

	int insertSelective(Register record);

	List<Register> selectList(Register register);

	int selectListCount(Register register);

	Register selectByPrimaryKey(String id);

	int checkUserInfo(Register register);

	int checkCpyInfo(String cpyName);
	
	int auditingRegister(Register record);

}
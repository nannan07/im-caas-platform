package com.allmsi.sys.dao;

import java.util.List;

import com.allmsi.sys.model.po.UserDept;

public interface UserDeptMapper {
	
	int insert(UserDept record);
   
	List<UserDept> selectUserOrDept(UserDept userdept);

	int insertBatch(List<UserDept> userDeptList);

	int batchUpdate(List<UserDept> userDeptList);

	int updateUser(String id);
	
	int updateDept(String id);
}
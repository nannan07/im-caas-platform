package com.allmsi.sys.service;

import java.util.List;

import com.allmsi.sys.model.po.UserRole;

public interface UserRoleService {

	List<UserRole> selectUserOrRole(UserRole userrole);

	int insertBatch(String id, List<String> strings,int i);

	int deleteBatch(String id, List<String> strings, int i);

}

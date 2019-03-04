package com.allmsi.sys.service;

import java.util.List;

public interface UserDeptService {
	
	Object insertUserList(String deptId, List<String> userList);
	
	Object insertDeptList(String userId, List<String> deptList);
	
	Object deleteDeptList(String userId, List<String> deptList);

}

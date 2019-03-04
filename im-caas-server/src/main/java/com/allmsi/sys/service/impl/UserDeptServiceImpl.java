package com.allmsi.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.sys.dao.UserDeptMapper;
import com.allmsi.sys.model.po.UserDept;
import com.allmsi.sys.model.protocol.ErrorProtocol;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.service.UserDeptService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class UserDeptServiceImpl implements UserDeptService {

	@Resource
	private UserDeptMapper userDeptDao;

	@Override
	public Object insertUserList(String deptId, List<String> userList) {
		return insertBatch(deptId, userList, 2);
	}

	@Override
	public Object insertDeptList(String userId, List<String> deptList) {
		return insertBatch(userId, deptList, 1);
	}

	public Object insertBatch(String id, List<String> strings, int i) {
		if (StrUtil.isEmpty(id) || strings == null || strings.size() <= 0) {
			return new ErrorProtocol();
		}
		List<UserDept> userDeptList = new ArrayList<UserDept>();
		if (1 == i) {// 为用户添加部门
			for (String string : strings) {
				UserDept userrole = new UserDept();
				userrole.setId(UUIDUtil.getUUID());
				userrole.setUserId(id);
				userrole.setDeptId(string);
				userDeptList.add(userrole);
			}
		} else {// 为部门添加用户
			for (String string : strings) {
				UserDept userrole = new UserDept();
				userrole.setId(UUIDUtil.getUUID());
				userrole.setUserId(string);
				userrole.setDeptId(id);
				userDeptList.add(userrole);
			}
		}
		userDeptDao.insertBatch(userDeptList);
		return new SuccessProtocol(true);
	}

	@Override
	public Object deleteDeptList(String userId, List<String> deptList) {
		return deleteBatch(userId, deptList, 1);
	}
	
	public Object deleteBatch(String id, List<String> strings, int i) {
		if (StrUtil.isEmpty(id) || strings == null || strings.size() <= 0) {
			return new ErrorProtocol();
		}
		List<UserDept> userDeptList = new ArrayList<UserDept>();
		if (1 == i) {// 为用户删除部门
			for (String string : strings) {
				UserDept userrole = new UserDept();
				userrole.setId(UUIDUtil.getUUID());
				userrole.setUserId(id);
				userrole.setDeptId(string);
				userDeptList.add(userrole);
			}
		} else {// 为部门删除用户
			for (String string : strings) {
				UserDept userrole = new UserDept();
				userrole.setId(UUIDUtil.getUUID());
				userrole.setUserId(string);
				userrole.setDeptId(id);
				userDeptList.add(userrole);
			}
		}
		userDeptDao.batchUpdate(userDeptList);
		return new SuccessProtocol(true);
	}
}

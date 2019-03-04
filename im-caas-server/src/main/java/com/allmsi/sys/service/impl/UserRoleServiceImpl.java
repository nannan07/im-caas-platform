package com.allmsi.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.sys.dao.UserRoleMapper;
import com.allmsi.sys.model.po.UserRole;
import com.allmsi.sys.service.UserRoleService;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Resource
	private UserRoleMapper userRoleDao;

	@Override
	public List<UserRole> selectUserOrRole(UserRole userrole) {
		return userRoleDao.selectUserOrRole(userrole);
	}

	@Override
	public int insertBatch(String id, List<String> strings, int i) {
		List<UserRole> userRoleList = new ArrayList<UserRole>();

		if (1 == i) {// 为用户添加角色
			for (String string : strings) {
				UserRole userrole = new UserRole();
				userrole.setId(UUIDUtil.getUUID());
				userrole.setUserid(id);
				userrole.setRoleid(string);
				userrole.setCreatetime(new Date());
				userrole.setDeleteFlag(0);
				userRoleList.add(userrole);
			}
		} else {// 为角色添加用户
			for (String string : strings) {
				UserRole userrole = new UserRole();
				userrole.setId(UUIDUtil.getUUID());
				userrole.setUserid(string);
				userrole.setRoleid(id);
				userrole.setCreatetime(new Date());
				userrole.setDeleteFlag(0);
				userRoleList.add(userrole);
			}
		}

		return userRoleDao.insertBatch(userRoleList);
	}

	@Override
	public int deleteBatch(String id, List<String> strings, int i) {
		List<UserRole> userRoleList = new ArrayList<UserRole>();

		if (1 == i) {// 为用户删除角色
			for (String string : strings) {
				UserRole userrole = new UserRole();
				userrole.setId(UUIDUtil.getUUID());
				userrole.setUserid(id);
				userrole.setRoleid(string);
				userrole.setUpdatetime(new Date());
				userrole.setDeleteFlag(1);
				userRoleList.add(userrole);
			}
		} else {// 为角色删除用户
			for (String string : strings) {
				UserRole userrole = new UserRole();
				userrole.setId(UUIDUtil.getUUID());
				userrole.setUserid(string);
				userrole.setRoleid(id);
				userrole.setUpdatetime(new Date());
				userrole.setDeleteFlag(1);
				userRoleList.add(userrole);
			}
		}
		return userRoleDao.batchUpdate(userRoleList);
	}

}

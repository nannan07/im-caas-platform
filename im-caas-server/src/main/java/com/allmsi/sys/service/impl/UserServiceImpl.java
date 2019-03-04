package com.allmsi.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.sys.dao.UserDeptMapper;
import com.allmsi.sys.dao.UserMapper;
import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.model.po.User;
import com.allmsi.sys.model.vo.UserVo;
import com.allmsi.sys.service.UserService;
import com.allmsi.sys.util.MD5Util;

/**
 * 用户信息处理类
 * 
 * @author sunnannan
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userDao;

	@Resource
	private UserDeptMapper userDeptDao;

	@Override
	public List<UserVo> select(UserVo userVo) {
		List<User> userList = userDao.select(new User(userVo));
		List<UserVo> userVoList = new ArrayList<>();
		for (User user : userList) {
			userVoList.add(new UserVo(user));
		}
		return userVoList;
	}

	@Override
	public ListBean selectPage(UserVo userVo,Integer page, Integer pageSize) {
		User user = new User(userVo);
		user.setPages(page, pageSize);
		List<User> userList = userDao.select(user);
		List<UserVo> userVoList = new ArrayList<>();
		for (User tempUser : userList) {
			userVoList.add(new UserVo(tempUser));
		}
		return new ListBean(userDao.selectCount(user), userVoList);
	}

	@Override
	public UserVo selectByPrimaryKey(String id) {
		return new UserVo(userDao.selectByPrimaryKey(id));
	}

	@Override
	public List<UserVo> selectByDeptId(String deptId) {
		List<User> userList = userDao.selectByDeptId(deptId);
		List<UserVo> userVoList = new ArrayList<>();
		for (User user : userList) {
			userVoList.add(new UserVo(user));
		}
		return userVoList;
	}

	@Override
	public List<UserVo> selectByRoleId(String roleId) {
		List<User> userList = userDao.selectByRoleId(roleId);
		List<UserVo> userVoList = new ArrayList<>();
		for (User user : userList) {
			userVoList.add(new UserVo(user));
		}
		return userVoList;
	}

	@Override
	public int checkUnique(UserVo userVo) {
		User user = new User();
		user.setPhone(userVo.getPhone());
		user.setEmail(userVo.getEmail());
		return userDao.checkUnique(user);
	}

	@Override
	public String insert(UserVo userVo) {
		return insert(userVo, true);
	}
	
	@Override
	public String insert(UserVo userVo, boolean isPwdMD5) {
		User user = new User();
		user.setPhone(userVo.getPhone());
		user.setEmail(userVo.getEmail());
		int count = userDao.checkUnique(user);
		if (count > 0) {
			return "";
		} else {
			user = new User(userVo);
			String pwd = isPwdMD5 ? MD5Util.encode(user.getPassword()) : user.getPassword();
			user.setPassword(pwd);
			return (userDao.insertSelective(user) == 0) ? "" : user.getId();
		}
	}

	@Override
	public boolean updateByPrimaryKeySelective(UserVo userVo) {
		User user = new User();
		user.setPhone(userVo.getPhone());
		user.setEmail(userVo.getEmail());
		int count = userDao.checkUnique(user);
		if (count > 0) {
			return false;
		} else {
			user = new User(userVo);
			return (userDao.updateByPrimaryKeySelective(user) == 0) ? false : true;
		}
	}

	@Override
	public boolean deleteByPrimaryKey(String id) {
		userDao.deleteByPrimaryKey(id);
		userDeptDao.updateUser(id);
		return true;
	}

	@Override
	public boolean pwdUpdate(String userId, String pwd, String newPsw) {
		User user = userDao.selectByPrimaryKey(userId);
		if (user == null) {
			return false;
		}
		if (user.getPassword().equals(MD5Util.encode(pwd))) {
			User userParam = new User();
			userParam.setId(userId);
			userParam.setPassword(MD5Util.encode(newPsw));
			return (userDao.updateByPrimaryKeySelective(userParam) == 0) ? false : true;
		}
		return false;
	}

}

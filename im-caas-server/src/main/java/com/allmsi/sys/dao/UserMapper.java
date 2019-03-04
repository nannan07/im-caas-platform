package com.allmsi.sys.dao;

import java.util.List;

import com.allmsi.sys.model.po.User;

public interface UserMapper {

	User login(User user);

	List<User> select(User user);

	int selectCount(User user);

    User selectByPrimaryKey(String id);

	List<User> selectByDeptId(String deptId);

	List<User> selectByRoleId(String roleId);

	int checkUnique(User user);

    int insertSelective(User user);

    int updateByPrimaryKeySelective(User record);

	int deleteByPrimaryKey(String id);
}
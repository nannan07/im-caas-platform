package com.allmsi.sys.dao;

import java.util.List;

import com.allmsi.sys.model.po.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

	List<UserRole> selectUserOrRole(UserRole userrole);

	int insertBatch(List<UserRole> userRoleList);

	int deleteBatch(List<UserRole> userRoleList);

	int batchUpdate(List<UserRole> userRoleList);
}
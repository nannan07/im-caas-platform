package com.allmsi.sys.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.sys.model.po.Role;
import com.allmsi.sys.model.vo.RoleVo;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    int updateByPrimaryKeySelective(RoleVo rolevo);

    int updateByPrimaryKey(Role record);
    
    List<Role> query(Map<String, Object> param);

    List<Role> roleNameSelect(String rolename);
	
    int queryCount(Role role);
    
	int upddateflag(String id);

	Role selectByPrimaryKey(String id);

	
}
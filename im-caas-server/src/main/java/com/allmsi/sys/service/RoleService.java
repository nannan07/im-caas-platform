package com.allmsi.sys.service;

import java.util.List;

import com.allmsi.sys.model.po.Role;
import com.allmsi.sys.model.vo.RoleVo;

public interface RoleService {

	List<Role> query(RoleVo rolevo, int page, int page_size);

	boolean insert(RoleVo rolevo);

	boolean updateByPrimaryKeySelective(RoleVo rolevo);

	boolean deleteByPrimaryKey(String id);

	int queryCount(RoleVo rolevo);

	Role selectByPrimaryKey(String id);

}

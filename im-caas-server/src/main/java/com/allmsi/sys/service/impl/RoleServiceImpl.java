package com.allmsi.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.sys.dao.RoleMapper;
import com.allmsi.sys.model.po.Role;
import com.allmsi.sys.model.vo.RoleVo;
import com.allmsi.sys.service.RoleService;
import com.allmsi.sys.util.StrUtil;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper roleDao;

	@Override
	public int queryCount(RoleVo rolevo) {
		Role role = new Role();
		if (StrUtil.notEmpty(rolevo.getRolename())) {
			role.setRolename(rolevo.getRolename());
		}
		return roleDao.queryCount(role);
	}
	
	@Override
	public List<Role> query(RoleVo rolevo, int page, int page_size) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", (page - 1) * page_size);
		param.put("pageSize", page_size);
		if (StrUtil.notEmpty(rolevo.getRolename())) {
			param.put("rolename", rolevo.getRolename());
		}
		return roleDao.query(param);
	}

	@Override
	public boolean insert(RoleVo rolevo) {
		Role role = new Role();
		role.setRolename(rolevo.getRolename());
		List<Role> rolelist = roleDao.roleNameSelect(role.getRolename());
		if (rolelist != null && rolelist.size() > 0) {
			return false;
		}
		role.setId(rolevo.getId());
		int msg = roleDao.insert(role);
		return (msg != 0) ? true : false;

	}

	@Override
	public boolean updateByPrimaryKeySelective(RoleVo rolevo) {
		List<Role> rolelist = roleDao.roleNameSelect(rolevo.getRolename());
		if (rolelist != null && rolelist.size() > 0) {
			return false;
		}
		int msg = roleDao.updateByPrimaryKeySelective(rolevo);
		return (msg != 0) ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(String id) {
		int msg = roleDao.upddateflag(id);
		return (msg != 0) ? true : false;
	}

	@Override
	public Role selectByPrimaryKey(String id) {
		return roleDao.selectByPrimaryKey(id);
	}
}

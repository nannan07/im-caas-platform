package com.allmsi.sys.dao;

import java.util.List;

import com.allmsi.sys.model.po.Dept;

public interface DeptMapper {
	
	int selectCount(Dept dept);

	List<Dept> selectPage(Dept dept);

	List<Dept> query(Dept dept);

	Dept selectByPrimaryKey(String id);

	int checkUnique(Dept dept);

	int insertSelective(Dept dept);

	int deleteByPrimaryKey(String id);

	int deleteByLicence(String bLicenceId);

	int updateByPrimaryKeySelective(Dept dept);

}
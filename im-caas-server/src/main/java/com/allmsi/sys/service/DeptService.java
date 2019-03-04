package com.allmsi.sys.service;

import java.util.List;

import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.model.po.Dept;
import com.allmsi.sys.model.vo.DeptVo;

public interface DeptService {

	String insertSelective(DeptVo deptVo);

	ListBean selectPage(DeptVo deptvo);
	
	DeptVo selectByPrimaryKey(String id);

	List<Dept> selectByDeptType(int deptType);
	
	List<Dept> selectByDeptName(String deptName);
	
	boolean deleteByBLicenceId(String bLicenceId);
	
	boolean deleteByPrimaryKey(String id);
	
	boolean updateByPrimaryKeySelective(DeptVo deptVo);

}

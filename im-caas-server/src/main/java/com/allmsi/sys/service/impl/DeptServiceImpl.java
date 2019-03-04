package com.allmsi.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.plugin.file.service.FileService;
import com.allmsi.sys.dao.DeptMapper;
import com.allmsi.sys.dao.UserDeptMapper;
import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.model.po.Dept;
import com.allmsi.sys.model.vo.DeptVo;
import com.allmsi.sys.service.DeptService;
import com.allmsi.sys.util.StrUtil;

@Service
public class DeptServiceImpl implements DeptService {

	@Resource
	private DeptMapper deptDao;

	@Resource
	private FileService fileService;

	@Resource
	private UserDeptMapper userDeptDao;

	@Override
	public String insertSelective(DeptVo deptVo) {
		Dept dept = new Dept();
		dept.setDeptName(deptVo.getDeptName());
		int count = deptDao.checkUnique(dept);
		if (count > 0) {
			return "";
		}
		dept = new Dept(deptVo);
		return (deptDao.insertSelective(dept) == 0) ? "" : dept.getId();
	}

	public ListBean selectPage(DeptVo deptvo) {
		return new ListBean(selectCount(deptvo), select(deptvo));
	}

	private List<Object> select(DeptVo deptvo) {
		List<Dept> deptList = deptDao.selectPage(new Dept(deptvo));
		List<DeptVo> deptVoList = new ArrayList<>();
		for (Dept dept : deptList) {
			deptVoList.add(new DeptVo(dept));
		}
		List<Object> list = new ArrayList<Object>();
		list.addAll(deptVoList);
		return list;
	}

	private int selectCount(DeptVo deptvo) {
		return deptDao.selectCount(new Dept(deptvo));
	}

	@Override
	public boolean updateByPrimaryKeySelective(DeptVo deptVo) {
		Dept dept = new Dept();
		dept.setDeptName(deptVo.getDeptName());
		int count = deptDao.checkUnique(dept);
		if (count > 0) {
			return false;
		} else {
			dept = new Dept(deptVo);
			return (deptDao.updateByPrimaryKeySelective(dept) == 0) ? false : true;
		}
	}

	@Override
	public DeptVo selectByPrimaryKey(String id) {
		return new DeptVo(deptDao.selectByPrimaryKey(id));
	}

	@Override
	public boolean deleteByPrimaryKey(String id) {
		deptDao.deleteByPrimaryKey(id);
		userDeptDao.updateDept(id);
		return true;
	}

	@Override
	public boolean deleteByBLicenceId(String id) {
		fileService.delete(id);
		deptDao.deleteByLicence(id);
		return true;
	}

	@Override
	public List<Dept> selectByDeptType(int deptType) {
		if(0 == deptType){
			return null;
		}
		Dept dept = new Dept();
		dept.setDeptType(deptType);
		return deptDao.query(dept);
	}
	
	@Override
	public List<Dept> selectByDeptName(String deptName) {
		if(StrUtil.isEmpty(deptName)){
			return null;
		}
		Dept dept = new Dept();
		dept.setDeptName(deptName);
		return deptDao.query(dept);
	}
}

package com.allmsi.caas.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.caas.dao.PjPlatgormMapper;
import com.allmsi.caas.model.PjPlatgorm;
import com.allmsi.caas.model.vo.PjPlatGormVo;
import com.allmsi.caas.service.PjPlatgormService;
import com.allmsi.sys.model.po.Dept;
import com.allmsi.sys.service.DeptService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class PjPlatgormServiceImpl implements PjPlatgormService {

	@Resource
	private PjPlatgormMapper pjPlatgormDao;
	
	@Resource
	private DeptService deptService;
	
	private final int DEPT_TYPE = 3;

	@Override
	public int insertBatch(String pjid, List<String> deptList) {
		int msg = pjPlatgormDao.deleteByPjid(pjid);
		if(deptList.size()>0){
			List<PjPlatgorm> list = new ArrayList<PjPlatgorm>();
			for (String string : deptList) {
				PjPlatgorm pjPlatgorm = new PjPlatgorm();
				pjPlatgorm.setId(UUIDUtil.getUUID());
				pjPlatgorm.setPjId(pjid);
				pjPlatgorm.setDeptId(string);
				list.add(pjPlatgorm);
			}
			return pjPlatgormDao.insertBatch(list);
		}
		return msg;
	}

	@Override
	public int deleteByPjid(String pjid) {
		return pjPlatgormDao.deleteByPjid(pjid);
	}

	@Override
	public List<PjPlatGormVo> selectPlat() {
		List<Dept> deptList = deptService.selectByDeptType(DEPT_TYPE);
		List<PjPlatGormVo> platGormVoList = new ArrayList<>();
		for (Dept dept : deptList) {
			PjPlatGormVo platGormVo = new PjPlatGormVo();
			platGormVo.setDeptId(dept.getId());
			platGormVo.setDeptName(dept.getDeptName());
			platGormVoList.add(platGormVo);
		}
		return platGormVoList;
	}

	@Override
	public List<PjPlatGormVo> selectByPjid(String pjId) {
		if(StrUtil.isEmpty(pjId)){
			return null;
		}
		List<PjPlatgorm> pjPlatgorm = pjPlatgormDao.selectByPjid(pjId);
		List<PjPlatGormVo> pjPlatGormVo = new ArrayList<PjPlatGormVo>();// 发行平台
		for (PjPlatgorm d : pjPlatgorm) {
			pjPlatGormVo.add(new PjPlatGormVo(d));
		}
		return pjPlatGormVo;
	}
}

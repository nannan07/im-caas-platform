package com.allmsi.caas.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.caas.dao.PjPpersonMapper;
import com.allmsi.caas.model.PjPperson;
import com.allmsi.caas.model.vo.PjPPersonVO;
import com.allmsi.caas.service.PjPersonnelService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class PjPersonnelServiceImpl implements PjPersonnelService {

	@Resource
	private PjPpersonMapper pjPersonnelDao;

	@Override
	public String addPjPersonnel(String pjId,List<PjPPersonVO> pjPersonnelVO) {
		if(StrUtil.isEmpty(pjId)){
			return null;
		}
		pjPersonnelDao.deleteByPjid(pjId);
		if (pjPersonnelVO != null && pjPersonnelVO.size() > 0) {
			List<PjPperson> pjPersonnel = new ArrayList<PjPperson>();
			for (PjPPersonVO pjPersonnelvo2 : pjPersonnelVO) {
				PjPperson pjPersonnelindex = new PjPperson(pjPersonnelvo2);
				pjPersonnelindex.setId(UUIDUtil.getUUID());
				pjPersonnelindex.setPjid(pjId);
				pjPersonnel.add(pjPersonnelindex);
			}
			pjPersonnelDao.insertBatch(pjPersonnel);
		}
		return pjId;
	}

	@Override
	public List<PjPPersonVO> selectPjPersonnelListByPjId(String pjId) {
		List<PjPperson> pjPersonnel = pjPersonnelDao.selectByPjId(pjId);
		List<PjPPersonVO> pjPPersonList = new ArrayList<PjPPersonVO>();// 项目人员
		for (PjPperson personnel : pjPersonnel) {
			pjPPersonList.add(new PjPPersonVO(personnel));
		}
		return pjPPersonList;
	}

}

package com.allmsi.caas.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.caas.dao.PjCpersonMapper;
import com.allmsi.caas.model.PjCperson;
import com.allmsi.caas.model.vo.PjCpersonVo;
import com.allmsi.caas.service.PjCPersonService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class PjCPersonServiceImpl implements PjCPersonService {

	@Resource
	private PjCpersonMapper pjCpersonDao;

	@Override
	public boolean addPjCPerson(String pjId, List<PjCpersonVo> cPersonVO) {
		if (StrUtil.isEmpty(pjId)) {
			return false;
		}
		pjCpersonDao.deleteByPjid(pjId);
		if (cPersonVO != null && cPersonVO.size() > 0) {
			List<PjCperson> list = new ArrayList<PjCperson>();
			for (PjCpersonVo cPVO : cPersonVO) {
				PjCperson pjCperson = new PjCperson(cPVO);
				pjCperson.setId(UUIDUtil.getUUID());
				pjCperson.setPjid(pjId);
				list.add(pjCperson);
			}
			pjCpersonDao.insertBatch(list);
		}
		return true;
	}

	@Override
	public List<PjCpersonVo> selectPjCPersonList(String pjId) {
		List<PjCperson> pjCpersonList = pjCpersonDao.selectByPjId(pjId);
		List<PjCpersonVo> cpersonVO = new ArrayList<PjCpersonVo>();// 联系人
		for (PjCperson pjCperson : pjCpersonList) {
			cpersonVO.add(new PjCpersonVo(pjCperson));
		}
		return cpersonVO;
	}

}

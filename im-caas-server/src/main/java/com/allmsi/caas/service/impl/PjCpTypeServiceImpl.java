package com.allmsi.caas.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.caas.dao.PjCpTypeMapper;
import com.allmsi.caas.model.PjCpType;
import com.allmsi.caas.service.PjCpTypeService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class PjCpTypeServiceImpl implements PjCpTypeService {

	@Resource
	private PjCpTypeMapper pjCpTypeDao;

	@Override
	public boolean savePjCpType(String pjid, List<String> types) {
		pjCpTypeDao.deleteFlag(pjid);// 对之前已经存在的合作类型进行标记删除
		List<PjCpType> pjCpTypeList = new ArrayList<PjCpType>();
		if (types != null && types.size() > 0) {
			for (String type : types) {
				PjCpType record = new PjCpType();
				record.setId(UUIDUtil.getUUID());
				record.setPjid(pjid);
				record.setType(type);
				pjCpTypeList.add(record);
			}
			pjCpTypeDao.insertBatch(pjCpTypeList);
		}
		return true;
	}

	@Override
	public List<String> selectPjCpType(String pjid) {
		List<String> pjCpTypeStringList = new ArrayList<String>();
		if(StrUtil.isEmpty(pjid)){
			return pjCpTypeStringList;
		}
		List<PjCpType> pjCpTypeList =  pjCpTypeDao.selectByPjid(pjid);
		for (PjCpType PjCpType : pjCpTypeList) {
			pjCpTypeStringList.add(String.valueOf(PjCpType.getType()));
		}
		return pjCpTypeStringList;
	}

}

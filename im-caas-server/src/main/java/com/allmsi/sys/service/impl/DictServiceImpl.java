package com.allmsi.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.sys.dao.DictMapper;
import com.allmsi.sys.model.po.Dict;
import com.allmsi.sys.model.vo.DictVo;
import com.allmsi.sys.service.DictService;

@Service
public class DictServiceImpl implements DictService {
	
	@Resource
	private DictMapper dictDao;

	@Override
	public List<DictVo> selectDictList(String type) {
		List<Dict> dictList = dictDao.selectDictList(type);
		List<DictVo> dictVoList = new ArrayList<DictVo>();
		for (Dict dict : dictList) {
			dictVoList.add(new DictVo(dict));
		}
		return dictVoList;
	}

}

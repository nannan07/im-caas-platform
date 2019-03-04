package com.allmsi.caas.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.caas.dao.PjThemeMapper;
import com.allmsi.caas.model.PjTheme;
import com.allmsi.caas.model.vo.PjThemeVo;
import com.allmsi.caas.service.PjThemeService;
import com.allmsi.sys.model.vo.DictVo;
import com.allmsi.sys.service.DictService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class PjThemeServiceImpl implements PjThemeService {
	
	@Resource
	private PjThemeMapper pjThemeDao;

	@Resource
	private DictService dictService;
	
	private final String DICT_TYPE = "01";

	@Override
	public List<PjThemeVo> selectAllThemeList() {
		List<DictVo> pjDictList =  dictService.selectDictList(DICT_TYPE);
		 List<PjThemeVo> pjDictVoList = new ArrayList<PjThemeVo>();
		for (DictVo pjDict : pjDictList) {
			pjDictVoList.add(new PjThemeVo(pjDict));
		}
		return pjDictVoList;
	}

	@Override
	public List<PjThemeVo> selectThemeList(String pjId) {
		List<PjTheme> pjDictList =  pjThemeDao.selectByPjId(pjId);
		 List<PjThemeVo> pjDictVoList = new ArrayList<PjThemeVo>();
		for (PjTheme pjDict : pjDictList) {
			pjDictVoList.add(new PjThemeVo(pjDict));
		}
		return pjDictVoList;
	}

	@Override
	public String saveThemeList(String pjId, List<PjThemeVo> themeIdList) {
		if (StrUtil.isEmpty(pjId)) {
			return null;
		}
		pjThemeDao.deleteByPjid(pjId);
		if(themeIdList != null && themeIdList.size() > 0){
			List<PjTheme> pjDictList = new ArrayList<PjTheme>();
			for (PjThemeVo pjThemeVo : themeIdList) {
				PjTheme pjDict = new PjTheme();
				pjDict.setId(UUIDUtil.getUUID());
				pjDict.setPjId(pjId);
				pjDict.setDictId(pjThemeVo.getDictId());
				pjDictList.add(pjDict);
			}
			pjThemeDao.insertBatch(pjDictList);
		}
		return pjId;
	}
}

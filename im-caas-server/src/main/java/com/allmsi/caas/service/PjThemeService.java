package com.allmsi.caas.service;

import java.util.List;

import com.allmsi.caas.model.vo.PjThemeVo;

public interface PjThemeService {

	List<PjThemeVo> selectAllThemeList();

	List<PjThemeVo> selectThemeList(String pjId);

	String saveThemeList(String pjId, List<PjThemeVo> dictIdList);

}

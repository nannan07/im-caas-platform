package com.allmsi.caas.dao;

import java.util.List;

import com.allmsi.caas.model.PjTheme;

public interface PjThemeMapper {

	List<PjTheme> selectByPjId(String pjid);

	int insertBatch(List<PjTheme> pjDictList);

	int deleteByPjid(String pjId);
}
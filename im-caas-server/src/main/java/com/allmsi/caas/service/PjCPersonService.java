package com.allmsi.caas.service;

import java.util.List;

import com.allmsi.caas.model.vo.PjCpersonVo;

public interface PjCPersonService {
	
	List<PjCpersonVo> selectPjCPersonList(String pjId);

	boolean addPjCPerson(String pjId, List<PjCpersonVo> cPersonVO);

}

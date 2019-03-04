package com.allmsi.caas.service;

import java.util.List;

import com.allmsi.caas.model.vo.PjPPersonVO;

public interface PjPersonnelService {

	List<PjPPersonVO> selectPjPersonnelListByPjId(String pjId);

	String addPjPersonnel(String pjId, List<PjPPersonVO> pjPersonnelVO);


}

package com.allmsi.caas.service;

import java.util.List;

public interface PjCpTypeService {

	boolean savePjCpType(String pjid, List<String> types);

	List<String> selectPjCpType(String pjid);

}

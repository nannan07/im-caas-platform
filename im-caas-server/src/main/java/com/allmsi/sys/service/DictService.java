package com.allmsi.sys.service;

import java.util.List;

import com.allmsi.sys.model.vo.DictVo;

public interface DictService {

	List<DictVo> selectDictList(String type);

}

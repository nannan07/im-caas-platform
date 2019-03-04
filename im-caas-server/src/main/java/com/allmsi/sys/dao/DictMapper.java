package com.allmsi.sys.dao;

import java.util.List;

import com.allmsi.sys.model.po.Dict;

public interface DictMapper {
    int deleteByPrimaryKey(String id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);

	List<Dict> selectDictList(String type);
}
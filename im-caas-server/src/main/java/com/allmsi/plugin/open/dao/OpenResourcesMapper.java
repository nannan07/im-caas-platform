package com.allmsi.plugin.open.dao;

import java.util.List;

import com.allmsi.plugin.open.model.OpenResourcesPo;

public interface OpenResourcesMapper {

    int insertSelective(OpenResourcesPo record);

    int updateByPrimaryKeySelective(OpenResourcesPo record);
    
    int insertBatch(List<OpenResourcesPo> list);

	List<String> selectByOpenId(String id);

}
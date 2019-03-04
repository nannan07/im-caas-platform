package com.allmsi.caas.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.caas.model.OpinionNews;

public interface OpinionNewsMapper {
    int deleteByPrimaryKey(String id);

    int insert(OpinionNews record);

    int insertSelective(OpinionNews record);

    OpinionNews selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OpinionNews record);

    int updateByPrimaryKey(OpinionNews record);

	List<OpinionNews> selectBySender(String userId);

	int selectCount(OpinionNews unreadNews);

	List<OpinionNews> query(Map<String, Object> map);

	int deleteFlag(OpinionNews unreadNews);

	List<OpinionNews> query1(Map<String, Object> param);

	int updateBatch(List<OpinionNews> list);

}
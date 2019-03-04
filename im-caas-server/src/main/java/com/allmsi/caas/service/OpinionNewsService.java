package com.allmsi.caas.service;

import java.util.List;

import com.allmsi.caas.model.OpinionNews;
import com.allmsi.caas.model.vo.OpinionNewsVo;
import com.allmsi.sys.model.protocol.SuccessProtocol;

public interface OpinionNewsService {

	List<OpinionNews> selectBySender(String userId);

	int selectCount(OpinionNews unreadNews);

	List<OpinionNews> query(OpinionNews sender, int page, int page_size);

	OpinionNews selectByPrimaryKey(String id);

	SuccessProtocol saveOpinion(OpinionNewsVo opinionNewsVo);

	Boolean delOpinion(String userid,String id);

	List<OpinionNews> query1(OpinionNews receive, int page, int page_size);

	Boolean markOpinion(List<String> id);

}

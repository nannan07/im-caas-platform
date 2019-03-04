package com.allmsi.caas.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.caas.dao.OpinionNewsMapper;
import com.allmsi.caas.model.OpinionNews;
import com.allmsi.caas.model.vo.OpinionNewsVo;
import com.allmsi.caas.service.OpinionNewsService;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class OpinionNewsServiceImpl implements OpinionNewsService {

	@Resource
	private OpinionNewsMapper opinionNewsDao;

	@Override
	public List<OpinionNews> selectBySender(String userId) {
		return opinionNewsDao.selectBySender(userId);
	}

	@Override
	public int selectCount(OpinionNews unreadNews) {
		return opinionNewsDao.selectCount(unreadNews);
	}

	@Override
	public List<OpinionNews> query(OpinionNews sender, int page, int page_size) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mark", sender.getMark());
		param.put("senderflag", sender.getSenderflag());
		param.put("receiverflag", sender.getReceiverflag());
		param.put("receiver", sender.getReceiver());
		param.put("sender", sender.getSender());
		param.put("page", (page - 1) * page_size);
		param.put("pageSize", page_size);
		return opinionNewsDao.query(param);
	}

	@Override
	public OpinionNews selectByPrimaryKey(String id) {
		return opinionNewsDao.selectByPrimaryKey(id);
	}

	@Override
	public SuccessProtocol saveOpinion(OpinionNewsVo opinionNewsVo) {
		int msg = 0;
		SuccessProtocol util = new SuccessProtocol();
		String id = opinionNewsVo.getId();
		if (StrUtil.isEmpty(id)) {// 添加
			OpinionNews record = new OpinionNews();
			String uuid=UUIDUtil.getUUID();
			record.setId(uuid);
			record.setSender(opinionNewsVo.getSender());
			record.setReceiver(opinionNewsVo.getReceiver());
			record.setTitle(opinionNewsVo.getTitle());
			record.setContent(opinionNewsVo.getContent());
			record.setMark(0);
			record.setSenderflag(0);
			record.setReceiverflag(0);
			msg = opinionNewsDao.insert(record);
			System.out.println(uuid);
			if(msg>0){
				util.setData(uuid);
				util.setMsg("成功");
				util.setStatus(200);
			}
		} else {// 修改
			OpinionNews news = opinionNewsDao.selectByPrimaryKey(id);
			if(1 == news.getMark()){//接收方已读----不能修改
				util.setData(false);
				util.setMsg("接收方已读");
				util.setStatus(200);
				return util;
			}else{//接收方未读---可以修改
				OpinionNews record = new OpinionNews();
				record.setId(opinionNewsVo.getId());
				record.setTitle(opinionNewsVo.getTitle());
				record.setContent(opinionNewsVo.getContent());
				msg = opinionNewsDao.updateByPrimaryKeySelective(record);
				if(msg>0){
					util.setData(true);
					util.setMsg("成功");
					util.setStatus(200);
				}
			}
		}
		
		if(msg==0){
			util.setData(false);
			util.setMsg("失败");
			util.setStatus(200);
		}
		return util;
	}

	@Override
	public Boolean delOpinion(String userid,String id) {
		OpinionNews news = opinionNewsDao.selectByPrimaryKey(id);
		OpinionNews unreadNews = new OpinionNews();
		if(userid.equals(news.getSender())){//发送方
			System.out.println("发送方");
			if(1 == news.getMark()){//接收方已读-----发送方标识为1
				unreadNews.setId(id);
				unreadNews.setSenderflag(1);
			}else{//接收方未读---所有标识都为1
				unreadNews.setId(id);
				unreadNews.setSenderflag(1);
				unreadNews.setReceiverflag(1);
				unreadNews.setMark(1);
			}
		}else{//接收方
			System.out.println("接收方");
			unreadNews.setId(id);
			unreadNews.setReceiverflag(1);
			unreadNews.setMark(1);
		}
		int msg = opinionNewsDao.deleteFlag(unreadNews);
		return (msg != 0) ? true : false;
	}

	@Override
	public List<OpinionNews> query1(OpinionNews receive, int page, int page_size) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("receiverflag", receive.getReceiverflag());
		param.put("receiver", receive.getReceiver());
		param.put("page", (page - 1) * page_size);
		param.put("pageSize", page_size);
		return opinionNewsDao.query1(param);
	}

	@Override
	public Boolean markOpinion(List<String> ids) {
		List<OpinionNews> list = new ArrayList<OpinionNews>();
		for (String string : ids) {
			OpinionNews news = new OpinionNews();
			news.setId(string);
			news.setMark(1);
			list.add(news);
		}
		System.out.println(list.size());
		int msg = opinionNewsDao.updateBatch(list);;
		return (msg != 0) ? true : false;
	}

}

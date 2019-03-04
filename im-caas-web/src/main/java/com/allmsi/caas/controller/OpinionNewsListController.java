package com.allmsi.caas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.caas.model.OpinionNews;
import com.allmsi.caas.service.OpinionNewsService;
import com.allmsi.cache.CacheKeyPrefix;
import com.allmsi.sys.config.CacheInstenceSubclass;
import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.model.TokenBean;
import com.allmsi.sys.model.po.User;
import com.allmsi.sys.model.protocol.SuccessProtocol;

@Controller
@RequestMapping("opinion/list")
public class OpinionNewsListController {

	@Resource
	private OpinionNewsService opinionNewsService;

	@Resource
	private CacheInstenceSubclass cacheInstenceSubclass;

	@RequestMapping(value = "/sender/select", method = RequestMethod.GET) // 查询发送的消息列表
	@ResponseBody
	public Object senderNews(TokenBean tokenBean, int page, int page_size) {
		String token = tokenBean.getToken();
		User user = (User) cacheInstenceSubclass.getObject(CacheKeyPrefix.TOKEN.getValue() + token);
		String userId = user.getId();
		List<Object> list = new ArrayList<Object>();
		int total = 0;
		// 发送的所有消息数
		OpinionNews senderCount = new OpinionNews();
		senderCount.setSender(userId);
		senderCount.setSenderflag(0);
		total = opinionNewsService.selectCount(senderCount);
		// 查询发送信息列表
		OpinionNews sender = new OpinionNews();
		sender.setSender(userId);
		sender.setSenderflag(0);
		list.addAll(opinionNewsService.query(sender, page, page_size));

		ListBean base = new ListBean();
		base.setTotal(total);
		base.setList(list);
		return new SuccessProtocol(base);
	}

	@RequestMapping(value = "/receicver/select", method = RequestMethod.GET) // 查询接收的消息列表
	@ResponseBody
	public Object receiverSelect(TokenBean tokenBean, int page, int page_size) {
		String token = tokenBean.getToken();
		User user = (User) cacheInstenceSubclass.getObject(CacheKeyPrefix.TOKEN.getValue() + token);
		String userId = user.getId();
		List<Object> list = new ArrayList<Object>();
		int total = 0;
		// 接收的所有消息数
		OpinionNews receiverCount = new OpinionNews();
		receiverCount.setReceiver(userId);
		receiverCount.setReceiverflag(0);
		total = opinionNewsService.selectCount(receiverCount);
		// 查询接收的消息列表,未读消息在前
		OpinionNews receive = new OpinionNews();
		receive.setReceiver(userId);
		receive.setReceiverflag(0);
		list.addAll(opinionNewsService.query1(receive, page, page_size));
		ListBean base = new ListBean();
		base.setList(list);
		base.setTotal(total);
		return new SuccessProtocol(base);
	}

	@RequestMapping(value = "/count/select", method = RequestMethod.GET) // 查询未读消息数
	@ResponseBody
	public Object countSelect(TokenBean tokenBean) {
		String token = tokenBean.getToken();
		User user = (User) cacheInstenceSubclass.getObject(CacheKeyPrefix.TOKEN.getValue() + token);
		String userId = user.getId();
		// 用户的未读条数
		int total = 0;
		OpinionNews unreadNews = new OpinionNews();
		unreadNews.setReceiver(userId);
		unreadNews.setMark(0);
		unreadNews.setReceiverflag(0);
		total = opinionNewsService.selectCount(unreadNews);
		ListBean base = new ListBean();
		base.setTotal(total);
		return new SuccessProtocol(base);
	}

	// @RequestMapping(value = "/more/select", method = RequestMethod.GET) //
	// 查询未读消息列表
	// @ResponseBody
	// public Object moreSelect(TokenBean tokenBean, int page, int page_size) {
	// String token = tokenBean.getToken();
	// User user = CacheManager.getInstance().getTokenCode(token);
	// String userId = user.getId();
	// System.out.println("当前用户：" + userId);
	// List<Object> list = new ArrayList<Object>();
	// int total = 0;
	// // 用户的未读条数
	// OpinionNews unreadNews = new OpinionNews();
	// unreadNews.setReceiver(userId);
	// unreadNews.setMark(0);
	// unreadNews.setReceiverflag(0);
	// total = opinionNewsService.selectCount(unreadNews);
	// OpinionNews receive = new OpinionNews();
	// receive.setReceiver(userId);
	// receive.setReceiverflag(0);
	// receive.setMark(0);
	// list.addAll(opinionNewsService.query(receive, page, page_size));
	// System.out.println("未读消息数:" + list.size());
	// ListBean bean = new ListBean();
	// bean.setList(list);
	// bean.setTotal(total);
	// BaseBean util = new BaseBean();
	// util.setData(bean);
	// util.setStatus(200);
	// util.setMsg("查询结束!");
	// return util;
	// }

}

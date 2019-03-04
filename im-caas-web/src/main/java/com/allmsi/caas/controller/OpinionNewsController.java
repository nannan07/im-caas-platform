package com.allmsi.caas.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.caas.model.OpinionNews;
import com.allmsi.caas.model.vo.OpinionIdsVo;
import com.allmsi.caas.model.vo.OpinionNewsVo;
import com.allmsi.caas.service.OpinionNewsService;
import com.allmsi.cache.CacheKeyPrefix;
import com.allmsi.sys.config.CacheInstenceSubclass;
import com.allmsi.sys.model.TokenBean;
import com.allmsi.sys.model.po.User;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping("opinion")
public class OpinionNewsController {

	@Resource
	private OpinionNewsService opinionNewsService;

	@Resource
	private CacheInstenceSubclass cacheInstenceSubclass;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Object select(String id) {
		OpinionNews info = new OpinionNews();
		if (StrUtil.notEmpty(id)) {
			info = opinionNewsService.selectByPrimaryKey(id);
		}
		System.out.println("查询的消息为：" + info.getId() + "[" + info.getSender() + "/" + info.getTitle() + "]");
		return new SuccessProtocol(info);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOpinion(OpinionNewsVo opinionNewsVo, TokenBean tokenBean) {

		String token = tokenBean.getToken();
		User user = (User)cacheInstenceSubclass.getObject(CacheKeyPrefix.TOKEN.getValue() + token);
		String userid = user.getId();// 当前用户ID
		opinionNewsVo.setSender(userid);
		return new SuccessProtocol(opinionNewsService.saveOpinion(opinionNewsVo));
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Object delOpinion(String id, TokenBean tokenBean) {
		String token = tokenBean.getToken();
		User user = (User) cacheInstenceSubclass.getObject(CacheKeyPrefix.TOKEN.getValue() + token);
		String userid = user.getId();// 当前用户ID
		Boolean flag = opinionNewsService.delOpinion(userid, id);
		return new SuccessProtocol(flag);
	}

	@RequestMapping(value = "/mark", method = RequestMethod.POST)
	@ResponseBody
	public Object markOpinion(OpinionIdsVo idsvo) {
		List<String> ids = idsvo.getIds();
		System.out.println(ids.size());
		Boolean flag = opinionNewsService.markOpinion(ids);
		return new SuccessProtocol(flag);
	}

}

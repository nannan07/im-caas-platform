package com.allmsi.plugin.open.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.cache.CacheKeyPrefix;
import com.allmsi.plugin.open.model.vo.OpenInsertVo;
import com.allmsi.plugin.open.service.OpenService;
import com.allmsi.sys.config.CacheInstenceSubclass;
import com.allmsi.sys.model.TokenBean;
import com.allmsi.sys.model.protocol.ErrorProtocol;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.model.vo.UserVo;
import com.allmsi.sys.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Controller
@RequestMapping("/open")
public class OpenFileController {

	@Resource
	private OpenService openService;

	@Resource
	private CacheInstenceSubclass cacheInstenceSubclass;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Object openCheckToken(String token) {
		if (StrUtil.isEmpty(token)) {
			return new SuccessProtocol(false);
		}
		return new SuccessProtocol(openService.checkToken(token));
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Object openTokenCodeSelect(String token, String code) {
		if (StrUtil.isEmpty(token) || StrUtil.isEmpty(code)) {
			return new WarnProtocol("提取路径或提取码错误！");
		}
		Object object = openService.selectByTokenCode(token, code);
		if ("error".equals(object)) {
			return new WarnProtocol("提取路径或提取码错误！");
		}
		if ("Invalid".equals(object)) {
			return new WarnProtocol("提取路径或提取码失效，请与提取码提供人员联系！");
		}
		return new SuccessProtocol(object);
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	public void openDownload(String token, String code, String resourseId, HttpServletResponse response,
			HttpServletRequest request) {
		if (StrUtil.isEmpty(token) || StrUtil.isEmpty(code) || StrUtil.isEmpty(resourseId)) {
			return;
		}
		boolean flag = openService.checkDownload(token, code, resourseId);
		if (!flag) {
			return;
		}
		openService.download(resourseId, response, request);
	}

	@RequestMapping(value = "/list/select", method = RequestMethod.GET)
	@ResponseBody
	public Object openListSelect(String objectId, String receiverId, int page, int pageSize) {
		return new SuccessProtocol(openService.openListSelect(objectId, receiverId, page, pageSize));
	}

	@RequestMapping(value = "/objReceId/select", method = RequestMethod.GET)
	@ResponseBody
	public Object openListSelect(String objectId, String receiverId) {
		if (StrUtil.isEmpty(objectId) || StrUtil.isEmpty(receiverId)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(openService.openListByobjReceIdSelect(objectId, receiverId));
	}

	@RequestMapping(value = "/more/select", method = RequestMethod.GET)
	@ResponseBody
	public Object openMoreSelect(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return openService.openMoreSelect(id);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object openInsert(TokenBean tokenBean) {
		String data = tokenBean.getData();
		if (StrUtil.isEmpty(data)) {
			return new WarnProtocol();
		}

		Gson gson = new Gson();
		OpenInsertVo open = new OpenInsertVo();
		try {
			open = gson.fromJson(data, OpenInsertVo.class);
		} catch (JsonSyntaxException e) {
			return new ErrorProtocol(400);
		}
		UserVo userVo = (UserVo) cacheInstenceSubclass
				.getObject(CacheKeyPrefix.TOKEN.getValue() + tokenBean.getToken());
		return openService.insert(open.getObjectId(), open.getResourseIds(), open.getReceiverIds(), userVo.getId(),
				open.getType(), open.gettCount());
	}
}

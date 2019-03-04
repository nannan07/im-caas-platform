package com.allmsi.caas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.caas.model.vo.PjInfoVo;
import com.allmsi.caas.service.PjInfoService;
import com.allmsi.caas.service.PjPlatgormService;
import com.allmsi.caas.service.PjThemeService;
import com.allmsi.sys.controller.BaseController;
import com.allmsi.sys.model.TokenBean;
import com.allmsi.sys.model.protocol.ErrorProtocol;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.model.vo.OneToManyVo;
import com.allmsi.sys.model.vo.UserVo;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * 项目信息
 * 
 * @author sunnannan
 *
 */
@Controller
@RequestMapping("project")
public class ProjectController extends BaseController {

	@Resource
	private PjInfoService pjInfoService;

	@Resource
	private PjThemeService pjThemeService;

	@Resource
	private PjPlatgormService pjPlatgormService;
	
	@RequestMapping(value = "/list/node")
	@ResponseBody
	public Object selectByFlowNode(Integer node, int page, int pageSize) {
		return new SuccessProtocol(pjInfoService.selectByFlowNode(node, page, pageSize));
	}

	// 查询待办项目列表
	@RequestMapping(value = "/list/todo")
	@ResponseBody
	public Object selectToDoList(TokenBean tokenBean, int page, int pageSize) {
		String data = tokenBean.getData();
		UserVo userVo = getUser(tokenBean.getToken());
		PjInfoVo pjInfoVo = new PjInfoVo();
		pjInfoVo.setUserid(userVo.getId());
		pjInfoVo.setDeptid(userVo.getDeptId());
		if (StrUtil.notEmpty(data)) {
			pjInfoVo.setDeptname(data);
			pjInfoVo.setPjname(data);
		}
		return new SuccessProtocol(pjInfoService.selectList(pjInfoVo, page, pageSize));
	}

	// 查询我的项目列表
	@RequestMapping(value = "/list/my")
	@ResponseBody
	public Object selectMyList(String token, String title, int page, int pageSize) {
		UserVo userVo = getUser(token);
		PjInfoVo pjInfoVo = new PjInfoVo();
		pjInfoVo.setUserid(userVo.getId());
		pjInfoVo.setDeptid(userVo.getDeptId());
		if (StrUtil.notEmpty(title)) {
			pjInfoVo.setDeptname(title);
			pjInfoVo.setPjname(title);
			pjInfoVo.setType(title);
		}
		return new SuccessProtocol(pjInfoService.selectMyList(pjInfoVo, page, pageSize));
	}

	// 查询项目信息
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectInfo(String token, String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		UserVo userVo = getUser(token);
		return new SuccessProtocol(pjInfoService.selectInfo(id, userVo.getId(), userVo.getDeptId()));
	}

	// 查询项目信息和联系人信息
	@RequestMapping(value = "/more/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectInfoById(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(pjInfoService.selectPjInfo(id));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object saveInfo(TokenBean tokenBean) {
		String data = tokenBean.getData();
		if (StrUtil.isEmpty(data)) {
			return new WarnProtocol();
		}
		Gson gson = new Gson();
		PjInfoVo pjInfoVo = new PjInfoVo();
		try {
			pjInfoVo = gson.fromJson(data, PjInfoVo.class);
		} catch (JsonSyntaxException e) {
			return new ErrorProtocol();
		}

		String pjId = pjInfoService.saveInfo(pjInfoVo, getUser(tokenBean.getToken()));
		return StrUtil.isEmpty(pjId) ? new WarnProtocol() : new SuccessProtocol((Object) pjId);
	}

	@RequestMapping(value = "/related/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectPjRelated() {
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("themes", pjThemeService.selectAllThemeList());
		returnMap.put("plats", pjPlatgormService.selectPlat());
		returnMap.put("id", UUIDUtil.getUUID());
		return new SuccessProtocol(returnMap);
	}

	// 项目添加或者修改发行平台
	@RequestMapping(value = "/plat/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(OneToManyVo vo) {
		if (vo == null) {
			return new WarnProtocol();
		}
		String pjid = vo.getId();
		List<String> deptList = vo.getStrings();
		if (deptList.size() > 0) {
			pjPlatgormService.insertBatch(pjid, deptList);
		} else {
			pjPlatgormService.deleteByPjid(pjid);
		}
		return new SuccessProtocol(true);
	}

}

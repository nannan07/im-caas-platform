package com.allmsi.sys.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.cache.CacheKeyPrefix;
import com.allmsi.sys.config.CacheInstenceSubclass;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.model.vo.UserVo;
import com.allmsi.sys.service.UserService;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping(value = "/system/user")
public class UserController {

	@Resource
	private UserService userService;

	@Resource
	private CacheInstenceSubclass cacheInstenceSubclass;

	/** 条件查询 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object select(String data,Integer deptType,Integer page,Integer pageSize) {
		UserVo userVo = new UserVo();
		if(StrUtil.notEmpty(data)){
			userVo.setUserName(data);
		}
		if(deptType != null){
			userVo.setDeptType(deptType);
		}
		return new SuccessProtocol(userService.selectPage(userVo,page,pageSize));
	}

	/** 查询用户个人信息 */
	@RequestMapping(value = "/info/select", method = RequestMethod.GET)
	@ResponseBody
	public Object userSelect(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		UserVo userVo = userService.selectByPrimaryKey(id);
		return new SuccessProtocol(userVo);
	}

	/** 添加用户 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(UserVo userVo) {
		if (userVo == null) {
			return new WarnProtocol();
		}
		String id = userService.insert(userVo);
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol("失败");
		}
		return new SuccessProtocol("成功", id);
	}

	/** 修改用户 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object updateUser(UserVo userVo) {
		if (userVo == null || StrUtil.isEmpty(userVo.getId())) {
			return new WarnProtocol();
		}
		if (userService.updateByPrimaryKeySelective(userVo)) {
			return new SuccessProtocol("成功", userVo.getId());
		}
		return new WarnProtocol("失败");
	}

	/** 删除 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteUser(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(userService.deleteByPrimaryKey(id));
	}

	@RequestMapping(value = "/pwd/update", method = RequestMethod.POST)
	@ResponseBody
	public Object pwdUpdate(String token, String psw, String newPsw) {
		if (StrUtil.isEmpty(psw) || StrUtil.isEmpty(newPsw)) {
			return new WarnProtocol();
		}
		UserVo userVo = (UserVo) cacheInstenceSubclass.getObject(CacheKeyPrefix.TOKEN.getValue() + token);
		return userService.pwdUpdate(userVo.getId(), psw, newPsw) ? new SuccessProtocol("密码修改成功")
				: new WarnProtocol("密码修改失败");
	}

	@RequestMapping(value = "/user/dept/select", method = RequestMethod.GET)
	@ResponseBody
	public Object throughDeptSelect(String deptId) {
		if (StrUtil.isEmpty(deptId)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(userService.selectByDeptId(deptId));
	}

	@RequestMapping(value = "/user/role/select", method = RequestMethod.GET)
	@ResponseBody
	public Object throughRoleSelect(String roleId) {
		if (StrUtil.isEmpty(roleId)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(userService.selectByRoleId(roleId));
	}

}

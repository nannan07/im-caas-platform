package com.allmsi.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.model.vo.RoleVo;
import com.allmsi.sys.service.RoleService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Controller
@RequestMapping(value = "/system/role")
public class RoleController {

	@Resource
	private RoleService roleService;

	/** 通过角色名称进行查询 */
	@RequestMapping(value = "/select",method = RequestMethod.GET)
	@ResponseBody
	public Object query(RoleVo rolevo,int page, int page_size) {
		int total = roleService.queryCount(rolevo);
		List<Object> roleList = new ArrayList<Object>();
		roleList.addAll(roleService.query(rolevo,page,page_size));
		return new ListBean(total,roleList);
	}

	/**查询 */
	@RequestMapping(value = "info/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectRole(String id) {
		if(StrUtil.isEmpty(id)){
			return new WarnProtocol("失败");
		}
		return new SuccessProtocol(roleService.selectByPrimaryKey(id));
	}
	
	/** 添加角色 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Object add(RoleVo rolevo) {
		if(StrUtil.isEmpty(rolevo.getRolename())){
			return new WarnProtocol("失败");
		}
		String id = UUIDUtil.getUUID();
		rolevo.setId(id);
		if(roleService.insert(rolevo)){
			return new SuccessProtocol("成功",rolevo.getId());
		}
		return new WarnProtocol("失败");
	}

	/** 修改角色 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object updateUser(RoleVo rolevo) {
		if(StrUtil.isEmpty(rolevo.getId()) || StrUtil.isEmpty(rolevo.getRolename())){
			return new WarnProtocol("失败");
		}
		if(roleService.updateByPrimaryKeySelective(rolevo)){
			return new SuccessProtocol("成功",rolevo.getId());
		}
		return new WarnProtocol("失败");
	}

	/** 删除角色 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Object deleteUser(String id) {
		if(StrUtil.isEmpty(id)){
			return new WarnProtocol("失败");
		}
		 return new SuccessProtocol(roleService.deleteByPrimaryKey(id));
	}

}

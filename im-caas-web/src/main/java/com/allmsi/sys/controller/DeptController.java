package com.allmsi.sys.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.plugin.file.service.FileService;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.model.vo.DeptVo;
import com.allmsi.sys.service.DeptService;
import com.allmsi.sys.util.StrUtil;

/**
 * 
 * @author sunnannan
 *
 */
@Controller
@RequestMapping(value = "/system/dept")
public class DeptController {

	@Resource
	private DeptService deptService;

	@Resource
	private FileService fileService;

	/** 条件查询 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectPage(DeptVo deptVo) {
		if (deptVo == null) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(deptService.selectPage(deptVo));
	}

	/** 部门详细信息 */
	@RequestMapping(value = "info/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectDept(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		DeptVo deptVo = deptService.selectByPrimaryKey(id);
		return new SuccessProtocol(deptVo);
	}

	/** 添加 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addDept(DeptVo deptvo) {
		if (deptvo == null || StrUtil.isEmpty(deptvo.getDeptName())) {
			return new WarnProtocol();
		}
		String id = deptService.insertSelective(deptvo);
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol("失败");
		}
		return new SuccessProtocol("成功", id);
	}

	/** 修改 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object updateDept(DeptVo deptvo) {
		if (StrUtil.isEmpty(deptvo.getId()) || StrUtil.isEmpty(deptvo.getDeptName())) {
			return new WarnProtocol();
		}
		if (deptService.updateByPrimaryKeySelective(deptvo)) {
			return new SuccessProtocol("成功",deptvo.getId());
		}
		return new WarnProtocol("失败");
	}

	/** 删除*/
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delDept(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(deptService.deleteByPrimaryKey(id));
	}

	/**营业执照的文件删除*/
	@RequestMapping(value = "/licence/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(deptService.deleteByBLicenceId(id));
	}

}

package com.allmsi.caas.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allmsi.caas.dao.RegisterMapper;
import com.allmsi.caas.model.Register;
import com.allmsi.caas.model.vo.RegisterVo;
import com.allmsi.caas.service.RegisterService;
import com.allmsi.plugin.file.model.FilePo;
import com.allmsi.plugin.file.model.FileVo;
import com.allmsi.plugin.file.service.FileService;
import com.allmsi.sys.config.PropertyConfig;
import com.allmsi.sys.dao.UserDeptMapper;
import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.model.po.Dept;
import com.allmsi.sys.model.po.UserDept;
import com.allmsi.sys.model.vo.DeptVo;
import com.allmsi.sys.model.vo.UserVo;
import com.allmsi.sys.service.DeptService;
import com.allmsi.sys.service.UserService;
import com.allmsi.sys.util.MD5Util;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Resource
	private RegisterMapper registerDao;

	@Resource
	private UserService userService;

	@Resource
	private DeptService deptService;

	@Resource
	private UserDeptMapper userDeptDao;

	@Resource
	private FileService fileService;

	@Autowired
	private PropertyConfig properties;

	private final String DOWDLOAD_URL = "download_url";

	@Override
	public Object insert(RegisterVo registerVo) {
		Register register = new Register(registerVo);
		register.setPhone(registerVo.getPhone());
		register.setEmail(registerVo.getEmail());
		int msg = registerDao.checkUserInfo(register);// 手机号，邮箱
		if (msg > 0) {
			return false;
		} else {
			msg = registerCheckCpy(register.getCpyName());// 校验公司名称是否在部门表和注册表中
			if (msg == 3) {
				return false;
			} else if (msg == 1) {
				if (StrUtil.isEmpty(register.getCorporation()) || StrUtil.isEmpty(register.getCpyTel())
						|| StrUtil.isEmpty(register.getCpyAddress()) || StrUtil.isEmpty(register.getbLicenceId())) {
					return false;
				}
			}
			register.setId(UUIDUtil.getUUID());
			register.setPassword(MD5Util.encode(registerVo.getPassword()));
			register.setState(1);
			
			List<Dept> list = deptService.selectByDeptName(register.getCpyName());
			if(list != null && list.size() >0){
				Dept dept = list.get(0);
				register.setCorporation(dept.getCorporation());
				register.setCpyAddress(dept.getAddress());
				register.setCpyTel(dept.getPhone());
				register.setbLicenceId(dept.getbLicenceId());
				register.setCpyType(dept.getDeptType());
			}
			msg = registerDao.insertSelective(register);
			return (msg == 0) ? false : true;
		}
	}

	// 查询注册表的信息
	@Override
	public RegisterVo selectRegister(String id) {
		Register register = registerDao.selectByPrimaryKey(id);
		RegisterVo registerVo = new RegisterVo(register);
		if (register != null) {
			FilePo filePo = fileService.selectById(registerVo.getbLicenceId());
			FileVo fileVo = new FileVo();
			if (filePo != null) {
				fileVo.setId(filePo.getId());
				fileVo.setName(filePo.getFileName());
				fileVo.setUrl(properties.getProperty(DOWDLOAD_URL) + filePo.getId());
			}
			registerVo.setFile(fileVo);
		}
		return registerVo;
	}

	// 查询注册表的信息
	@Override
	public List<RegisterVo> selectList(RegisterVo registerVo) {
		List<Register> registerList = registerDao.selectList(new Register(registerVo));
		List<RegisterVo> registerVoList = new ArrayList<>();
		for (Register register : registerList) {
			registerVoList.add(new RegisterVo(register));
		}
		return registerVoList;
	}
	
	@Override
	public ListBean selectPage(RegisterVo registerVo,Integer page,Integer pageSize) {
		Register register = new Register(registerVo);
		register.setPages(page, pageSize);
		List<Register> registerList = registerDao.selectList(register);
		List<RegisterVo> registerVoList = new ArrayList<>();
		for (Register tempRegister : registerList) {
			registerVoList.add(new RegisterVo(tempRegister));
		}
		int total = registerDao.selectListCount(register);
		ListBean  listBean = new ListBean(total, registerVoList);
		return listBean;
	}

	@Override
	public boolean auditingRegister(String id, int state, String opinion) {
		Register register = registerDao.selectByPrimaryKey(id);
		if (2 == state) {// 审核通过
			UserVo userVo = new UserVo();
			userVo.setPhone(register.getPhone());
			userVo.setEmail(register.getEmail());
			int count = userService.checkUnique(userVo);
			if (count > 0) {
				return false;
			} else {
				userVo.setId(UUIDUtil.getUUID());
				userVo.setUserName(register.getRealName());
				userVo.setPassword(register.getPassword());
				userVo.setPhone(register.getPhone());
				userVo.setEmail(register.getEmail());
				userService.insert(userVo, false);
			}
			DeptVo deptVo = new DeptVo();
			UserDept userDept = new UserDept();
			List<Dept> dtlist = deptService.selectByDeptName(register.getCpyName());
			if (dtlist.size() == 0) {
				deptVo.setDeptName(register.getCpyName());
				deptVo.setCorporation(register.getCorporation());
				deptVo.setAddress(register.getCpyAddress());
				deptVo.setPhone(register.getCpyTel());
				deptVo.setbLicenceId(register.getbLicenceId());
				deptVo.setDeptType(register.getCpyType());
				deptVo.setId(UUIDUtil.getUUID());
				deptService.insertSelective(deptVo);
				userDept.setDeptId(deptVo.getId());
			} else {
				userDept.setDeptId(dtlist.get(0).getId());
			}
			userDept.setId(UUIDUtil.getUUID());
			userDept.setUserId(userVo.getId());
			System.out.println("userDept--"+userDept.getDeptId());
			userDeptDao.insert(userDept);
		}
		Register record = new Register();
		record.setId(id);
		record.setOpinion(opinion);
		record.setState(state);
		int msg = registerDao.auditingRegister(record);
		return (msg != 0) ? true : false;
	}

	/**
	 * 1：可注册，2:已注册，3:待审核
	 */
	@Override
	public int registerCheckCpy(String cpyName) {
		List<Dept> list = deptService.selectByDeptName(cpyName);
		if (list.size() > 0) {
			return 2;
		}
		int msg = registerDao.checkCpyInfo(cpyName);// 校验公司名称
		return msg > 0 ? 3 : 1;
	}

	@Override
	public boolean registerCheckPhone(String phone) {
		Register record = new Register();
		record.setPhone(phone);
		int msg = registerDao.checkUserInfo(record);// 校验手机号
		return !(msg > 0);
	}

	@Override
	public boolean registerCheckEmail(String email) {
		Register record = new Register();
		record.setEmail(email);
		int msg = registerDao.checkUserInfo(record);// 校验邮箱
		return !(msg > 0);
	}
}
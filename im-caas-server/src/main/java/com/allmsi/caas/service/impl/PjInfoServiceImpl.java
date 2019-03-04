package com.allmsi.caas.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.allmsi.caas.dao.PjInfoMapper;
import com.allmsi.caas.model.PjInfo;
import com.allmsi.caas.model.vo.PjCpersonVo;
import com.allmsi.caas.model.vo.PjInfoVo;
import com.allmsi.caas.model.vo.PjMoreInfoBean;
import com.allmsi.caas.model.vo.PjPPersonVO;
import com.allmsi.caas.model.vo.PjPlatGormVo;
import com.allmsi.caas.model.vo.PjProductCompanyVo;
import com.allmsi.caas.model.vo.PjThemeVo;
import com.allmsi.caas.model.vo.PjVo4List;
import com.allmsi.caas.service.PjCPersonService;
import com.allmsi.caas.service.PjCpTypeService;
import com.allmsi.caas.service.PjInfoService;
import com.allmsi.caas.service.PjPersonnelService;
import com.allmsi.caas.service.PjPlatgormService;
import com.allmsi.caas.service.PjThemeService;
import com.allmsi.caas.service.ProductCompanyService;
import com.allmsi.plugin.file.model.FilePo;
import com.allmsi.plugin.file.model.FileVo;
import com.allmsi.plugin.file.service.FileService;
import com.allmsi.plugin.flow.model.vo.FlowStateVo;
import com.allmsi.plugin.flow.service.FlowStateService;
import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.model.vo.UserVo;

@Service
public class PjInfoServiceImpl implements PjInfoService {

	@Resource
	private PjInfoMapper pjInfoDao;

	@Resource
	private PjCPersonService pjCPersonService;
	
	@Resource
	private PjPersonnelService pjPersonnelService;
	
	@Resource
	private PjCpTypeService pjCpTypeService;
	
	@Resource
	private ProductCompanyService productCompanyService;
	
	@Resource
	private PjPlatgormService pjPlatgormService;
	
	@Resource
	private PjThemeService pjDictService;
	
	@Resource
	private FileService fileService;

	@Resource
	private FlowStateService flowStateService;

	@Value("${download_url.value:null}")
	private String DOWDLOAD_URL;

	@Override
	public String saveInfo(PjInfoVo pjInfoVo, UserVo userVo) {
		// 合作类型做主表的冗余字段
		List<String> pjCpTypeList = pjInfoVo.getPjCpTypeList();
		StringBuilder sbPjCpType = new StringBuilder();
		for (String string : pjCpTypeList) {
			if (sbPjCpType.length() > 0) {
				sbPjCpType.append(",");
			}
			sbPjCpType.append(string);
		}

		int msg = 0;
		String pjid = pjInfoVo.getId();
		PjInfo info = new PjInfo(pjInfoVo);
		info.setCpTypes(sbPjCpType.toString());
		PjInfo dbPjInfo = pjInfoDao.selectByPrimaryKey(pjid);// 项目基本信息
		if (dbPjInfo == null) {
			info.setUserid(userVo.getId());
			info.setDeptid(userVo.getDeptId());
			msg = pjInfoDao.insertSelective(info);// 基本信息
		} else {
			msg = pjInfoDao.updateByPrimaryKeySelective(info); // 更新项目信息表
		}
		insertBatch(pjid, pjInfoVo);// 添加关联表信息
		return (msg != 0) ? pjid : null;
	}

	@Override
	public PjMoreInfoBean selectPjInfo(String pjid) {
		PjInfo info = pjInfoDao.selectByPrimaryKey(pjid);// 项目基本信息
		List<PjCpersonVo> cPersonVoList = pjCPersonService.selectPjCPersonList(pjid);
		PjMoreInfoBean infoBase = new PjMoreInfoBean();
		infoBase.setDeptname(info.getDeptname());
		infoBase.setcPerson(cPersonVoList);
		return infoBase;
	}

	private void insertBatch(String pjid, PjInfoVo pjInfoVo) {
		pjCpTypeService.savePjCpType(pjid, pjInfoVo.getPjCpTypeList());// 合作类型
		productCompanyService.insertBatch(pjid, pjInfoVo.getPjProductCompanyList());// 出品公司
		pjPersonnelService.addPjPersonnel(pjid,pjInfoVo.getPjPPersonList());// 项目人员
		pjCPersonService.addPjCPerson(pjid, pjInfoVo.getPjCPersonList());// 联系人
		pjDictService.saveThemeList(pjid, pjInfoVo.getPjThemeList());
	}

	@Override
	public PjInfoVo selectInfo(String id, String userId, String deptId) {
		PjInfo info = pjInfoDao.selectByPrimaryKey(id);// 项目基本信息
		if (info != null) {
			List<PjCpersonVo> pjCPersonVoList = pjCPersonService.selectPjCPersonList(id);// 联系人
			List<PjPPersonVO> pjPPersonVoList = pjPersonnelService.selectPjPersonnelListByPjId(id);// 项目人员
			List<String>  pjCpTypeList = pjCpTypeService.selectPjCpType(id);// 合作类型
			List<PjProductCompanyVo> pjPCompanyVo = productCompanyService.selectProductCompanyList(id);// 出品公司
			List<PjPlatGormVo> pjPlatgormVo = pjPlatgormService.selectByPjid(id);// 项目发行平台
			List<PjThemeVo> pjThemeVoList = pjDictService.selectThemeList(id);// 项目题材
			List<FileVo> fileList = selectFile(id);
			FlowStateVo flowState = flowStateService.selectFlowState(id, userId, deptId);	
			PjInfoVo infoBase = new PjInfoVo(info);
			infoBase.setPjCpTypeList(pjCpTypeList);
			infoBase.setPjCPersonList(pjCPersonVoList);
			infoBase.setPjPlatGorm(pjPlatgormVo);
			infoBase.setPjProductCompanyList(pjPCompanyVo);
			infoBase.setPjPPersonList(pjPPersonVoList);
			infoBase.setPjThemeList(pjThemeVoList);
			infoBase.setFileList(fileList);
			if (flowState != null) {
				infoBase.setFlowState(flowState.getNode());
			}
			return infoBase;
		}
		return null;
	}

	@Override
	public ListBean selectList(PjInfoVo pjInfoVo, int page, int pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userid", pjInfoVo.getUserid());
		param.put("deptid", pjInfoVo.getDeptid());
		param.put("data", pjInfoVo.getDeptname());
		int total = pjInfoDao.selectListCount(param);

		param.put("page", (page - 1) * pageSize);
		param.put("pageSize", pageSize);
		List<PjInfo> pjInfoList = pjInfoDao.selectList(param);
		List<PjVo4List> pjVoList = new ArrayList<PjVo4List>();
		for (PjInfo pjInfo : pjInfoList) {
			pjVoList.add(new PjVo4List(pjInfo));
		}

		return new ListBean(total, pjVoList);
	}

	@Override
	public ListBean selectMyList(PjInfoVo pjInfoVo, int page, int pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userid", pjInfoVo.getUserid());
		param.put("deptid", pjInfoVo.getDeptid());
		param.put("data", pjInfoVo.getDeptname());
		int total = pjInfoDao.selectMyListCount(param);
		
		param.put("page", (page - 1) * pageSize);
		param.put("pageSize", pageSize);
		List<PjInfo> pjInfolist = pjInfoDao.selectMyList(param);
		List<PjVo4List> pjVoList = new ArrayList<PjVo4List>();
		for (PjInfo pjInfo : pjInfolist) {
			pjVoList.add(new PjVo4List(pjInfo));
		}

		return new ListBean(total, pjVoList);
	}

	private List<FileVo> selectFile(String id) {
		List<FilePo> list = fileService.selectByObject(id);
		List<FileVo> fileList = new ArrayList<FileVo>();
		for (FilePo file : list) {
			FileVo vo = new FileVo();
			vo.setId(file.getId());
			vo.setName(file.getFileName());
			vo.setLength(file.getFileSize());
			vo.setUrl(DOWDLOAD_URL + file.getId());
			vo.setType(file.getObjectType());
			vo.setStatus(file.getFileStatus());
			fileList.add(vo);
		}
		return fileList;
	}

	@Override
	public ListBean selectByFlowNode(Integer node, int page, int pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("node", node);
		param.put("page", (page - 1) * pageSize);
		param.put("pageSize", pageSize);
		int total = pjInfoDao.selectByFlowNodeCount(param);
		List<PjInfo> pjInfoList = pjInfoDao.selectByFlowNode(param);
		List<PjVo4List> pjVoList = new ArrayList<PjVo4List>();
		for (PjInfo pjInfo : pjInfoList) {
			pjVoList.add(new PjVo4List(pjInfo));
		}

		return new ListBean(total, pjVoList);
	}

}

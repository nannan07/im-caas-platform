package com.allmsi.caas.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.allmsi.caas.model.PjInfo;
import com.allmsi.plugin.file.model.FileVo;

public class PjInfoVo {

	private String id;

	private String pjname;

	private String introduction;

	private String userid;

	private String deptid;

	private String type;

	private Long pCost;

	private Date createtime;

	private String deptname;

	private String senderid;

	private List<PjPPersonVO> pjPPersonList = new ArrayList<PjPPersonVO>();// 项目人员

	private List<PjProductCompanyVo> pjProductCompanyList = new ArrayList<PjProductCompanyVo>();// 出品公司

	private List<PjCpersonVo> pjCPersonList = new ArrayList<PjCpersonVo>();// 联系人

	private List<String> pjCpTypeList = new ArrayList<String>();// 合作类型

	private List<PjPlatGormVo> pjPlatGorm = new ArrayList<PjPlatGormVo>();// 发行平台
	
	private List<PjThemeVo> pjThemeList = new ArrayList<PjThemeVo>();// 题材

	private List<FileVo> fileList = new ArrayList<FileVo>();

	private int flowState;// 流程状态

	public PjInfoVo() {

	}
	
	public PjInfoVo(PjInfo pjinfo) {
		if(pjinfo != null){
			this.id = pjinfo.getId();
			this.pjname = pjinfo.getPjname();
			this.introduction = pjinfo.getIntroduction();
			this.deptid = pjinfo.getDeptid();
			this.type = pjinfo.getType();
			this.pCost = pjinfo.getProductCost();
			this.createtime = pjinfo.getCreatetime();
			this.deptname = pjinfo.getDeptname();
			this.senderid = pjinfo.getSenderid();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPjname() {
		return pjname;
	}

	public void setPjname(String pjname) {
		this.pjname = pjname;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public List<PjPPersonVO> getPjPPersonList() {
		return pjPPersonList;
	}

	public void setPjPPersonList(List<PjPPersonVO> pjPPersonList) {
		this.pjPPersonList = pjPPersonList;
	}

	public List<PjProductCompanyVo> getPjProductCompanyList() {
		return pjProductCompanyList;
	}

	public void setPjProductCompanyList(List<PjProductCompanyVo> pjProductCompanyList) {
		this.pjProductCompanyList = pjProductCompanyList;
	}

	public List<PjCpersonVo> getPjCPersonList() {
		return pjCPersonList;
	}

	public void setPjCPersonList(List<PjCpersonVo> pjCPersonList) {
		this.pjCPersonList = pjCPersonList;
	}

	public List<PjPlatGormVo> getPjPlatGorm() {
		return pjPlatGorm;
	}

	public void setPjPlatGorm(List<PjPlatGormVo> pjPlatGorm) {
		this.pjPlatGorm = pjPlatGorm;
	}

	public int getFlowState() {
		return flowState;
	}

	public void setFlowState(int flowState) {
		this.flowState = flowState;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getPjCpTypeList() {
		return pjCpTypeList;
	}

	public void setPjCpTypeList(List<String> pjCpTypeList) {
		this.pjCpTypeList = pjCpTypeList;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	public List<FileVo> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVo> fileList) {
		this.fileList = fileList;
	}

	public Long getpCost() {
		return pCost;
	}

	public void setpCost(Long pCost) {
		this.pCost = pCost;
	}

	public List<PjThemeVo> getPjThemeList() {
		return pjThemeList;
	}

	public void setPjThemeList(List<PjThemeVo> pjThemeList) {
		this.pjThemeList = pjThemeList;
	}
	
}
package com.allmsi.caas.model;

import java.util.Date;

import com.allmsi.caas.model.vo.PjInfoVo;

public class PjInfo {

	private String id;

	private String pjname;

	private String introduction;

	private String userid;

	private String deptid;

	private String type;

	private Long productCost;

	private Date createtime;

	private Date updatetime;

	private int deleflag;

	private String deptname;

	private String cpTypes;

	private String flowState;

	private int deptType;

	private String senderid;
	
	private String senderName;
	
	private String receiverId;
	
	private String receiverName;
	
	private String nodeTime;

	public PjInfo() {

	}

	public PjInfo(PjInfoVo pjInfoVo) {
		if(pjInfoVo != null){
			this.id = pjInfoVo.getId();
			this.userid = pjInfoVo.getUserid();
			this.deptid = pjInfoVo.getDeptid();
			this.introduction = pjInfoVo.getIntroduction();
			this.pjname = pjInfoVo.getPjname();
			this.type = pjInfoVo.getType();
			this.productCost = pjInfoVo.getpCost();
		}
	}

	public String getCpTypes() {
		return cpTypes;
	}

	public void setCpTypes(String cpTypes) {
		this.cpTypes = cpTypes;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getPjname() {
		return pjname;
	}

	public void setPjname(String pjname) {
		this.pjname = pjname == null ? null : pjname.trim();
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction == null ? null : introduction.trim();
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid == null ? null : deptid.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getFlowState() {
		return flowState;
	}

	public void setFlowState(String flowState) {
		this.flowState = flowState;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDeptType() {
		return deptType;
	}

	public void setDeptType(int deptType) {
		this.deptType = deptType;
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

	public int getDeleflag() {
		return deleflag;
	}

	public void setDeleflag(int deleflag) {
		this.deleflag = deleflag;
	}

	public Long getProductCost() {
		return productCost;
	}

	public void setProductCost(Long productCost) {
		this.productCost = productCost;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getNodeTime() {
		return nodeTime;
	}

	public void setNodeTime(String nodeTime) {
		this.nodeTime = nodeTime;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

}
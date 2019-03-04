package com.allmsi.caas.model.vo;

import java.util.Date;

import com.allmsi.caas.model.PjInfo;

/**
 * 项目列表
 * 
 * @author sunnannan
 *
 */
public class PjVo4List {

	private String id;

	private String pjName;

	private String introduction;

	private String deptId;

	private String type;

	private Date cTime;

	private Date uTime;

	private String cpType;

	private String flowState;

	private String deptName;
	
	private String senderId;

	private String senderName;
	
	private String receiverId;

	private String receiverName;
	
	private String nodeTime;

	public PjVo4List() {

	}

	public PjVo4List(PjInfo pjInfo) {
		if (pjInfo != null) {
			this.id = pjInfo.getId();
			this.pjName = pjInfo.getPjname();
			this.introduction = pjInfo.getIntroduction();
			this.type = pjInfo.getType();
			this.deptId = pjInfo.getDeptid();
			this.cTime = pjInfo.getCreatetime();
			this.uTime = pjInfo.getUpdatetime();
			this.cpType = pjInfo.getCpTypes();
			this.flowState = pjInfo.getFlowState();
			this.deptName = pjInfo.getDeptname();
			this.senderId = pjInfo.getSenderid();
			this.senderName = pjInfo.getSenderName();
			this.receiverId = pjInfo.getReceiverId();
			this.receiverName = pjInfo.getReceiverName();
			this.nodeTime = pjInfo.getNodeTime();
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPjName() {
		return pjName;
	}

	public void setPjName(String pjName) {
		this.pjName = pjName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	public String getCpType() {
		return cpType;
	}

	public void setCpType(String cpType) {
		this.cpType = cpType;
	}

	public String getFlowState() {
		return flowState;
	}

	public void setFlowState(String flowState) {
		this.flowState = flowState;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
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

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

}
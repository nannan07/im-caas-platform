package com.allmsi.plugin.flow.model.vo;

import java.util.Date;

import com.allmsi.plugin.flow.model.FlowStateLog;

public class FlowStateLogVo {
	
	private String objectName;

	private int node;

	private String sName;

	private String rName;

	private int type;

	private Date cTime;

	private String opinion;

	public FlowStateLogVo() {

	}

	public FlowStateLogVo(FlowStateLog flowStateLog) {
		if (flowStateLog != null) {
			this.objectName = flowStateLog.getObjectname();
			this.node = flowStateLog.getNode();
			this.sName = flowStateLog.getSendername();
			this.rName = flowStateLog.getReceiveername();
			this.type = flowStateLog.getType();
			this.cTime = flowStateLog.getCreatetime();
			this.opinion = flowStateLog.getOpinion();
		}
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public int getNode() {
		return node;
	}

	public void setNode(int node) {
		this.node = node;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
}

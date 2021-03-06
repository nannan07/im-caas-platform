package com.allmsi.plugin.flow.model;

import java.util.Date;

import com.allmsi.sys.model.PageBean;

public class FlowStateLog extends PageBean{
	private String id;

	private String objectid;
	
	private String objectname;

	private int node;

	private String senderid;

	private String receiveerid;

	private String sendername;

	private String receiveername;

	private int type;

	private Date createtime;

	private Date updatetime;

	private int delectflag;

	private String opinion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid == null ? null : objectid.trim();
	}

	public String getObjectname() {
		return objectname;
	}

	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid == null ? null : senderid.trim();
	}

	public String getReceiveerid() {
		return receiveerid;
	}

	public void setReceiveerid(String receiveerid) {
		this.receiveerid = receiveerid == null ? null : receiveerid.trim();
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

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public void setNode(int node) {
		this.node = node;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDelectflag() {
		return delectflag;
	}

	public void setDelectflag(int delectflag) {
		this.delectflag = delectflag;
	}

	public int getNode() {
		return node;
	}

	public int getType() {
		return type;
	}

	public String getSendername() {
		return sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	public String getReceiveername() {
		return receiveername;
	}

	public void setReceiveername(String receiveername) {
		this.receiveername = receiveername;
	}

}
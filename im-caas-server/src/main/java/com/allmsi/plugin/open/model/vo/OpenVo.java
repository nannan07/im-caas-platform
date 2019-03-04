package com.allmsi.plugin.open.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.allmsi.plugin.open.model.OpenPo;

public class OpenVo {

	private String id;

	private String token;

	private String code;

	private String objectId;

	private String receiverId;

	private String objectName;

	private String receiverName;

	private String type;

	private Date ctime;

	private int tcount;

	private int ccount;

	private Date failUretime;

	private int status;

	private List<Object> list = new ArrayList<Object>();
	
	private String url;

	public OpenVo() {

	}

	public OpenVo(OpenPo openPo) {
		if (openPo != null) {
			this.id = openPo.getId();
			this.token = openPo.getToken();
			this.code = openPo.getCode();
			this.objectName = openPo.getObjectName();
			this.receiverName = openPo.getReceiverName();
			this.type = openPo.getType();
			this.ctime = openPo.getCtime();
			this.failUretime = openPo.getFailUretime();
			this.tcount = openPo.getTcount();
			this.ccount = openPo.getCcount();
			this.status = openPo.getStatus();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public int getTcount() {
		return tcount;
	}

	public void setTcount(int tcount) {
		this.tcount = tcount;
	}

	public int getCcount() {
		return ccount;
	}

	public void setCcount(int ccount) {
		this.ccount = ccount;
	}

	public Date getFailUretime() {
		return failUretime;
	}

	public void setFailUretime(Date failUretime) {
		this.failUretime = failUretime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

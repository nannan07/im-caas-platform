package com.allmsi.plugin.open.model;

import java.util.Date;

public class OpenPo {
	private String id;

    private String token;

    private String code;

    private String objectid;

    private String receiveid;

    private String cuser;

    private String type;

    private Date ctime;
    
    private Date utime;

    private int del;

    private int tcount;

    private int ccount;
    
    private Date failUretime;
    
    private int status;
    
    private String objectName;
    
    private String receiverName;
    
    public OpenPo(){
    	
    }
    
	public OpenPo(String id, String token, String code, String objectid, String receiveid, String cuser, String type,
			int tcount) {
		this.id = id;
		this.token = token;
		this.code = code;
		this.objectid = objectid;
		this.receiveid = receiveid;
		this.cuser = cuser;
		this.type = type;
		this.tcount = tcount;
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

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public String getReceiveid() {
		return receiveid;
	}

	public void setReceiveid(String receiveid) {
		this.receiveid = receiveid;
	}

	public String getCuser() {
		return cuser;
	}

	public void setCuser(String cuser) {
		this.cuser = cuser;
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

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
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

	public Date getUtime() {
		return utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}
}
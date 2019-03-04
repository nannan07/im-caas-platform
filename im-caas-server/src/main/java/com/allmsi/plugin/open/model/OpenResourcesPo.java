package com.allmsi.plugin.open.model;

import java.util.Date;

public class OpenResourcesPo {
    private String id;

    private String openid;

    private String resourceid;

    private Date ctime;

    private Date utime;

    private Boolean del;
    
    public OpenResourcesPo(){
    	
    }

    public OpenResourcesPo(String id, String openid, String resourceid) {
		this.id = id;
		this.openid = openid;
		this.resourceid = resourceid;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid == null ? null : resourceid.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}
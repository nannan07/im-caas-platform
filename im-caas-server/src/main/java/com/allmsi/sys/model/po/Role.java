package com.allmsi.sys.model.po;

import java.util.Date;

public class Role {
    private String id;

    private String rolename;

    private Date createtime;

    private Date updatetime;

    private int deleteflag;
    
    private String userid;
    
    private String loginname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
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

	public int getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(int deleteflag) {
		this.deleteflag = deleteflag;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
}
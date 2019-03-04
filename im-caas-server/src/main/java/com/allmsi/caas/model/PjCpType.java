package com.allmsi.caas.model;

import java.util.Date;

/**
 * 合作类型
 * @author sunnannan
 *
 */
public class PjCpType {
	
    private String id;

    private String pjid;

    private String type;

    private Date createtime;

    private Date updatetime;

    private int deleteflag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPjid() {
        return pjid;
    }

    public void setPjid(String pjid) {
        this.pjid = pjid == null ? null : pjid.trim();
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

}
package com.allmsi.caas.model;

import java.util.Date;

import com.allmsi.caas.model.vo.PjCpersonVo;

/**
 * 联系人
 * @author sunnannan
 *
 */
public class PjCperson {
    private String id;

    private String pjid;

    private String name;

    private String phone;
    
    private Integer sort;

    private Date createtime;

    private Date updatetime;

    private int deleflag;
    
    public PjCperson() {

	}

	public PjCperson(PjCpersonVo pjCpersonVo) {
		this.name = pjCpersonVo.getName();
		this.phone = pjCpersonVo.getPhone();
		this.sort = pjCpersonVo.getSort();
	}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public int getDeleflag() {
		return deleflag;
	}

	public void setDeleflag(int deleflag) {
		this.deleflag = deleflag;
	}

}
package com.allmsi.caas.model;

import java.util.Date;

import com.allmsi.caas.model.vo.PjProductCompanyVo;

/**
 * 出品公司
 * 
 * @author sunnannan
 *
 */
public class PjProductCompany {
	private String id;

	private String pjid;

	private String company;

	private String type;

	private Integer sort;

	private Date createtime;

	private Date updatetime;

	private int deleteflag;

	public PjProductCompany() {

	}

	public PjProductCompany(PjProductCompanyVo productCompanyVo) {
		if (productCompanyVo != null) {
			this.company = productCompanyVo.getCompany();
			this.type = productCompanyVo.getType();
			this.sort = productCompanyVo.getSort();
		}
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company == null ? null : company.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
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

	public int getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(int deleteflag) {
		this.deleteflag = deleteflag;
	}
}
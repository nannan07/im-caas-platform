package com.allmsi.caas.model;

import java.util.Date;

import com.allmsi.caas.model.vo.PjPPersonVO;

/**
 * 项目人员表
 * 
 * @author sunnannan
 *
 */
public class PjPperson {
	private String id;

	private String pjid;

	private String name;

	private String role;

	private Integer sort;

	private Date createtime;

	private Date updatetime;

	private int deleflag;

	public PjPperson() {

	}

	public PjPperson(PjPPersonVO pjPPersonVO) {
		if (pjPPersonVO != null) {
			this.name = pjPPersonVO.getName();
			this.role = pjPPersonVO.getRole();
			this.sort = pjPPersonVO.getSort();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role == null ? null : role.trim();
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
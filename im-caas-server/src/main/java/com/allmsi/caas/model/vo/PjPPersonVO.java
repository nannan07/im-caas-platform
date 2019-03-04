package com.allmsi.caas.model.vo;

import com.allmsi.caas.model.PjPperson;

/**
 * 项目人员表
 * 
 * @author sunnannan
 *
 */
public class PjPPersonVO {

	private String name;

	private String role;
	
	private Integer sort;

	public PjPPersonVO() {

	}

	public PjPPersonVO(PjPperson personnel) {
		if (personnel != null) {
			this.name = personnel.getName();
			this.role = personnel.getRole();
			this.sort = personnel.getSort();
		}
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
}
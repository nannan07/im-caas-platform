package com.allmsi.caas.model.vo;

import com.allmsi.caas.model.PjCperson;

/**
 * 联系人
 * 
 * @author sunnannan
 *
 */
public class PjCpersonVo {

	private String name;

	private String phone;
	
	private Integer sort;

	public PjCpersonVo() {

	}

	public PjCpersonVo(PjCperson pjCperson) {
		this.name = pjCperson.getName();
		this.phone = pjCperson.getPhone();
		this.sort = pjCperson.getSort();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}

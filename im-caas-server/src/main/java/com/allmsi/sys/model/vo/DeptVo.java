package com.allmsi.sys.model.vo;

import java.io.Serializable;
import java.util.Date;

import com.allmsi.sys.model.PageBean;
import com.allmsi.sys.model.po.Dept;

public class DeptVo extends PageBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;

    private String deptCode;

    private String deptName;

    private String parentid;

    private String phone;

    private String corporation;

    private String description;

    private String address;

    private String bLicenceId;

    private Integer deptType;

    private Integer sort;

    private Date cTime;
	
	
	public DeptVo(){
		
	}
	
	public DeptVo(Dept dept) {
    	if (dept != null) {
	    	this.id = dept.getId();
	    	this.deptCode = dept.getDeptCode();
	    	this.deptName = dept.getDeptName();
	    	this.parentid = dept.getParentid();
	    	this.phone = dept.getPhone();
	    	this.corporation = dept.getCorporation();
	    	this.description = dept.getDescription();
	    	this.address = dept.getAddress();
	    	this.bLicenceId = dept.getbLicenceId();
	    	this.cTime = dept.getcTime();
	    	this.deptType = dept.getDeptType();
	    	this.sort = dept.getSort();
    	}
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getbLicenceId() {
		return bLicenceId;
	}

	public void setbLicenceId(String bLicenceId) {
		this.bLicenceId = bLicenceId;
	}

	public Integer getDeptType() {
		return deptType;
	}

	public void setDeptType(Integer deptType) {
		this.deptType = deptType;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

}
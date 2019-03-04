package com.allmsi.sys.model.po;

import java.util.Date;

import com.allmsi.sys.model.PageBean;
import com.allmsi.sys.model.vo.DeptVo;

public class Dept extends PageBean implements Cloneable {
	
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

    private Date uTime;

    private int del;

	public Dept() {
		
	}
	
	public Dept(DeptVo deptVo) {
		super(deptVo);
    	if (deptVo != null) {
	    	this.id = deptVo.getId();
	    	this.deptCode = deptVo.getDeptCode();
	    	this.deptName = deptVo.getDeptName();
	    	this.parentid = deptVo.getParentid();
	    	this.phone = deptVo.getPhone();
	    	this.corporation = deptVo.getCorporation();
	    	this.description = deptVo.getDescription();
	    	this.address = deptVo.getAddress();
	    	this.bLicenceId = deptVo.getbLicenceId();
	    	this.deptType = deptVo.getDeptType();
	    	this.sort = deptVo.getSort();
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

	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
    
	@Override
	public Object clone() {
		Dept dept = null;
		try {
			dept = (Dept) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return dept;
	}
}
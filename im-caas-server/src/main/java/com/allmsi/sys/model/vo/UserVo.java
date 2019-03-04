package com.allmsi.sys.model.vo;

import java.io.Serializable;
import java.util.Date;

import com.allmsi.sys.model.po.User;

public class UserVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
    
    private String userName;

    private String password;

    private String phone;

    private String email;

    private Integer sex;
    
    private Integer sort;
    
	private Date cTime;

    private String deptId;

    private Integer deptType;
    
    private String deptName;
    
    private String token;
    
    public UserVo() {
    	
    }
    
    public UserVo(User user) {
    	if (user != null) {
	    	this.id = user.getId();
	    	this.userName = user.getUserName();
	    	this.phone = user.getPhone();
	    	this.email = user.getEmail();
	    	this.sex = user.getSex();
	    	this.sort = user.getSort();
	    	this.cTime = user.getcTime();
	    	this.deptId = user.getDeptId();
	    	this.deptType = user.getDeptType();
	    	this.deptName = user.getDeptName();
    	}
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
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

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Integer getDeptType() {
		return deptType;
	}

	public void setDeptType(Integer deptType) {
		this.deptType = deptType;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

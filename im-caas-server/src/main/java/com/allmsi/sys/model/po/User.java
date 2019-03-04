package com.allmsi.sys.model.po;

import java.util.Date;

import com.allmsi.sys.model.PageBean;
import com.allmsi.sys.model.vo.UserVo;

public class User extends PageBean implements Cloneable {

	private String id;

	private String userName;

	private String loginName;

	private String password;

	private String phone;

	private String email;

	private Integer sex;

	private Integer sort;

	private Date cTime;

	private Date uTime;

	private int del;

	private String deptId;

	private String deptName;

	private Integer deptType;
	
	public User() {
		
	}
	
	public User(UserVo userVo) {
		if (userVo != null) { 
	    	this.id = userVo.getId();
	    	this.userName = userVo.getUserName();
	    	this.password = userVo.getPassword();
	    	this.phone = userVo.getPhone();
	    	this.email = userVo.getEmail();
	    	this.sex = userVo.getSex();
	    	this.sort = userVo.getSort();
	    	this.deptType = userVo.getDeptType();
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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getDeptType() {
		return deptType;
	}

	public void setDeptType(Integer deptType) {
		this.deptType = deptType;
	}

	@Override
	public Object clone() {
		User user = null;
		try {
			user = (User) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return user;
	}

}
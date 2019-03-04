package com.allmsi.caas.model;

import java.util.Date;

import com.allmsi.caas.model.vo.RegisterVo;
import com.allmsi.sys.model.PageBean;

public class Register extends PageBean implements Cloneable {

	private String id;

	private String realName;

	private String loginName;

	private String password;

	private String phone;

	private String email;

	private String cpyName;

	private String corporation;

	private String cpyAddress;

	private String cpyTel;

	private String bLicenceId;

	private Integer cpyType;

	private String opinion;

	private Integer state;

	private String deptId;

	private Date cTime;

	private Date uTime;

	public Register() {

	}

	public Register(RegisterVo registerVo) {
		if (registerVo != null) {
			this.realName = registerVo.getRealName();
			this.password = registerVo.getPassword();
			this.phone = registerVo.getPhone();
			this.email = registerVo.getEmail();
			this.cpyName = registerVo.getCpyName();
			this.corporation = registerVo.getCorporation();
			this.cpyAddress = registerVo.getCpyAddress();
			this.cpyTel = registerVo.getCpyTel();
			this.bLicenceId = registerVo.getbLicenceId();
			this.cpyType = registerVo.getCpyType();
			this.state = registerVo.getState();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public String getCpyName() {
		return cpyName;
	}

	public void setCpyName(String cpyName) {
		this.cpyName = cpyName;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getCpyAddress() {
		return cpyAddress;
	}

	public void setCpyAddress(String cpyAddress) {
		this.cpyAddress = cpyAddress;
	}

	public String getCpyTel() {
		return cpyTel;
	}

	public void setCpyTel(String cpyTel) {
		this.cpyTel = cpyTel;
	}

	public String getbLicenceId() {
		return bLicenceId;
	}

	public void setbLicenceId(String bLicenceId) {
		this.bLicenceId = bLicenceId;
	}

	public Integer getCpyType() {
		return cpyType;
	}

	public void setCpyType(Integer cpyType) {
		this.cpyType = cpyType;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
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

	@Override
	public Object clone() {
		Register register = null;
		try {
			register = (Register) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return register;
	}
}
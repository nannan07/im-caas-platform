package com.allmsi.caas.model.vo;

import java.io.Serializable;
import java.util.Date;

import com.allmsi.caas.model.Register;
import com.allmsi.plugin.file.model.FileVo;
import com.allmsi.sys.util.StrUtil;

public class RegisterVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;

	private String realName;

	private String password;

	private String phone;

	private String email;

	private String cpyName;

	private String corporation;

	private String cpyAddress;

	private String cpyTel;

	private String bLicenceId;

	private Integer cpyType;

	private Integer state;
    
    private String opinion;
	
	private Date cTime;
	
	private Integer deptState;

	private FileVo file;

	public RegisterVo() {

	}

	public RegisterVo(Register register) {
		this.id = register.getId();
		this.realName = register.getRealName();
		this.phone = register.getPhone();
		this.email = register.getEmail();
		this.cpyName = register.getCpyName();
		this.corporation = register.getCorporation();
		this.cpyAddress = register.getCpyAddress();
		this.cpyTel = register.getCpyTel();
		this.bLicenceId = register.getbLicenceId();
		this.cpyType = register.getCpyType();
		this.opinion = register.getOpinion();
		this.state = register.getState();
		this.cTime = register.getcTime();
		this.deptState = StrUtil.isEmpty(register.getDeptId()) ? 0 : 1;
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

	public FileVo getFile() {
		return file;
	}

	public void setFile(FileVo file) {
		this.file = file;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public Integer getDeptState() {
		return deptState;
	}

	public void setDeptState(Integer deptState) {
		this.deptState = deptState;
	}

}

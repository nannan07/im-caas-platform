package com.allmsi.sys.model.po;

public class DeptUserRole {

	private String userid;
	
	private String deptid;
	
	private String roleid;
	
	private String loginname;
	
	private String username;
	
	private String deptcode;
	
	private String deptname;
	
	private String rolename;
	
	private int deleteflag;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getDeptid() {
		return deptid;
	}
	
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	
	public String getRoleid() {
		return roleid;
	}
	
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	public String getLoginname() {
		return loginname;
	}
	
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	public String getDeptcode() {
		return deptcode;
	}
	
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	
	public String getDeptname() {
		return deptname;
	}
	
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	
	public String getRolename() {
		return rolename;
	}
	
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public int getDeleteflag() {
		return deleteflag;
	}
	public void setDeleteflag(int deleteflag) {
		this.deleteflag = deleteflag;
	}
	
}

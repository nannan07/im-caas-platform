package com.allmsi.caas.model;

import java.util.Date;

/**
 * 项目发行平台
 * @author sunnannan
 *
 */
public class PjPlatgorm {
	
    private String id;

    private String pjId;

    private String deptId;
    
    private String deptName;

    private Date cTime;

    private Date uTime;

    private int del;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPjId() {
		return pjId;
	}

	public void setPjId(String pjId) {
		this.pjId = pjId;
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
}
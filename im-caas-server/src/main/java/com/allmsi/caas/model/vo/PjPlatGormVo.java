package com.allmsi.caas.model.vo;

import com.allmsi.caas.model.PjPlatgorm;

public class PjPlatGormVo {

	private String deptId;

	private String deptName;

	public PjPlatGormVo() {

	}

	public PjPlatGormVo(PjPlatgorm pjPlatgorm) {
		if(pjPlatgorm != null) {
			this.deptId = pjPlatgorm.getDeptId();
			this.deptName = pjPlatgorm.getDeptName();
		}
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

}
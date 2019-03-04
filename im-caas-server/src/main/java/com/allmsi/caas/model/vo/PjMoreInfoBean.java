package com.allmsi.caas.model.vo;

import java.util.List;

/**
 * 查询更多
 * @author sunnannan
 *
 */
public class PjMoreInfoBean {

	private String deptname;
	
	private List<PjCpersonVo> cPerson;

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public List<PjCpersonVo> getcPerson() {
		return cPerson;
	}

	public void setcPerson(List<PjCpersonVo> cPerson) {
		this.cPerson = cPerson;
	}
}

package com.allmsi.caas.model;

import java.util.Date;

import com.allmsi.caas.model.vo.PjThemeVo;

public class PjTheme {
	private String id;

	private String pjId;

	private String dictId;

	private Date cTime;

	private Date uTime;

	private Boolean del;

	private String name;
	
	private Integer sort;

	public PjTheme() {

	}

	public PjTheme(PjThemeVo dictVo) {
		if (dictVo != null) {
			this.dictId = dictVo.getDictId();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getPjId() {
		return pjId;
	}

	public void setPjId(String pjId) {
		this.pjId = pjId == null ? null : pjId.trim();
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId == null ? null : dictId.trim();
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

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
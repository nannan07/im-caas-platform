package com.allmsi.sys.model.vo;

import com.allmsi.sys.model.po.Dict;

public class DictVo {
	
	private String id;

	private String name;
	
	private String type;
	
	private Integer sort;

	public DictVo() {

	}

	public DictVo(Dict dict) {
		if(dict != null) {
			this.id = dict.getId();
			this.name = dict.getName();
			this.type = dict.getType();
			this.sort = dict.getSort();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}

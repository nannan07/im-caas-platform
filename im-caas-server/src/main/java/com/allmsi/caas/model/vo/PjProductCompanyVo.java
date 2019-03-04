package com.allmsi.caas.model.vo;

import com.allmsi.caas.model.PjProductCompany;

public class PjProductCompanyVo {

	private String company;
	
	private String type;
	
	private Integer sort;
	
	public PjProductCompanyVo(){
		
	}
	
	public PjProductCompanyVo(PjProductCompany productCompany){
		if(productCompany != null){
		 this.company = productCompany.getCompany();
		 this.type = productCompany.getType();
		 this.sort = productCompany.getSort();
		}
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

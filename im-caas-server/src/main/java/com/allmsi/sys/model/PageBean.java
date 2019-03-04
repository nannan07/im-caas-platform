package com.allmsi.sys.model;

public class PageBean {

	private Integer pageSize;

	private Integer page;

	public PageBean() {

	}

	public PageBean(PageBean pageBean) {
		if (pageBean != null) {
			this.pageSize = pageBean.pageSize;
			this.page = pageBean.page;
		}
		this.pageSize = 10;
		this.page = 0;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPages(Integer page,Integer pageSize) {
		if(page == null || pageSize == null){
			page = 1;
			pageSize = 10;
		}
		if (page > 0 && pageSize > 0) {
			this.page = (page - 1) * pageSize;
			this.pageSize = pageSize;
		} else {
			this.page = 0;
			this.pageSize = 10;
		}
	}

}

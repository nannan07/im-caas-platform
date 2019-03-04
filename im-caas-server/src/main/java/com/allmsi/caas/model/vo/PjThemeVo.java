package com.allmsi.caas.model.vo;

import com.allmsi.caas.model.PjTheme;
import com.allmsi.sys.model.vo.DictVo;

public class PjThemeVo {

    private String dictId;

	private String name;
	
	private Integer sort;
    
    public PjThemeVo () {
    	
    }

    public PjThemeVo(DictVo dictVo){
    	if(dictVo != null) {
    		this.dictId = dictVo.getId();
    		this.name = dictVo.getName();
    		this.sort = dictVo.getSort();
    	}
    }
    
    public PjThemeVo(PjTheme pjTheme){
    	if(pjTheme != null) {
    		this.dictId = pjTheme.getDictId();
    		this.name = pjTheme.getName();
    		this.sort = pjTheme.getSort();
    	}
    }

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
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

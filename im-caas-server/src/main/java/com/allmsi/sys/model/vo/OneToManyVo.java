package com.allmsi.sys.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 一对多添加
 * @author sunnannan
 *
 */
public class OneToManyVo {

	private String id;
	
	List<String> strings = new ArrayList<String>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}
	
}

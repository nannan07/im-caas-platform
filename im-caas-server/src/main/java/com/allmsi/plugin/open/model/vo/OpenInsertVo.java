package com.allmsi.plugin.open.model.vo;

import java.util.ArrayList;
import java.util.List;

public class OpenInsertVo {

	private String objectId;
	
	private List<String> resourseIds = new ArrayList<String>();
	
	private List<String> receiverIds = new ArrayList<String>();
	
	private String type;
	
	private Integer tCount;

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public List<String> getResourseIds() {
		return resourseIds;
	}

	public void setResourseIds(List<String> resourseIds) {
		this.resourseIds = resourseIds;
	}

	public List<String> getReceiverIds() {
		return receiverIds;
	}

	public void setReceiverIds(List<String> receiverIds) {
		this.receiverIds = receiverIds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer gettCount() {
		return tCount;
	}

	public void settCount(Integer tCount) {
		this.tCount = tCount;
	}

}

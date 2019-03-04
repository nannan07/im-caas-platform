package com.allmsi.plugin.open.model.vo;

public class OpenUrl {

	private String url;

	private String code;

	private String receiverId;

	public OpenUrl() {

	}

	public OpenUrl(String url, String code, String receiverId) {
		this.url = url;
		this.code = code;
		this.receiverId = receiverId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
}

package com.allmsi.plugin.open.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allmsi.sys.model.ListBean;

public interface OpenService {

	boolean checkToken(String token);

	Object insert(String objectId, List<String> resourseIds, List<String> receiverIds, String cUser, String type,
			Integer tCount);

	Object selectByTokenCode(String token, String code);
	
	ListBean openListByobjReceIdSelect(String objectId, String receiverId);

	ListBean openListSelect(String objectId, String receiverId, int page, int pageSize);

	Object openMoreSelect(String id);

	boolean checkDownload(String token, String code, String resourseId);

	void download(String resourseId, HttpServletResponse response, HttpServletRequest request);

}

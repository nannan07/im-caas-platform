package com.allmsi.plugin.open.service;

import java.util.List;

public interface OpenSelect {

	public List<Object> resourceSelect(String objectId);

	public List<Object> resourceSelect(List<String> reseourcesIds);

}

package com.allmsi.plugin.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.plugin.flow.model.FlowTransactor;

public interface FlowRuleMapper {

	List<FlowTransactor> selectNodeReceiverPj(Map<String,Object> map);
}

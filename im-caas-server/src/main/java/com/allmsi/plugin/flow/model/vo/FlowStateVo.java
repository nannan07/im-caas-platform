package com.allmsi.plugin.flow.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.allmsi.plugin.flow.model.FlowState;
import com.allmsi.plugin.flow.model.FlowTransactor;

public class FlowStateVo {
    
    private String flowcode;

    private String objectid;

    private int node;

    private String senderid;

	private List<FlowTransactor> transactorList = new ArrayList<FlowTransactor>();
    
    private String opinion;
    
    private String senderDept;
    
    public FlowStateVo () {
    	
    }
    
    public FlowStateVo(FlowState flowState){
    	if(flowState != null){
    		this.objectid=flowState.getObjectid();
    		this.node =flowState.getNode();
    		this.senderid = flowState.getSenderid();
    		this.opinion = flowState.getOpinion();
    	}
    }

    public String getFlowcode() {
		return flowcode;
	}

	public void setFlowcode(String flowcode) {
		this.flowcode = flowcode;
	}

	public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid == null ? null : objectid.trim();
    }

    public int getNode() {
		return node;
	}

	public void setNode(int node) {
		this.node = node;
	}

	public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid == null ? null : senderid.trim();
    }

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
    
    public List<FlowTransactor> getTransactorList() {
		return transactorList;
	}

	public void setTransactorList(List<FlowTransactor> transactorList) {
		this.transactorList = transactorList;
	}

	public String getSenderDept() {
		return senderDept;
	}

	public void setSenderDept(String senderDept) {
		this.senderDept = senderDept;
	}

}
package com.crowdfireapp.instagramservice.model;

import java.util.HashMap;
import java.util.Map;

public class ResponseObject {
	private int status;
	private Map<String, Object> data = new HashMap<String, Object>();
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}

package com.crowdfireapp.instagramservice.model;

import java.util.HashMap;
import java.util.Map;

public class BestTime {
	private String day;
	private Map<String, String> week = new HashMap<String, String>();

	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Map<String, String> getWeek() {
		return week;
	}
	public void setWeek(Map<String, String> week) {
		this.week = week;
	}
}

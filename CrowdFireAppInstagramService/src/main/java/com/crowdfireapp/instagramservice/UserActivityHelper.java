package com.crowdfireapp.instagramservice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.crowdfireapp.instagramservice.model.BestTime;
import com.crowdfireapp.instagramservice.model.ResponseObject;
import com.crowdfireapp.instagramservice.model.UserActivity;

public class UserActivityHelper {
	public static ResponseObject getBestTimeToPost(List<UserActivity> userList) {
		Map<Integer, List<UserActivity>> activityList = getEmptyActivityList();
		for(UserActivity user : userList) {
			int day = user.getDay();
			activityList.get(day).add(user);
		}
		
		BestTime bestTime = getVerboseBestTime(activityList);
		ResponseObject ro = new ResponseObject();
		ro.setStatus(200);
		ro.getData().put("data", bestTime);
		return ro;
	}
	
	private static Map<Integer, List<UserActivity>> getEmptyActivityList() {
		Map<Integer, List<UserActivity>> activityList = new HashMap<Integer, List<UserActivity>>();
		for(int i=1; i<=7; i++)
			activityList.put(i, new LinkedList<UserActivity>());
		
		return activityList;
	}
	
	public static BestTime getVerboseBestTime(Map<Integer, List<UserActivity>> activityList) {
		BestTime bt = new BestTime();
		int max = Integer.MIN_VALUE, day=0;
		System.out.println("Best Time for Day====> ");
		for(Entry<Integer, List<UserActivity>> entry : activityList.entrySet()) {
			List<UserActivity> list = entry.getValue();
			if(max < list.size()) {
				max = list.size();
				day = entry.getKey();
			}
			
			// Now fetch the best time of the day
			int[] timeInterval = new int[24];
			max = Integer.MIN_VALUE;
			int time = -1;
			for(UserActivity ua : list) {
				int userTime = ua.getTime();
				timeInterval[userTime]++;
				if(max < timeInterval[userTime]) {
					max = timeInterval[userTime];
					time = userTime;
				}
			}
			System.out.println("Best Time on " + getDay(entry.getKey())+ " is " + time +":00 hrs \n");
			bt.getWeek().put(getDay(entry.getKey()), String.valueOf(time));
		}

		System.out.println("\n\nBest Day of the Week to Post: "+ getDay(day));
		bt.setDay(getDay(day));
		
		return bt;
	}
	
	private static String getDay(int n) {
		String[] days = {"Day", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		return days[n];
	}
}

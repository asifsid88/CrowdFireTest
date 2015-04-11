package com.crowdfireapp.instagramservice.apis.social;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.crowdfireapp.instagram.api.InstagramApi;
import com.crowdfireapp.instagramservice.model.UserActivity;
import com.crowdfireapp.instagramservice.model.helper.JSONToModelTransformer;
import com.crowdfireapp.utility.JSONUtils;
import com.crowdfireapp.utility.StringUtils;

public class InstagramApiClient implements SocialConnectApi {
	public boolean hasAccessToken(String userName) {
		return InstagramApi.hasAccessToken(userName);
	}
	
	public String getUserId(String userName) {
		return InstagramApi.getUserId(userName);
	}
	
	public String getUserIdFromUserInformation(String userName) {
		String jsonResponse = InstagramApi.getUserInformation(userName);
		Map<String, Object> map = JSONUtils.fromJSONToObject(jsonResponse);
		// TODO: Get the userId
		
		String userId = "";
		
		return userId;
	}
	
	public String authorizeUser(String userName) {
		return InstagramApi.authorizeUser(userName);
	}
	
	public String obtainAccessToken(String code) {
		return InstagramApi.obtainAccessToken(code);
	}
	
	public List<UserActivity> getFollowersActivity(String userName) {
		System.out.println("Calling FollewedBy");
		String response = InstagramApi.getUsersFollowedBy(userName);
		if(StringUtils.isNullOrEmpty(response)) {
			return (List<UserActivity>) Collections.EMPTY_LIST;
		}
		
		System.out.println("Followers list obtained, transforming into objects");
		List<String> followersList = JSONToModelTransformer.getUsers(response);
		System.out.println("List of followers for userId="+userName);
		System.out.println(followersList);
		
		System.out.println("Getting Activities for each followers");
		List<UserActivity> list = new LinkedList<UserActivity>();
		for(String followerName : followersList) {
			System.out.println("Fetching last activity for follower="+followerName);
			response = InstagramApi.getLastActivityByUser(followerName);
			if(StringUtils.isNullOrEmpty(response)) {
				System.out.println("Couldn't find details of follower => " + followerName);
				continue;
			}
			UserActivity followerActivity = JSONToModelTransformer.createActivity(response);
			if( followerActivity != null) {
				System.out.println("Last time he posted => "+followerActivity);
				list.add(followerActivity);
			} else {
				System.out.println("Issue while getting follower activity for userId="+followerName);
			}
		}
		
		return list;
	}
}

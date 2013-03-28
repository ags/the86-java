package org.the86.model;

public class Authentication {

	private User user;

	private String user_access_token;

	public String getUserAccessToken() {
		return user_access_token;
	}

	public User getUser() {
		return user;
	}

}

package org.the86.model;

public class User extends The86Object {

	private String avatar_url;

	private String email;

	private String location;

	private String name;

	private String profile;

	private String twitter_username;

	private String url;

	public String toString() {
		return String.format("%s <%s>", name, email);
	}
}

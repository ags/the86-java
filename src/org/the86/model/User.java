package org.the86.model;

import java.util.Date;

public class User extends The86Object implements Comparable<User> {

	private String name;

	private String email;

	private String avatar_url;

	private String twitter_username;

	private String profile;

	private String location;

	private Date created_at;

	private String url;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAvatarUrl() {
		return avatar_url;
	}

	public String getTwitterUsername() {
		return twitter_username;
	}

	public String getProfile() {
		return profile;
	}

	public String getLocation() {
		return location;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public String getUrl() {
		return url;
	}

	public String toString() {
		return String.format("%s <%s>", name, email);
	}

	public int compareTo(User user) {
		return this.id.compareTo(user.getId());
	}

	public boolean equals(User user) {
		return compareTo(user) == 0;
	}
}

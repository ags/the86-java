package org.the86.model;

public class Like extends The86Object {

	private User user;

	public User getUser() {
		return user;
	}

	public String toString() {
		return String.format("Liked by %s", user);
	}
}
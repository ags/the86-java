package org.the86.model;

public class Post extends The86Object {
	private String content;
	
	private User user;
	
	public String toString() {
		return content + " - " + user;
	}
}

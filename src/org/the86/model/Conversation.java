package org.the86.model;

public class Conversation extends The86Object {

	private Post[] posts;
	
	public Post[] getPosts() {
		return posts;
	}
	
	public String toString() {
		return String.format("%s", id);
	}
	
}

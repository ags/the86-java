package org.the86.model;

import java.util.Date;

public class Conversation extends The86Object {

	private Date created_at;

	private Date updated_at;

	private Date bumped_at;

	private Post[] posts;

	public Post[] getPosts() {
		return posts;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public Date getUpdatedAt() {
		return updated_at;
	}

	public Date getBumpedAt() {
		return bumped_at;
	}

}

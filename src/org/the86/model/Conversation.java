package org.the86.model;

import java.util.Date;
import java.util.List;

import org.the86.Metadatum;

public class Conversation extends The86Object {

	private Date created_at;

	private Date updated_at;

	private Date bumped_at;

	private List<Post> posts;

	private Group group;

	public List<Post> getPosts() {
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

	public Group getGroup() {
		// TODO could be null if conversation was created by retrieving
		// conversations in a group, not all user conversations
		return group;
	}

	public List<Metadatum> getMetadata() {
		return null;
	}

}

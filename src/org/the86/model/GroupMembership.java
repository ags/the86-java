package org.the86.model;

import java.util.Date;

public class GroupMembership extends The86Object {

	private boolean administrator;

	private Date created_at;

	private User user;

	private Group group;

	public boolean isAdministrator() {
		return administrator;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public User getUser() {
		return user;
	}

	public Group getGroup() {
		return group;
	}

}
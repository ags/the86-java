package org.the86.model;

import java.util.Date;

public class Group extends The86Object {

	private String name;

	private String slug;

	private int member_count;

	private Date created_at;

	private Date updated_at;

	public String getName() {
		return name;
	}

	public String getSlug() {
		return slug;
	}

	public int getMemberCount() {
		return member_count;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public Date getUpdatedAt() {
		return updated_at;
	}

	public String toString() {
		return String.format("%s <%s> (%d)", name, slug, member_count);
	}
}

package org.the86.model;

public class Group extends The86Object {

	private String name;
	
	private String slug;
	
	private int member_count;

	public String toString() {
		return String.format("%s <%s> (%d)", name, slug, member_count);
	}
}

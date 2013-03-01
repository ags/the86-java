package org.the86.model;

import java.util.Date;
import java.util.List;

public class Post extends The86Object {

	private String content;

	private String content_html;

	private boolean is_original;

	private String in_reply_to_id;

	private Date created_at;

	private Date updated_at;

	private User user;

	private List<Like> likes;

	public String getContent() {
		return content;
	}

	public String getContentHtml() {
		return content_html;
	}

	public boolean isOriginal() {
		return is_original;
	}

	public String getInReplyToId() {
		return in_reply_to_id;
	}

	public User getUser() {
		return user;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public Date getUpdatedAt() {
		return updated_at;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public String toString() {
		return content + " - " + user;
	}

	// TODO attachments
	// TODO replies
}

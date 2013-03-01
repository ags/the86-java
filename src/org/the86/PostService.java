package org.the86;

import java.util.List;

import org.the86.model.Post;

public interface PostService {

	public List<Post> getConversationPosts(String groupSlug, String conversationId);
	
}

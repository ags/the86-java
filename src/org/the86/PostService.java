package org.the86;

import java.util.List;

import org.the86.exception.The86Exception;
import org.the86.model.Post;

public interface PostService {

	public List<Post> getConversationPosts(String groupSlug, String conversationId) throws The86Exception;
	
}

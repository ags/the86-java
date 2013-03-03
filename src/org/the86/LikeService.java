package org.the86;

import org.the86.exception.The86Exception;
import org.the86.model.Like;

public interface LikeService {

	public Like likePost(String groupSlug, String conversationId, String postId)
			throws The86Exception;

	public void unlikePost(String groupSlug, String conversationId,
			String postId, String likeId) throws The86Exception;
}

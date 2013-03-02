package org.the86;

import java.util.List;

import org.the86.exception.The86Exception;
import org.the86.model.Conversation;

public interface ConversationService {

	public List<Conversation> getGroupConversations(String groupSlug) throws The86Exception;

	public List<Conversation> getUserConversations(String userId) throws The86Exception;
}

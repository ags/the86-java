package org.the86;

import java.util.List;

import org.the86.model.Conversation;

public interface ConversationService {

	public List<Conversation> getGroupConversations(String groupSlug);

	public List<Conversation> getUserConversations(String userId);
}

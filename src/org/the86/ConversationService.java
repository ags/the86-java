package org.the86;

import java.util.List;

import org.the86.model.Conversation;

public interface ConversationService {

	public List<Conversation> getConversations(String group_slug);
	
}

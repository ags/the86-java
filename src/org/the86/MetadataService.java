package org.the86;

import java.util.List;

public interface MetadataService {

	public List<Metadatum> getConversationMetadata(String groupSlug,
			String conversationId);
}

package org.the86;

import java.util.List;

import org.the86.exception.The86Exception;

public interface MetadataService {

	public List<Metadatum> getConversationMetadata(String groupSlug,
			String conversationId) throws The86Exception;
}

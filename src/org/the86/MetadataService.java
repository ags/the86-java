package org.the86;

import java.util.List;

import org.the86.exception.The86Exception;
import org.the86.model.Metadatum;

public interface MetadataService {

	public List<Metadatum> getConversationMetadata(String groupSlug,
			String conversationId) throws The86Exception;
}

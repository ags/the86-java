package org.the86;

public interface The86 extends AuthorizationService, GroupService,
		ConversationService, MetadataService, GroupMembershipsService,
		PostService, LikeService {

	public String getUserId();

}

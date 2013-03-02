package org.the86;

import java.util.List;

import org.the86.exception.The86Exception;
import org.the86.model.GroupMembership;

public interface GroupMembershipsService {

	public List<GroupMembership> getUserGroupMemberships(String userId)
			throws The86Exception;

}

package org.the86;

import java.util.List;

import org.the86.exception.The86Exception;
import org.the86.model.GroupMembership;
import org.the86.model.User;

public interface GroupMembershipsService {

	public List<GroupMembership> getUserGroupMemberships()
			throws The86Exception;

}

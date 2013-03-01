package org.the86;

import java.util.List;

import org.the86.model.GroupMembership;

public interface GroupMembershipsService {

	public List<GroupMembership> getGroupMemberships(String userId);

}

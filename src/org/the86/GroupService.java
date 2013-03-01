package org.the86;

import java.util.List;

import org.the86.model.Group;

public interface GroupService {

	public List<Group> getGroups();

	public Group getGroup(String slug);

}
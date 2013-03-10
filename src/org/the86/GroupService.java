package org.the86;

import java.util.List;

import org.the86.exception.The86Exception;
import org.the86.model.Group;

public interface GroupService {

	public List<Group> getGroups() throws The86Exception;

	public Group getGroup(String slug) throws The86Exception;

	public Group createGroup(String name) throws The86Exception;

}
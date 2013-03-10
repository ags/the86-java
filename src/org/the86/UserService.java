package org.the86;

import org.the86.exception.The86Exception;
import org.the86.model.User;

public interface UserService {

	public String getUserId();

	public User getUser(String userId) throws The86Exception;
	
}

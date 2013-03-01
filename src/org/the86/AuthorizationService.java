package org.the86;

import org.the86.model.User;

public interface AuthorizationService {

	public User authorize(String email, String password);
	
}

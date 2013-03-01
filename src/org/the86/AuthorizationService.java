package org.the86;

import org.the86.model.Authorization;

public interface AuthorizationService {

	public Authorization authorize(String email, String password);
	
}

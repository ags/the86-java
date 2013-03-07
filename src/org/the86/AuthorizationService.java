package org.the86;

import org.the86.exception.The86Exception;
import org.the86.model.Authorization;

public interface AuthorizationService {

	public Authorization authorize(String email, String password)
			throws The86Exception;

	public void setAuthorization(String userId, String userAcessToken);

}

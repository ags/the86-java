package org.the86;

import org.the86.exception.The86Exception;
import org.the86.model.Authentication;
import org.the86.model.User;

public interface AuthenticationService {

	/**
	 * Authenticates as a {@link User}.
	 * 
	 * @param email
	 *            Email of {@link User} to authenticate as.
	 * @param password
	 *            Password of {@link User} to authenticate as.
	 * @return {@link Authentication}
	 * @throws The86Exception
	 */
	public Authentication authenticate(String email, String password)
			throws The86Exception;

	/**
	 * Set known authentication parameters.
	 * 
	 * @param userId
	 *            ID of {@link User}
	 * @param userAcessToken
	 *            Access Token of {@link User}
	 */
	public void setAuthentication(String userId, String userAcessToken);

}

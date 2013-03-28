package test.org.the86;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.the86.The86;
import org.the86.The86Impl;
import org.the86.exception.The86Exception;
import org.the86.model.Authentication;

public class AuthenticationTest {
	
	@Test
	public void testInvalidCredentials() {
		try {
			The86 the86 = new The86Impl("http://localhost:3000");
			the86.authenticate("faux@no.com", "foobar");
			fail("Expected The86Exception");
		} catch (The86Exception e) {
			assertEquals("Unauthorized.", e.getThe86Error().getMessage());
		}
	}

	@Test
	public void testValidCredentials() throws The86Exception {
		The86 the86 = new The86Impl("http://localhost:3000");
		Authentication auth = the86.authenticate("a@a.com", "foobarbar");
		assertNotNull("User access token", auth.getUserAccessToken());
		assertEquals("a@a.com", auth.getUser().getEmail());
	}

}

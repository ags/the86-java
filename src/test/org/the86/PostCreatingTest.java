package test.org.the86;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.the86.The86;
import org.the86.The86Impl;
import org.the86.exception.The86Exception;
import org.the86.model.Post;
import org.the86.model.The86Error;

public class PostCreatingTest {
	private The86 the86;

	@Before
	public void setup() throws The86Exception {
		the86 = new The86Impl("http://localhost:3000");
		the86.setAuthentication(The86Test.VALID_USER_ID, The86Test.VALID_USER_AUTH_TOKEN);
	}

	@Test
	public void testPostingUnauthorized() throws The86Exception {
		the86 = new The86Impl("http://localhost:3000");
		the86.authenticate("b@b.com", "asdasdasd");
		try {
			the86.createPost("2-a-user-s-pod", "1", "imma post!");
			fail("Expected The86Exception");
		} catch (The86Exception e) {
			assertEquals("Unauthorized.", e.getThe86Error().getMessage());
		}
	}

	@Test
	public void testPostingNonExistantConversation() {
		try {
			the86.createPost("2-a-user-s-pod", "0", "imma post!");
			fail("Expected The86Exception");
		} catch (The86Exception e) {
			assertEquals("Resource not found.", e.getThe86Error().getMessage());
		}
	}

	@Test
	public void testPostCreatingEmptyContent() throws The86Exception {
		try {
			the86.createPost("2-a-user-s-pod", "3", "");
		} catch (The86Exception e) {
			The86Error error = e.getThe86Error();
			assertEquals("Validation failed.", error.getMessage());
			assertEquals("Post content can't be blank", error.getErrors()
					.get(0).toString());
		}
	}

	@Test
	public void testPostCreating() throws The86Exception {
		Post post = the86.createPost("2-a-user-s-pod", "3", "imma post!");

		assertEquals("imma post!", post.getContent());
	}

	@Test
	public void testPostReplying() throws The86Exception {
		Post post = the86.createPost("2-a-user-s-pod", "3", "imma post!", "11");
		assertEquals(post.getInReplyToId(), "11");
	}

}

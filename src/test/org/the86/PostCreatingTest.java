package test.org.the86;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.the86.The86;
import org.the86.The86Impl;
import org.the86.exception.The86Exception;
import org.the86.model.Post;

public class PostCreatingTest {
	private The86 the86;

	@Before
	public void setup() throws The86Exception {
		the86 = new The86Impl("localhost:3000", "a@a.com", "foobarbar");
	}

	@Test
	public void testPostingUnauthorized() throws The86Exception {
		the86 = new The86Impl("localhost:3000", "b@b.com", "asdasdasd");
		try {
			the86.createPost("2-a-user-s-pod", "1", "imma post!");
			fail("Expected The86Exception");
		} catch (The86Exception e) {
			assertEquals("Unauthorized.", e.getThe86Error().toString());
		}
	}

	@Test
	public void testPostingNonExistantConversation() {
		try {
			the86.createPost("2-a-user-s-pod", "0", "imma post!");
			fail("Expected The86Exception");
		} catch (The86Exception e) {
			assertEquals("Resource not found.", e.getThe86Error().toString());
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

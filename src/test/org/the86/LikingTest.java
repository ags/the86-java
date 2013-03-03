package test.org.the86;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.the86.The86;
import org.the86.The86Impl;
import org.the86.exception.The86Exception;
import org.the86.model.Like;
import org.the86.model.Post;
import org.the86.model.The86Error;

public class LikingTest {

	private The86 the86;

	@Before
	public void setup() throws The86Exception {
		the86 = new The86Impl("localhost:3000", "a@a.com", "foobarbar");
	}

	@Test
	public void testLikingPost() throws The86Exception {
		Post post = the86.createPost("2-a-user-s-pod", "3", "LIKE MEEEE");
		Like like = the86.likePost("2-a-user-s-pod", "3", post.getId());
		assertTrue(the86.getAuthorization().getUser().equals(like.getUser()));
	}

	@Test
	public void testPostCreatingEmptyContent() throws The86Exception {
		try {
			Post post = the86.createPost("2-a-user-s-pod", "3", "LIKE MEEEE");
			the86.likePost("2-a-user-s-pod", "3", post.getId());
			the86.likePost("2-a-user-s-pod", "3", post.getId());
			fail("expected The86Exception");
		} catch (The86Exception e) {
			The86Error error = e.getThe86Error();
			assertEquals("Validation failed.", error.getMessage());
			assertEquals("Like post_id already liked", error.getErrors()
					.get(0).toString());
		}
	}
}

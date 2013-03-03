package test.org.the86;

import org.junit.Before;
import org.junit.Test;
import org.the86.The86;
import org.the86.The86Impl;
import org.the86.exception.The86Exception;
import org.the86.model.Like;
import org.the86.model.Post;

public class UnlikingTest {

	private The86 the86;

	@Before
	public void setup() throws The86Exception {
		the86 = new The86Impl("localhost:3000", "a@a.com", "foobarbar");
	}

	@Test
	public void testUnlikingPost() throws The86Exception {
		Post post = the86.createPost("2-a-user-s-pod", "3", "LIKE MEEEE");
		Like like = the86.likePost("2-a-user-s-pod", "3", post.getId());
		the86.unlikePost("2-a-user-s-pod", "3", post.getId(), like.getId());
		post = the86.getConversationPost("2-a-user-s-pod", "3", post.getId());
		for (Like l : post.getLikes()) {
			the86.getAuthorization().getUser().equals(l.getUser());
		}
	}
}

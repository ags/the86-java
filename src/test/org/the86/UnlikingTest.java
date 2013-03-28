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
		the86 = new The86Impl("http://localhost:3000");
		the86.setAuthentication(The86Test.VALID_USER_ID, The86Test.VALID_USER_AUTH_TOKEN);
	}

	@Test
	public void testUnlikingPost() throws The86Exception {
		Post post = the86.createPost("2-a-user-s-pod", "3", "LIKE MEEEE");
		Like like = the86.likePost("2-a-user-s-pod", "3", post.getId());
		the86.unlikePost("2-a-user-s-pod", "3", post.getId(), like.getId());
		post = the86.getConversationPost("2-a-user-s-pod", "3", post.getId());
		for (Like l : post.getLikes()) {
			The86Test.VALID_USER_ID.equals(l.getUser().getId());
		}
	}
}

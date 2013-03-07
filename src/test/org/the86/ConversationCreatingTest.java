package test.org.the86;

import static org.junit.Assert.*;

import org.junit.Test;
import org.the86.The86;
import org.the86.The86Impl;
import org.the86.exception.The86Exception;
import org.the86.model.Conversation;

public class ConversationCreatingTest {

	@Test
	public void testNotAGroupMember() throws The86Exception {
		The86 the86 = new The86Impl("http://localhost:3000");
		the86.authorize("b@b.com", "asdasdasd");
		try {
			the86.createConversation("2-a-user-s-pod", "Hello World");
			fail("Expected The86Exception");
		} catch (The86Exception e) {
			assertEquals("Unauthorized.", e.getThe86Error().getMessage());
		}
	}

	@Test
	public void testCreating() throws The86Exception {
		The86 the86 = new The86Impl("http://localhost:3000");
		the86.authorize("a@a.com", "foobarbar");
		Conversation c = the86.createConversation("2-a-user-s-pod",
				"Hello World");
		assertEquals("Hello World", c.getPosts().get(0).getContent());

	}
}

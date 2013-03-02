package org.the86;

import org.the86.exception.The86Exception;
import org.the86.model.Conversation;
import org.the86.model.Post;

public class Main {

	public static void main(String args[]) {
		String domain = args[0];
		String email = args[1];
		String password = args[2];
		The86 the86;
		try {
			the86 = new The86Impl(domain, email, password);
			Conversation c = the86.createConversation("2-a-user-s-pod",
					"java postin'");
			System.out.println(c);
			for (Post post : c.getPosts()) {
				System.out.println("\t" + post);
			}
			// System.out.println("group index");
			// List<Group> groups = the86.getGroups();
			// for (Group g : groups) {
			// System.out.println(g);
			// }
			//
			// System.out.println("group show");
			// Group g = the86.getGroup("1-alex-s-pod");
			// System.out.println(g);
			//
			// System.out.println("listing conversations");
			// List<Conversation> conversations = the86
			// .getGroupConversations("1-alex-s-pod");
			// for (Conversation conversation : conversations) {
			// System.out.println(conversation);
			// for (Post post : conversation.getPosts()) {
			// System.out.println("\t" + post);
			// for (Like like : post.getLikes()) {
			// System.out.println("\t\t" + like);
			// }
			// for (Attachment attachment : post.getAttachments()) {
			// System.out.println("\t\t" + attachment);
			// }
			//
			// }
			// for (Metadatum metadatum : the86.getConversationMetadata(
			// "1-alex-s-pod", conversation.getId())) {
			// System.out.println("\t" + metadatum);
			// }
			// }
			//
			// System.out.println("group memberships");
			// List<GroupMembership> memberships = the86
			// .getUserGroupMemberships("1");
			// for (GroupMembership membership : memberships) {
			// System.out.println(membership);
			// }
			//
			// System.out.println("all conversations");
			// for (Conversation conversation : the86.getUserConversations("1"))
			// {
			// System.out.println(conversation);
			// for (Post post : conversation.getPosts()) {
			// System.out.println("\t" + post);
			// }
			// }
			//
			// System.out.println("conversation posts");
			// List<Post> posts = the86.getConversationPosts("1-alex-s-pod",
			// "2");
			// for (Post post : posts) {
			// System.out.println(post);
			// }
			//
			// System.out.println("404 resource");
			// try {
			// the86.getConversationPosts("1-alex-s-pod", "3");
			// } catch (The86Exception e) {
			// System.err.println(e);
			// }
		} catch (The86Exception e) {
			e.printStackTrace();
		}

	}
}

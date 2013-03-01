package org.the86;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.the86.model.Attachment;
import org.the86.model.Authorization;
import org.the86.model.Conversation;
import org.the86.model.Group;
import org.the86.model.GroupMembership;
import org.the86.model.Like;
import org.the86.model.Post;

import com.google.gson.reflect.TypeToken;

public class The86Impl implements The86 {
	private Authorization authorization = null;

	private The86ObjectFactory the86ObjFactory = new The86ObjectFactory();
	private The86UrlResourceFactory the86UrlFactory = null;

	public static void main(String args[]) {
		String domain = args[0];
		String email = args[1];
		String password = args[2];
		The86 the86 = new The86Impl(domain, email, password);

		System.out.println("group index");
		List<Group> groups = the86.getGroups();
		for (Group g : groups) {
			System.out.println(g);
		}

		System.out.println("group show");
		Group g = the86.getGroup("1-alex-s-pod");
		System.out.println(g);

		System.out.println("listing conversations");
		List<Conversation> conversations = the86
				.getGroupConversations("1-alex-s-pod");
		for (Conversation conversation : conversations) {
			System.out.println(conversation);
			for (Post post : conversation.getPosts()) {
				System.out.println("\t" + post);
				for (Like like : post.getLikes()) {
					System.out.println("\t\t" + like);
				}
				for (Attachment attachment : post.getAttachments()) {
					System.out.println("\t\t" + attachment);
				}

			}
			for (Metadatum metadatum : the86.getConversationMetadata(
					"1-alex-s-pod", conversation.getId())) {
				System.out.println("\t" + metadatum);
			}
		}

		System.out.println("group memberships");
		List<GroupMembership> memberships = the86.getGroupMemberships("1");
		for (GroupMembership membership : memberships) {
			System.out.println(membership);
		}

		System.out.println("all conversations");
		for (Conversation conversation : the86.getUserConversations("1")) {
			System.out.println(conversation);
			for (Post post : conversation.getPosts()) {
				System.out.println("\t" + post);
			}
		}
	}

	public The86Impl(String domain, String email, String password) {
		this.the86UrlFactory = new The86UrlResourceFactory(domain);
		this.authorization = authorize(email, password);
		this.the86UrlFactory.setAuthorization(authorization);
	}

	public Authorization authorize(String email, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		params.put("password", password);
		return the86ObjFactory.createObject(new TypeToken<Authorization>() {
		}, the86UrlFactory.buildUrl("/users/authenticate").post(params));
	}

	public List<Group> getGroups() {
		return getResource(new TypeToken<List<Group>>() {
		}, "/groups");
	}

	public Group getGroup(String groupSlug) {
		String url = String.format("/groups/%s", groupSlug);
		return getResource(new TypeToken<Group>() {
		}, url);
	}

	public List<Conversation> getGroupConversations(String groupSlug) {
		String url = String.format("/groups/%s/conversations", groupSlug);
		return getResource(new TypeToken<List<Conversation>>() {
		}, url);
	}

	public List<Conversation> getUserConversations(String userId) {
		String url = String.format("/users/%s/conversations", userId);
		return getResource(new TypeToken<List<Conversation>>() {
		}, url);
	}

	public List<Metadatum> getConversationMetadata(String groupSlug,
			String conversationId) {
		String url = String.format("/groups/%s/conversations/%s/metadata",
				groupSlug, conversationId);
		return getResource(new TypeToken<List<Metadatum>>() {
		}, url);
	}

	public List<GroupMembership> getGroupMemberships(String userId) {
		String url = String.format("/users/%s/memberships", userId);
		return getResource(new TypeToken<List<GroupMembership>>() {
		}, url);
	}

	private <T> T getResource(TypeToken<T> typeToken, String url) {
		return requestResource(typeToken, the86UrlFactory.buildUrl(url).get());
	}

	private <T> T requestResource(TypeToken<T> typeToken, InputStream json) {
		return the86ObjFactory.createObject(typeToken, json);
	}
}

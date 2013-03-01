package org.the86;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.the86.model.Authorization;
import org.the86.model.Conversation;
import org.the86.model.Group;
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
				.getConversations("1-alex-s-pod");
		for (Conversation conversation : conversations) {
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
		return the86ObjFactory.createObject(new TypeToken<List<Group>>() {
		}, the86UrlFactory.buildUrl("/groups").get());
	}

	public Group getGroup(String slug) {
		return the86ObjFactory.createObject(new TypeToken<Group>() {
		}, the86UrlFactory.buildUrl(String.format("/groups/%s", slug)).get());
	}

	public List<Conversation> getConversations(String group_slug) {
		String url = String.format("/groups/%s/conversations", group_slug);
		return the86ObjFactory.createObject(
				new TypeToken<List<Conversation>>() {
				}, the86UrlFactory.buildUrl(url).get());
	}
}

package org.the86;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.the86.exception.The86Exception;
import org.the86.model.Authorization;
import org.the86.model.Conversation;
import org.the86.model.Group;
import org.the86.model.GroupMembership;
import org.the86.model.Post;

import com.google.gson.reflect.TypeToken;

public class The86Impl implements The86 {
	private Authorization authorization = null;

	private The86ObjectFactory the86ObjFactory = new The86ObjectFactory();
	private The86UrlResourceFactory the86UrlFactory = null;

	public The86Impl(String domain, String email, String password)
			throws The86Exception {
		this.the86UrlFactory = new The86UrlResourceFactory(domain);
		this.authorization = authorize(email, password);
		this.the86UrlFactory.setAuthorization(authorization);
	}

	public Authorization getAuthorization() {
		return authorization;
	}

	public Authorization authorize(String email, String password)
			throws The86Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		params.put("password", password);
		return the86ObjFactory.createObject(new TypeToken<Authorization>() {
		}, the86UrlFactory.buildUrl("/users/authenticate").post(params));
	}

	public List<Group> getGroups() throws The86Exception {
		return getResource(new TypeToken<List<Group>>() {
		}, "/groups");
	}

	public Group getGroup(String groupSlug) throws The86Exception {
		String url = String.format("/groups/%s", groupSlug);
		return getResource(new TypeToken<Group>() {
		}, url);
	}

	public List<Conversation> getGroupConversations(String groupSlug)
			throws The86Exception {
		String url = String.format("/groups/%s/conversations", groupSlug);
		return getResource(new TypeToken<List<Conversation>>() {
		}, url);
	}

	public List<Conversation> getUserConversations(String userId)
			throws The86Exception {
		String url = String.format("/users/%s/conversations", userId);
		return getResource(new TypeToken<List<Conversation>>() {
		}, url);
	}

	public List<Metadatum> getConversationMetadata(String groupSlug,
			String conversationId) throws The86Exception {
		String url = String.format("/groups/%s/conversations/%s/metadata",
				groupSlug, conversationId);
		return getResource(new TypeToken<List<Metadatum>>() {
		}, url);
	}

	public List<GroupMembership> getUserGroupMemberships(String userId)
			throws The86Exception {
		String url = String.format("/users/%s/memberships", userId);
		return getResource(new TypeToken<List<GroupMembership>>() {
		}, url);
	}

	public List<Post> getConversationPosts(String groupSlug,
			String conversationId) throws The86Exception {
		String url = String.format("/groups/%s/conversations/%s/posts",
				groupSlug, conversationId);
		return getResource(new TypeToken<List<Post>>() {
		}, url);
	}

	private <T> T getResource(TypeToken<T> typeToken, String url)
			throws The86Exception {
		return requestResource(typeToken, the86UrlFactory.buildUrl(url).get());
	}

	private <T> T requestResource(TypeToken<T> typeToken, InputStream json) {
		return the86ObjFactory.createObject(typeToken, json);
	}
}

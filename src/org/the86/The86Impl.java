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
import org.the86.model.Like;
import org.the86.model.Metadatum;
import org.the86.model.Post;
import org.the86.model.User;

import com.google.gson.reflect.TypeToken;

public class The86Impl implements The86 {
	private String userId;

	private The86ObjectFactory the86ObjFactory = new The86ObjectFactory();
	private The86UrlResourceFactory the86UrlFactory;

	public The86Impl(String domain) {
		this.the86UrlFactory = new The86UrlResourceFactory(domain);
	}

	public void setAuthorization(String userId, String userAcessToken) {
		this.userId = userId;
		this.the86UrlFactory.setUserAuthToken(userAcessToken);
	}

	public Authorization authorize(String email, String password)
			throws The86Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		params.put("password", password);

		Authorization auth = postResource(new TypeToken<Authorization>() {
		}, "/users/authenticate", params);

		setAuthorization(auth.getUser().getId(), auth.getUserAccessToken());
		return auth;
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

	public Group createGroup(String name) throws The86Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		return postResource(new TypeToken<Group>() {
		}, "/groups", params);
	}

	public List<Conversation> getGroupConversations(String groupSlug)
			throws The86Exception {
		String url = String.format("/groups/%s/conversations", groupSlug);
		return getResource(new TypeToken<List<Conversation>>() {
		}, url);
	}

	public List<Conversation> getUserConversations() throws The86Exception {
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

	public List<GroupMembership> getUserGroupMemberships()
			throws The86Exception {
		String url = String.format("/users/%s/memberships", userId);
		return getResource(new TypeToken<List<GroupMembership>>() {
		}, url);
	}

	public List<User> getGroupMemberships(String groupSlug)
			throws The86Exception {
		String url = String.format("/groups/%s/members", groupSlug);
		return getResource(new TypeToken<List<User>>() {
		}, url);
	}

	public List<Post> getConversationPosts(String groupSlug,
			String conversationId) throws The86Exception {
		String url = String.format("/groups/%s/conversations/%s/posts",
				groupSlug, conversationId);
		return getResource(new TypeToken<List<Post>>() {
		}, url);
	}

	public Post getConversationPost(String groupSlug, String conversationId,
			String postId) throws The86Exception {
		String url = String.format("/groups/%s/conversations/%s/posts/%s",
				groupSlug, conversationId, postId);
		return getResource(new TypeToken<Post>() {
		}, url);
	}

	public Post createPost(String groupSlug, String conversationId,
			String content) throws The86Exception {
		return createPost(groupSlug, conversationId, content, null);
	}

	public Post createPost(String groupSlug, String conversationId,
			String content, String inReplyToId) throws The86Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("content", content);
		if (inReplyToId != null) {
			params.put("in_reply_to_id", inReplyToId);
		}
		String url = String.format("/groups/%s/conversations/%s/posts",
				groupSlug, conversationId);
		return postResource(new TypeToken<Post>() {
		}, url, params);
	}

	public Conversation createConversation(String groupSlug, String content)
			throws The86Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("content", content);
		String url = String.format("/groups/%s/conversations", groupSlug);
		return postResource(new TypeToken<Conversation>() {
		}, url, params);
	}

	public Like likePost(String groupSlug, String conversationId, String postId)
			throws The86Exception {
		Map<String, String> params = new HashMap<String, String>();
		// TODO temporary work around for an empty POST body
		params.put("", "");
		String url = String.format(
				"/groups/%s/conversations/%s/posts/%s/likes", groupSlug,
				conversationId, postId);
		return postResource(new TypeToken<Like>() {
		}, url, params);
	}

	public void unlikePost(String groupSlug, String conversationId,
			String postId, String likeId) throws The86Exception {
		String url = String.format(
				"/groups/%s/conversations/%s/posts/%s/likes/%s", groupSlug,
				conversationId, postId, likeId);
		deleteResource(null, url);
	}

	private <T> T postResource(TypeToken<T> typeToken, String url,
			Map<String, String> params) throws The86Exception {
		return requestResource(typeToken,
				the86UrlFactory.buildUrl(url).post(params));
	}

	private <T> T getResource(TypeToken<T> typeToken, String url)
			throws The86Exception {
		return requestResource(typeToken, the86UrlFactory.buildUrl(url).get());
	}

	private <T> T deleteResource(TypeToken<T> typeToken, String url)
			throws The86Exception {
		return requestResource(typeToken, the86UrlFactory.buildUrl(url)
				.delete());
	}

	private <T> T requestResource(TypeToken<T> typeToken, InputStream json) {
		return the86ObjFactory.createObject(typeToken, json);
	}

}

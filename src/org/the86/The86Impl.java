package org.the86;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.the86.model.Authorization;
import org.the86.model.Conversation;
import org.the86.model.Group;
import org.the86.model.Post;

import com.google.gson.reflect.TypeToken;

public class The86Impl implements The86 {
	private static final String METHOD_DELETE = "DELETE";
	private static final String METHOD_GET = "GET";
	private static final String METHOD_POST = "POST";
	private static final String METHOD_PUT = "PUT";
	private static final String API_VERSION = "v1";

	private String domain = null;
	private String email = null;
	private String password = null;
	private Authorization authorization = null;

	private The86ObjectFactoryImpl the86ObjFactory = new The86ObjectFactoryImpl();

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
		this.domain = domain;
		this.email = email;
		this.password = password;
		authorization = authorize(email, password);
	}

	public Authorization authorize(String email, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		params.put("password", password);
		return the86ObjFactory.createObject(new TypeToken<Authorization>() {
		}, doPost(buildUrl("/users/authenticate"), params));
	}

	public List<Group> getGroups() {
		return the86ObjFactory.createObject(new TypeToken<List<Group>>() {
		}, doGet(buildUrl("/groups")));
	}

	public Group getGroup(String slug) {
		return the86ObjFactory.createObject(new TypeToken<Group>() {
		}, doGet(buildUrl(String.format("/groups/%s", slug))));
	}

	public List<Conversation> getConversations(String group_slug) {
		return the86ObjFactory.createObject(
				new TypeToken<List<Conversation>>() {
				}, doGet(buildUrl(String.format("/groups/%s/conversations",
						group_slug))));
	}

	public String buildUrl(String resource) {
		return "http://" + domain + "/api/" + API_VERSION + "/" + resource;
	}

	private InputStream doGet(String url) {
		return doRequest(url, METHOD_GET);
	}

	private InputStream doPut(String url) {
		return doRequest(url, METHOD_PUT);
	}

	private InputStream doPost(String url, Map<String, String> params) {
		return doRequest(url, METHOD_POST, params);
	}

	private InputStream doDelete(String url) {
		return doRequest(url, METHOD_DELETE);
	}

	private InputStream doRequest(String url, String requestMethod) {
		return doRequest(url, requestMethod, null);
	}

	private InputStream doRequest(String url, String requestMethod,
			Map<String, String> map) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url)
					.openConnection();
			if (authorization != null) {
				conn.setRequestProperty("Authorization", authorization.header());
			}
			conn.setDoOutput(requestMethod.equals(METHOD_POST)
					|| requestMethod.equals(METHOD_PUT));
			conn.setRequestMethod(requestMethod);

			if (map != null && !map.isEmpty()) {
				StringBuilder sb = new StringBuilder();
				for (String key : map.keySet()) {
					sb.append(sb.length() > 0 ? "&" : "").append(key)
							.append("=")
							.append(URLEncoder.encode(map.get(key), "UTF-8"));
				}
				conn.getOutputStream().write(sb.toString().getBytes());
				conn.getOutputStream().close();
			}

			if (conn.getResponseCode() > 399) {
				return null;
			} else {
				return new BufferedInputStream(conn.getInputStream());
			}
		} catch (IOException e) {
			return null;
			// throw new TrelloException(e.getMessage());
		}
	}

}

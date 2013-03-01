package org.the86;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.the86.model.Authorization;

public class The86UrlResource {
	private static final String METHOD_DELETE = "DELETE";
	private static final String METHOD_GET = "GET";
	private static final String METHOD_POST = "POST";
	private static final String METHOD_PUT = "PUT";

	private String resource;
	private Authorization authorization;

	public The86UrlResource(String resource, Authorization authorization) {
		this.resource = resource;
		this.authorization = authorization;
	}

	public InputStream get() {
		return doRequest(METHOD_GET);
	}

	public InputStream put() {
		return doRequest(METHOD_PUT);
	}

	public InputStream post(Map<String, String> params) {
		return doRequest(METHOD_POST, params);
	}

	public InputStream delete() {
		return doRequest(METHOD_DELETE);
	}

	private InputStream doRequest(String requestMethod) {
		return doRequest(requestMethod, null);
	}

	private InputStream doRequest(String requestMethod, Map<String, String> map) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(resource)
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
			throw new RuntimeException(e.getMessage());
		}
	}
}

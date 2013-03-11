package org.the86;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.the86.exception.The86Exception;

public class The86UrlResource {
	private static final String METHOD_DELETE = "DELETE";
	private static final String METHOD_GET = "GET";
	private static final String METHOD_POST = "POST";
	private static final String METHOD_PUT = "PUT";

	private String resource;
	private String userAuthToken;

	public The86UrlResource(String resource, String userAuthToken) {
		this.resource = resource;
		this.userAuthToken = userAuthToken;
	}

	public InputStream get() throws The86Exception {
		return doRequest(METHOD_GET);
	}

	public InputStream put() throws The86Exception {
		return doRequest(METHOD_PUT);
	}

	public InputStream post(Map<String, String> params) throws The86Exception {
		return doRequest(METHOD_POST, params);
	}

	public InputStream delete() throws The86Exception {
		return doRequest(METHOD_DELETE);
	}

	private InputStream doRequest(String requestMethod) throws The86Exception {
		return doRequest(requestMethod, null);
	}

	private InputStream doRequest(String requestMethod, Map<String, String> map)
			throws The86Exception {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(resource).openConnection();
			if (userAuthToken != null) {
				conn.setRequestProperty("Authorization", authorizationHeader());
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
			conn.getResponseMessage();

			if (conn.getResponseCode() > 399) {
				// TODO subclass exception based on HTTP status
				throw new The86Exception(resource, new BufferedInputStream(
						conn.getErrorStream()));
			} else if (conn.getResponseCode() == 204) {
				return null;
			} else {
				return new BufferedInputStream(conn.getInputStream());
			}
		} catch (IOException e) {
			// TODO temp hack till the86 responds with the correct header
			if (conn != null
					&& e.getMessage().equals(
							"No authentication challenges found")) {
				throw new The86Exception(resource, new BufferedInputStream(
						conn.getErrorStream()));
			} else {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public String authorizationHeader() {
		return "Bearer " + userAuthToken;
	}
}

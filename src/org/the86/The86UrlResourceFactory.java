package org.the86;

import org.the86.model.Authorization;

public class The86UrlResourceFactory {
	private static final String API_VERSION = "v1";
	private String host = null;
	private Authorization authorization;

	public The86UrlResourceFactory(String host) {
		this.host = host;
	}

	public The86UrlResource buildUrl(String resource) {
		String url = String.format("%s/api/%s%s", host,
				API_VERSION, resource);
		return new The86UrlResource(url, authorization);
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

}

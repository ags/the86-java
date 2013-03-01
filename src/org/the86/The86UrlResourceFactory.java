package org.the86;

import org.the86.model.Authorization;

public class The86UrlResourceFactory {
	private static final String API_VERSION = "v1";
	private static final String PROTOCOL = "http://";
	private String domain = null;
	private Authorization authorization;

	public The86UrlResourceFactory(String domain) {
		this.domain = domain;
	}

	public The86UrlResource buildUrl(String resource) {
		String url = String.format("%s%s/api/%s/%s", PROTOCOL, domain,
				API_VERSION, resource);
		return new The86UrlResource(url, authorization);
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

}

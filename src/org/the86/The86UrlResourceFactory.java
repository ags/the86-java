package org.the86;


public class The86UrlResourceFactory {
	private static final String API_VERSION = "v1";
	private String host;
	private String userAuthToken;

	public The86UrlResourceFactory(String host) {
		this.host = host;
	}

	public The86UrlResource buildUrl(String resource) {
		String url = String.format("%s/api/%s%s", host, API_VERSION, resource);
		return new The86UrlResource(url, userAuthToken);
	}

	public void setUserAuthToken(String userAuthToken) {
		this.userAuthToken = userAuthToken;
	}

}

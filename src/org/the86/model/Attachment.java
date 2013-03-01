package org.the86.model;

public class Attachment {

	private String url;

	private String thumbnail_url;

	private String filename;

	private String mimetype;

	private long size;

	private Security security;

	private Security thumbnail_security;

	public String getUrl() {
		return url;
	}

	public String getThumbnailUrl() {
		return thumbnail_url;
	}

	public String getFilename() {
		return filename;
	}

	public String getMimetype() {
		return mimetype;
	}

	public long getSize() {
		return size;
	}

	public Security getSecurity() {
		return security;
	}

	public Security getThumbnailSecurity() {
		return thumbnail_security;
	}

	public String toString() {
		return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s", url, thumbnail_url,
				filename, mimetype, size, security, thumbnail_security);
	}

	static class Security {
		private String signature;
		private String policy;

		public String toString() {
			return String.format("%s %s", signature, policy);
		}
	}

}

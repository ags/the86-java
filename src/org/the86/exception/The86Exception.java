package org.the86.exception;

import java.io.InputStream;

import org.the86.The86ObjectFactory;

import com.google.gson.reflect.TypeToken;

public class The86Exception extends Exception {

	private static final long serialVersionUID = -6270576132683169546L;
	private The86Error error;
	private The86ObjectFactory f = new The86ObjectFactory();
	private String resourceUrl;

	public The86Exception(String resourceUrl, InputStream errorStream) {
		this.resourceUrl = resourceUrl;
		this.error = f.createObject(new TypeToken<The86Error>() {
		}, errorStream);
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public The86Error getThe86Error() {
		return error;
	}

	public String toString() {
		return String.format("%s - %s", resourceUrl, error);
	}
}

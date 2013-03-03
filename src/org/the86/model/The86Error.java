package org.the86.model;

import java.util.List;

public class The86Error {

	private String resource;
	private String field;
	private String message;
	private List<The86Error> errors;

	public String getResource() {
		return resource;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

	public String toString() {
		return String.format("%s %s %s", resource, field, message);
	}

	public List<The86Error> getErrors() {
		return errors;
	}
	
}

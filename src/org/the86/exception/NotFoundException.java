package org.the86.exception;

import java.io.InputStream;

public class NotFoundException extends The86Exception {

	private static final long serialVersionUID = -2833218244765201239L;

	public NotFoundException(String resource, InputStream errorStream) {
		super(resource, errorStream);
	}

}

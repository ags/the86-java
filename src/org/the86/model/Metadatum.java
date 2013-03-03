package org.the86.model;

import java.util.Date;


public class Metadatum extends The86Object {

	private String data_id;

	private String data_type;

	private String key;

	private String value;

	private Date created_at;

	private Date updated_at;

	public String getDataId() {
		return data_id;
	}

	public String getDataType() {
		return data_type;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public Date getUpdatedAt() {
		return updated_at;
	}

	public String toString() {
		return String.format("%s:%s", key, value);
	}
}

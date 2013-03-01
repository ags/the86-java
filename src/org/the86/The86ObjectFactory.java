package org.the86;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class The86ObjectFactory {
	private static final Charset UTF_8_CHAR_SET = Charset.forName("UTF-8");
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	private final JsonParser parser = new JsonParser();
	private Gson gson = null;

	@SuppressWarnings("unchecked")
	public <T> T createObject(TypeToken<T> typeToken, InputStream jsonContent) {
		if (jsonContent == null) {
			return isList(typeToken) ? (T) Collections.emptyList() : null;
		}
		return unmarshallToObj(typeToken, unmarshallToJson(jsonContent));
	}

	public JsonElement unmarshallToJson(InputStream jsonContent) {
		try {
			JsonElement element = parser.parse(new InputStreamReader(
					jsonContent, UTF_8_CHAR_SET));
			if (element.isJsonObject()) {
				return element.getAsJsonObject();
			} else if (element.isJsonArray()) {
				return element.getAsJsonArray();
			} else {
				throw new IllegalStateException(
						"Unknown content found in response." + element);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			closeStream(jsonContent);
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T unmarshallToObj(TypeToken<T> typeToken, JsonElement response) {
		return (T) getGson().fromJson(response, typeToken.getType());
	}

	private Gson getGson() {
		if (gson == null) {
			gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
		}
		return gson;
	}

	private void closeStream(InputStream is) {
		try {
			if (is != null) {
				is.close();
			}
		} catch (IOException e) {
			new RuntimeException();
		}
	}

	private <T> boolean isList(TypeToken<T> typeToken) {
		return List.class.isAssignableFrom(typeToken.getRawType());
	}
}

package org.the86;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.the86.model.User;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class The86Impl implements The86 {
	private static final String METHOD_DELETE   = "DELETE";
    private static final String METHOD_GET      = "GET";
    private static final String METHOD_POST     = "POST";
    private static final String METHOD_PUT      = "PUT";
	private static final String API_VERSION 	= "v1";

	private String domain = null;
	private String email = null;
	private String password = null;
	private String token = null;
	
	private The86ObjectFactoryImpl the86ObjFactory = new The86ObjectFactoryImpl();

	public static void main(String args[]) {
		String domain = args[0];
		String email = args[1];
		String password = args[2];
		The86 the86 = new The86Impl(domain, email, password);
	}

	public The86Impl(String domain, String email, String password) {
		this.domain = domain;
		this.email = email;
		this.password = password;
		User user = authorize(email, password);
		
		System.out.println(token);
		System.out.println(user);
	}

	public User authorize(String email, String password) {
		Gson gson = new Gson();
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		params.put("password", password);
		String url = buildUrl("/users/authenticate");
		JsonObject e = (JsonObject)the86ObjFactory.unmarshallToJson(doPost(url, params));
		token = gson.fromJson(e.get("user_access_token"), String.class);
		User user = gson.fromJson(e.get("user"), User.class);
		return user;
	}

	public String buildUrl(String resource) {
		return "http://" + domain + "/api/" + API_VERSION + "/" + resource;
	}

	private InputStream doGet(String url) {
		return doRequest(url, METHOD_GET);
	}

	private InputStream doPut(String url) {
		return doRequest(url, METHOD_PUT);
	}

	private InputStream doPost(String url, Map<String, String> params) {
		return doRequest(url, METHOD_POST, params);
	}

	private InputStream doDelete(String url) {
		return doRequest(url, METHOD_DELETE);
	}

	private InputStream doRequest(String url, String requestMethod) {
        return doRequest(url, requestMethod, null);
	}

	private InputStream doRequest(String url, String requestMethod, Map<String, String> map) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url)
					.openConnection();
			//conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setDoOutput(requestMethod.equals(METHOD_POST) || requestMethod.equals(METHOD_PUT));
            conn.setRequestMethod(requestMethod);

            if(map != null && !map.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (String key : map.keySet()) {
                    sb.append(sb.length() > 0 ? "&" : "")
                        .append(key)
                        .append("=")
                        .append(URLEncoder.encode(map.get(key), "UTF-8"));
                }
                conn.getOutputStream().write(sb.toString().getBytes());
                conn.getOutputStream().close();
            }

			if (conn.getResponseCode() > 399) {
				return null;
			} else {
				return new BufferedInputStream(
                    conn.getInputStream()
                );
			}
		} catch (IOException e) {
			return null;
			//throw new TrelloException(e.getMessage());
		}
	}

}

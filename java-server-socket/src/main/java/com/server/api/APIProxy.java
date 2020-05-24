package com.server.api;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class APIProxy {
	
	private static final String API_KEY = "&apikey=c882679b";
	private static final String API_URL = "http://www.omdbapi.com/";
	private static final String API_TITLE_PREFIX = "?t=";

	public static Object getMovies(int length, String title) {
		try {
			List<String> titles = new ArrayList<>();
			URL url = new URL(buildUrl(title));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int status = connection.getResponseCode();
			if(status == 200) {
				InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
				JsonObject response = new Gson().fromJson(inputStreamReader, JsonObject.class);
				titles = buildTitleListWithResponse(length, response);
				inputStreamReader.close();
			}
			return titles;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	private static List<String> buildTitleListWithResponse(int length, JsonObject response) {
		List<String> titles = new ArrayList<>();
		JsonObject object = new JsonObject();
		
		for(int index = 0; index < length; index++) {
			if(response.get("Search") != null) {
				object = (JsonObject) response.get("Search").getAsJsonArray().get(index);
			} else {
				object = response;
				index = length;
			}
			titles.add(String.valueOf(object.get("Title")));
			System.out.println(object.get("Title"));
		}
		return titles;
	}

	private static String buildUrl(String title) {
		return API_URL + API_TITLE_PREFIX + title + API_KEY;
	}
	
	

}

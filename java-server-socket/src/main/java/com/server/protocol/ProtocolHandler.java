package com.server.protocol;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProtocolHandler {

	public static Protocol buildProtocolBaseOnRequest(String request) {
		Protocol protocol = new Protocol();
		if(isValidRequest(request)) {
			protocol.setQueryLength(getQueryLength(request));
			protocol.setQuery(getQuery(request));
			protocol.setValid(true);
		} else {
			protocol.setValid(false);
		}
		
		return protocol;
	}

	private static int getQueryLength(String request) {
		return Integer.parseInt(request.substring(0, request.lastIndexOf(":")));
	}

	private static String getQuery(String request) {
		String query = request.substring(request.lastIndexOf(":") + 1);
		query = query.replaceAll(" ", "%20");
		return query;
	}

	private static boolean isValidRequest(String request) {
		Pattern pattern = Pattern.compile("[0-9]*:[A-Za-z0-9 ]*");
		Matcher matcher = pattern.matcher(request);
		return matcher.matches();
	}

	public static String buildResponse(List<String> response) {
		String res = "";
		int length = response.size();

		if(length != 0) {
			res += length + ":";
		}

		for(int index = 0; index < length; index++) {
			res += response.get(index) + "\n";
		}
		return res;
	}

}

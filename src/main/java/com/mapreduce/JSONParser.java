package com.mapreduce;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONParser {
	public static final String suffix = "}, {\"";
	public static final String prefix = "review_id";

	public static JSONObject parse(String s) {
		s = "{\"" + s;
		if (s.endsWith(suffix)) {
			s = s.substring(0, s.length() - suffix.length() + 1);
		} else {
			s = s.substring(0, s.length() - 1);
		}
		// System.out.println("*"+s+"*");
		return new JSONObject(new JSONTokener(s));
	}
}
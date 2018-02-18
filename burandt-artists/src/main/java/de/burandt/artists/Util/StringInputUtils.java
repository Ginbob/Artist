package de.burandt.artists.Util;

public class StringInputUtils {

	public static String eraseLeadingComma(String string) {
		if (string.startsWith(",")) {
			string = string.substring(1);
		}
		return string;
	}
}

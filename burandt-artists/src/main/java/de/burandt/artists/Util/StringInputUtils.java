package de.burandt.artists.Util;

public class StringInputUtils {

	public static void eraseLeadingComma(String string) {
		if (string.startsWith(",")) {
			string = string.substring(1);
		}
	}
	
	public static void eraseLeadingComma(String... strings) {
		for (int i = 0; i <= strings.length; i++ ) {
			if (strings[i].startsWith(",")) {
				strings[i] = strings[i].substring(1);
			}
		}
	}
}

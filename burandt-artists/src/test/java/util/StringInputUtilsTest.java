package util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import de.burandt.artists.Util.StringInputUtils;

public class StringInputUtilsTest {

	@Test
	public void should_erase_leading_comma() {
		String commaString = ",String with leading Comma";
		String nonCommaString = "String with leading Comma";
		
		StringInputUtils.eraseLeadingComma(commaString);
		assertThat(commaString.equals(nonCommaString));
	}
}

package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.Language;

public class Languagetest {
	Language l = new Language("Nederlands");

	@Test
	public void testLanguage() {
		l.setName("Dutch");
		assertEquals("Dutch", l.toString());
	}

}

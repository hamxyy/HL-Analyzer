package com.shs.hl.tools.codevalidator;

import java.io.File;

import junit.framework.TestCase;

import org.junit.Test;

import com.shs.hl.tools.codevalidator.utils.Util;

public class UtilTest extends TestCase {

	File	input;
	String	linebreak	= System.getProperty("line.separator");

	String	quickBrown	= "The quick brown fox jumps over the lazy dog"
								+ linebreak
								+ " The quick brown fox jumps over the lazy dog"
								+ linebreak
								+ "The quick brown fox jumps over the lazy dog"
								+ linebreak + linebreak
								+ "The quick brown fox jumps over the lazy dog"
								+ linebreak + linebreak

								+ "The quick brown fox jumps over the lazy dog"
								+ linebreak
								+ "The quick brown fox jumps over the lazy dog";

	@Override
	protected void setUp() throws Exception {
		input = new File("UtilText.txt");
		assertNotNull(input);
		assertTrue(input.exists());
	}

	@Test
	public void testGetLines() {
		assertEquals(quickBrown, Util.getLines(input));
	}
}

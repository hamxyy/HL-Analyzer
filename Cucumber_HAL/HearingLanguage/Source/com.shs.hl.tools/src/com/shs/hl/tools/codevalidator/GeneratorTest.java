package com.shs.hl.tools.codevalidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.junit.Test;

import com.shs.hl.tools.codevalidator.utils.Util;
import com.shs.text.compare.diff_match_patch;
import com.shs.text.compare.diff_match_patch.Diff;
import com.shs.text.compare.diff_match_patch.Operation;

public class GeneratorTest extends AbstractGeneratorTest
{

	final diff_match_patch	matcher	= new diff_match_patch();

	@Test
	public void testGenerate() {
		for (Entry<String, File> entry : sourceMap.entrySet()) {
			if (TARGETMAP.get(entry.getKey()) == null)
				System.out.println(entry.getKey());
			// assertNotNull(TARGETMAP.get(entry.getKey()));
		}
		System.out.println("*******************");
		for (Entry<String, File> entry : TARGETMAP.entrySet()) {
			// if (TARGETMAP.get(entry.getKey()) == null)
			System.out.println(entry.getKey());
			// assertNotNull(TARGETMAP.get(entry.getKey()));
		}

	}

	@Test
	public void testCompare() {
		boolean failed = false;
		for (Entry<String, File> entry : sourceMap.entrySet()) {
			boolean writeFile = false;
			assertNotNull(TARGETMAP.get(entry.getKey()));
			String xpt = Util.getLines(entry.getValue());
			String xtend = Util.getLines(TARGETMAP.get(entry.getKey()));
			LinkedList<Diff> diffs = matcher.diff_main(xpt, xtend);
			for (Diff diff : diffs) {
				if (!diff.operation.equals(Operation.EQUAL)) {
					writeFile = true;
					break;
				}
			}
			String diffString = matcher.diff_prettyHtml(diffs);
			// write file only if changes are available
			if (writeFile) {
				failed = true;
				try {
					Util.writeFile(entry.getKey() + ".html", diffString);
				} catch (IOException e) {
					fail("failed to write diff file" + entry.getKey());
					e.printStackTrace();
				}

			}
		}
		assertFalse(failed);
	}

}

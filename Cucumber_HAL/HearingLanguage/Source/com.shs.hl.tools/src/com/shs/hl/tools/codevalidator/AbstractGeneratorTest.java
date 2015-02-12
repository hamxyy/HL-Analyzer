package com.shs.hl.tools.codevalidator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.mwe2.launch.runtime.Mwe2Launcher;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.shs.hl.tools.codevalidator.utils.Util;

@RunWith((XtextRunner.class))
@InjectWith(HearingLanguageInjectorProvider.class)
public abstract class AbstractGeneratorTest {
	final List<File>		files		= new ArrayList<File>();
	final String			sourceDir	= "csxpt";
	final List<File>		targetFiles	= new ArrayList<File>();
	final String			targetDir	= "cshrp/src-gen";
	final Map<String, File>	sourceMap	= new HashMap<String, File>();
	final Map<String, File>	TARGETMAP	= new HashMap<String, File>();

	@Inject
	IGenerator				generator;

	@BeforeClass
	public static void init() {
		Mwe2Launcher
				.main(new String[] { "src/com/shs/hl/tests/generator/xtendMwe.mwe2" });
		Util.cleanOutDir();
	}

	@Before
	public void setUp() throws Exception {
		files.addAll(getFiles(sourceDir));
		targetFiles.addAll(getFiles(targetDir));
		for (File file : files) {
			sourceMap.put(file.getName(), file);
		}
		for (File file : targetFiles) {
			TARGETMAP.put(file.getName(), file);
		}
		// Util.cleanOutDir();

	}

	private List<File> getFiles(final String dir) {
		List<File> files = new ArrayList<File>();
		File file = new File(dir);
		if (file.isDirectory()) {
			for (File ele : file.listFiles()) {
				if (ele.isDirectory()) {
					files.addAll(getFiles(file.getAbsolutePath()));
				} else {
					files.add(ele);
				}
			}
		} else {
			files.add(file);
		}
		return files;
	}
}

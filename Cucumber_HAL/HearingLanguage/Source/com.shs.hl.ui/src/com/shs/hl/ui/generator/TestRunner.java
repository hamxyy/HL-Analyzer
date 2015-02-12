package com.shs.hl.ui.generator;

import static com.shs.hl.ui.utils.Constants.GENERATION_OUTPUT_FOLDER;
import static com.shs.hl.ui.utils.Constants.REFERENCE_DIR;
import static com.shs.hl.ui.utils.Constants.TEST_ASSEMBLY_NAME;
import static com.shs.hl.ui.utils.Constants.TEST_ASSEMBLY_NAMESPACE;
import static com.shs.hl.ui.utils.Constants.VENDOR_TEST_OUTPUT;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.google.common.collect.ImmutableMap;
import com.shs.hl.ui.utils.Constants;
import com.shs.hl.ui.utils.LoggerUtil;

public class TestRunner extends CSharpGenerator {

	final String	TESTRUNNERBATCH	= "testrunner.bat";

	public TestRunner(final String messageConsoleName) {
		super(messageConsoleName);

	}

	public void runGeneration1(final ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			// generate c# code

			startUpGeneration(selection);
			final List<Boolean> error = new ArrayList<Boolean>();
			final String nameSpace = store.getString(TEST_ASSEMBLY_NAMESPACE);
			error.add(testEmpty(TEST_ASSEMBLY_NAMESPACE, nameSpace));
			final String testAssemblyName = store.getString(TEST_ASSEMBLY_NAME);
			error.add(testEmpty(TEST_ASSEMBLY_NAME, testAssemblyName));
			final String vendorTestOutput = store.getString(VENDOR_TEST_OUTPUT);
			error.add(testEmpty(VENDOR_TEST_OUTPUT, vendorTestOutput));
			final String referenceDirectory = store.getString(REFERENCE_DIR);
			error.add(testEmpty(REFERENCE_DIR, referenceDirectory));
			final String outputRoot = store.getString(GENERATION_OUTPUT_FOLDER);
			error.add(testEmpty(GENERATION_OUTPUT_FOLDER, outputRoot));

			// optional
			final String runtime = store.getString(Constants.TEST_RUNTIME);
			{

			}

			// optional Log file

			if (error.contains(Boolean.TRUE)) {
				errorConsoleStream.println("Configuration settings are not complete, use preferences page to set missing values");
			} else {
				final ImmutableMap<String, String> options = new ImmutableMap.Builder<String, String>()
						.put(Constants.TEST_RUNTIME, runtime)
						.put(GENERATION_OUTPUT_FOLDER, outputRoot)
						.put(Constants.TESTING, Constants.TESTING)
						.put(Constants.REFERENCE_DIR, referenceDirectory)
						.put(Constants.FULLY_QUALIFIED_NAME_D8, nameSpace + "." + testAssemblyName)
						.put(Constants.VENDOR_OUT_PUT, vendorTestOutput).build();
				// build assembly
				startUpUnconfiguratedGeneration((IStructuredSelection) selection, options);
				runTest(outputRoot + File.separator + vendorTestOutput + File.separator + nameSpace + "." + testAssemblyName + ".dll /Test");
			}
		}
	}

	/**
	 * create batch file to run external test-runner.exe
	 * 
	 * @param command
	 *            String
	 */
	private void runTest(final String command) {
		final String batch = store.getString(Constants.TEST_RUNNER_PATH);
		final ProcessBuilder pb;
		try {
			final File file = new File(batch);
			final String dir = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator));
			final String comm = file.getCanonicalFile().toString() + " " + command;
			final File batchFile = new File(dir + File.separator + TESTRUNNERBATCH);
			// wrap command in batch file and run from java
			writeBatch(batchFile, comm);
			pb = new ProcessBuilder(batchFile.getCanonicalFile().toString());
			pb.directory(new File(dir));
			runExternal(pb);
			batchFile.delete();
		} catch (final IOException e) {
			errorConsoleStream.print("Failed to run external batch: " + command);
			e.printStackTrace();
		}
	}

	/**
	 * Write a batch file that can be run from java code
	 * 
	 * @param batch
	 *            File
	 * @param runnerPath
	 *            String
	 */
	private void writeBatch(final File batch, final String runnerPath) {
		Writer writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(batch));
			writer.write(runnerPath);
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (final IOException e) {
				LoggerUtil.log(IStatus.ERROR, "Error while trying to write Batch file", e);
				errorConsoleStream.println("An exception occured while writing Batch file : " + e.getMessage());
			}
		}

	}
}

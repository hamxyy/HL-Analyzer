package com.shs.hl.ui.preferences;

/**
 * 
 * Initialiser for preferences 
 * @author Z00345PB
 * 
 */

import java.io.File;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.shs.hl.ui.internal.HearingLanguageActivator;
import com.shs.hl.ui.utils.Constants;

public class HLPrefInitialiser extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		//final IPreferenceStore store = HearingLanguageActivator.getInstance().getPreferenceStore();
		final IPreferenceStore store = HearingLanguageActivator.getInstance().getPreferenceStore();
		
		
		final String vendorOutput  			 = System.getProperty(Constants.VENDOR_OUTPUT_ENVIRONMENT, "SI");
		final String vendorAssemblyNamespace = System.getProperty(Constants.VENDOR_ASSEMBLY_NAMESPACE, "SHS.SAT.Fitting.Business.HearingLanguageRuntime");
		final String vendorBatchBaseDir      = System.getProperty(Constants.VENDOR_GENERATOR_BASEDIR, "AssemblyGenerator");
		final String vendorBatch  			 = System.getProperty(Constants.VENDOR_GENERATOR_BATCHFILE, "BuildAssemblyDebug.bat");
		final String vendorD8PlatformMacros  = System.getProperty(Constants.VENDOR_D8_PLATFORM_MACROS, "D8PlatformMacros"); 
		final String vendorD9PlatformMacros	 = System.getProperty(Constants.VENDOR_D9_PLATFORM_MACROS, "D9PlatformMacros"); 
		final Boolean useProjectNameAsDLL 	 = Boolean.parseBoolean(System.getProperty(Constants.USE_PROJECT_NAME_AS_DLL_SELECTION,"false"));
		
		
		Constants.USE_PROJECT_NAME_AS_DLL =    useProjectNameAsDLL;
		
		// do some remapping between vendor settings and internal variables
		store.setDefault(Constants.USE_PROJECT_NAME_AS_DLL_SELECTION, useProjectNameAsDLL);
		store.setDefault(Constants.BUILD_ASSEMBLY_BATCH, vendorBatch);
		store.setDefault(Constants.ASSEMBLY_GENERATOR_BASEDIR, vendorBatchBaseDir);
		store.setDefault(Constants.VENDOR_OUT_PUT, vendorOutput);
		store.setDefault(Constants.ASSEMBLY_NAMESPACE, vendorAssemblyNamespace);
		store.setDefault(Constants.PLATFORM_MACROS_D8, vendorD8PlatformMacros);
		store.setDefault(Constants.PLATFORM_MACROS_D9, vendorD9PlatformMacros);
		
		
		//store.setDefault(Constants.TEST_RUNNER_PATH, "W:/Workspace/SIFIT7.0.0.IT20/HIData/HI/hiMacro/com.shs.d8PlatformMacros/runner/runner.bat");
		store.setDefault("boolean", true);
		store.setDefault("GraphvizPath", "");

		store.setDefault(Constants.REFERENCE_DIR, getFittingInstallDir());
		store.setDefault(Constants.SHOW_DEBUG_LIST, true);
		store.setDefault(Constants.GENERATION_OUTPUT_FOLDER, "src-gen"); // also used for SRC_GEN 
	}

	String getFittingInstallDir() {
		final String bt32 = System.getenv("%programfiles%" + " " + "(x86)");
		if (new File(bt32 + "/SAT/Fitting").exists())
			return bt32 + "/SAT/Fitting";
		else {
			return System.getenv("ProgramFiles") + "/SAT/Fitting";
		}
	}
}

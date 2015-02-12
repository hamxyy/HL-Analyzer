package com.shs.hl.ui.utils;




public class Constants
{

	// ================================================================================================================
	// General defaults
	// ================================================================================================================
	public static boolean 		USE_PROJECT_NAME_AS_DLL 	= false;
	public static final String USE_PROJECT_NAME_AS_DLL_SELECTION = "UseProjectNameAsDLL";
	
	// ================================================================================================================
	// Vendor Settings - configurable via preference page or system environment
	// ================================================================================================================
	public static final String	VENDOR_OUTPUT_ENVIRONMENT	= "AssemblyPath";				// -->> VENDOR_OUT_PUT
	public static final String	VENDOR_ASSEMBLY_NAMESPACE	= "AssemblyNamespace";			// -->> ASSEMBLY_NAMESPACE
	public static final String	VENDOR_GENERATOR_BASEDIR	= "GeneratorBatchFileBaseDir";	// -->> ASSEMBLY_GENERATOR_BASEDIR
	public static final String	VENDOR_GENERATOR_BATCHFILE	= "GeneratorBatchFile";		    // -->> BUILD_ASSEMBLY_BATCH
	public static final String	VENDOR_D8_PLATFORM_MACROS	= "VendorPlatformMacros8";			// -->> D8_PLATFORM_MACROS
	public static final String	VENDOR_D9_PLATFORM_MACROS	= "VendorPlatformMacros9";			// -->> D9_PLATFORM_MACROS

	// ================================================================================
	// Internal mapped variables
	// ================================================================================

	public static final String	VENDOR_OUT_PUT				= "vendorOutPut";				// <<-- VENDOR_OUTPUT_ENVIRONMENT
	public static final String	BUILD_ASSEMBLY_BATCH		= "BuildAssemblyDebug";			// <<-- VENDOR_GENERATOR_BATCHFILE
	public static final String	ASSEMBLY_GENERATOR_BASEDIR	= "AssemblyGenerator";			// <<-- ASSEMBLY_GENERATOR_BASEDIR
	public static final String	ASSEMBLY_NAMESPACE			= "AssemblyNamespace";			// <<-- VENDOR_ASSEMBLY_NAMESPACE 
	public static final String	PLATFORM_MACROS_D8			= "D8PlatformMacros";			// <<-- VENDOR_D9_PLATFORM_MACROS
	public static final String	PLATFORM_MACROS_D9			= "D9PlatformMacros";			// <<-- VENDOR_D9_PLATFORM_MACROS
	public static final String  PLATFORM_MACROS				= "PlatformMacros";

	public static final String PLATFORM_DELIMITER = "";
	
	// internal configuration settings
	public static final String	STDLIBNAME					= "com.shs.hl.stdlib";
	public static final String	SELECTED_STDLIB_PLATFORM	= "SelectedPlatForm";
	public static final String	GENERATION_OUTPUT_FOLDER	= "outputRoot";	// src-gen 																							// folder!!

	public static final String	BUILD_ASSEMBLY_COMMAND_PATH	= "BuildAssemblyCommand";		// full combined search path to batch file
	public static final String	REFERENCE_DIR				= "ReferenceDir";				// path to fitting dlls
	public static final String	FULLY_QUALIFIED_NAME_D8		= "FullyQualifiedNameD8";
	public static final String	FULLY_QUALIFIED_NAME_D9		= "FullyQualifiedNameD9";
	public static final String	FULLY_QUALIFIED_NAME		= "FullyQualifiedName";

	// ================================================================================================================
	// Testing relevant constants
	// ================================================================================================================

	public static final String	VENDOR_TEST_OUTPUT			= "VendorTestOutput";

	public static final String	TESTING						= "testing";

	public static final String	TEST_ASSEMBLY_NAME			= "TestAssemblyName";
	public static final String	TEST_ASSEMBLY_NAMESPACE		= "TestAssemblyNamespace";
	public static final String	TEST_RUNNER_PATH			= "TestRunnerPath";
	public static final String	TEST_RUNTIME				= "TestRuntime";

	// ================================================================================================================
	// Debugging relevant constants
	// ================================================================================================================
	public static final String	SHOW_DEBUG_LIST				= "ShowDebugList";

}

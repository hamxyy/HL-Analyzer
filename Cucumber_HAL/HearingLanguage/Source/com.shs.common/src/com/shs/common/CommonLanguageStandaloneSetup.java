
package com.shs.common;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class CommonLanguageStandaloneSetup extends CommonLanguageStandaloneSetupGenerated{
 
	public static void doSetup() {
		new CommonLanguageStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


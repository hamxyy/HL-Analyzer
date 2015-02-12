package com.shs.hl;

import org.eclipse.xtext.ISetup;

/**
 * Initialization support for running Xtext languages without equinox extension
 * registry
 */
public class HearingLanguageStandaloneSetup extends HearingLanguageStandaloneSetupGenerated implements ISetup
{

	public static void doSetup()
	{
		new HearingLanguageStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

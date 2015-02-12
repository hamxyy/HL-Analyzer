
package de.itemis.xtext.typesystem.dsl;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class TsDslStandaloneSetup extends TsDslStandaloneSetupGenerated{

	public static void doSetup() {
		new TsDslStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


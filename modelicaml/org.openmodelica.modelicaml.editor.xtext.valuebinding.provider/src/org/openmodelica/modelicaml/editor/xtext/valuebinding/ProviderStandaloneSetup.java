
package org.openmodelica.modelicaml.editor.xtext.valuebinding;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class ProviderStandaloneSetup extends ProviderStandaloneSetupGenerated{

	public static void doSetup() {
		new ProviderStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


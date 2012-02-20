
package org.openmodelica.modelicaml.editor.xtext.valuebinding;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class ClientStandaloneSetup extends ClientStandaloneSetupGenerated{

	public static void doSetup() {
		new ClientStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


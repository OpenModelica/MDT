
package org.openmodelica.modelicaml.editor.xtext.valuebinding;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class MediatorStandaloneSetup extends MediatorStandaloneSetupGenerated{

	public static void doSetup() {
		new MediatorStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


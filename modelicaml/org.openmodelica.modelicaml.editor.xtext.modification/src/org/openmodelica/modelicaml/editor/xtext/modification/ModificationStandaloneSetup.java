
package org.openmodelica.modelicaml.editor.xtext.modification;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class ModificationStandaloneSetup extends ModificationStandaloneSetupGenerated{

	public static void doSetup() {
		new ModificationStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


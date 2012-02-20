
package org.openmodelica.modelicaml.editor.xtext.model;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class ModeleditorStandaloneSetup extends ModeleditorStandaloneSetupGenerated{

	public static void doSetup() {
		new ModeleditorStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


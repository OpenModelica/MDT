
package org.openmodelica.modelicaml.editor.xtext.declaration;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class DeclarationStandaloneSetup extends DeclarationStandaloneSetupGenerated{

	public static void doSetup() {
		new DeclarationStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


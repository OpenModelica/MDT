
package org.openmodelica.modelicaml.editor.xtext.state;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class StatetransitionguardexpressionStandaloneSetup extends StatetransitionguardexpressionStandaloneSetupGenerated{

	public static void doSetup() {
		new StatetransitionguardexpressionStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


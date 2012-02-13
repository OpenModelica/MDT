
package org.openmodelica.modelicaml.editor.xtext.equation;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class EquationsectionStandaloneSetup extends EquationsectionStandaloneSetupGenerated{

	public static void doSetup() {
		new EquationsectionStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


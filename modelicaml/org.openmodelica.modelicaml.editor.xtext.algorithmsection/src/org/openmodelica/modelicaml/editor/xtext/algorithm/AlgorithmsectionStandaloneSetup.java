
package org.openmodelica.modelicaml.editor.xtext.algorithm;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class AlgorithmsectionStandaloneSetup extends AlgorithmsectionStandaloneSetupGenerated{

	public static void doSetup() {
		new AlgorithmsectionStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


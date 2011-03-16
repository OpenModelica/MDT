
package org.openmodelica.modelicaml.editor.xtext.array;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class ArraysubscriptStandaloneSetup extends ArraysubscriptStandaloneSetupGenerated{

	public static void doSetup() {
		new ArraysubscriptStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


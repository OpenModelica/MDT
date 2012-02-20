
package org.openmodelica.modelicaml.editor.xtext.activity;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class ActivitycontrolflowguardexpressionStandaloneSetup extends ActivitycontrolflowguardexpressionStandaloneSetupGenerated{

	public static void doSetup() {
		new ActivitycontrolflowguardexpressionStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


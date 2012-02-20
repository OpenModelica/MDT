
package org.openmodelica.modelicaml.editor.xtext.condition;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class ConditionalattributeStandaloneSetup extends ConditionalattributeStandaloneSetupGenerated{

	public static void doSetup() {
		new ConditionalattributeStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


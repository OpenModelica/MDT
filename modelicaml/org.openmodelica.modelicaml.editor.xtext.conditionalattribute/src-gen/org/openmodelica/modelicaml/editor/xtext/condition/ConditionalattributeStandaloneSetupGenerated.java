
package org.openmodelica.modelicaml.editor.xtext.condition;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.ISetup;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Generated from StandaloneSetup.xpt!
 */
@SuppressWarnings("all")
public class ConditionalattributeStandaloneSetupGenerated implements ISetup {

	public Injector createInjectorAndDoEMFRegistration() {
		org.openmodelica.modelicaml.editor.xtext.model.ModeleditorStandaloneSetup.doSetup();

		Injector injector = createInjector();
		register(injector);
		return injector;
	}
	
	public Injector createInjector() {
		return Guice.createInjector(new org.openmodelica.modelicaml.editor.xtext.condition.ConditionalattributeRuntimeModule());
	}
	
	public void register(Injector injector) {
	if (!EPackage.Registry.INSTANCE.containsKey("http://www.openmodelica.org/modelicaml/editor/xtext/condition/Conditionalattribute")) {
		EPackage.Registry.INSTANCE.put("http://www.openmodelica.org/modelicaml/editor/xtext/condition/Conditionalattribute", org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.ConditionalattributePackage.eINSTANCE);
	}

		org.eclipse.xtext.resource.IResourceFactory resourceFactory = injector.getInstance(org.eclipse.xtext.resource.IResourceFactory.class);
		org.eclipse.xtext.resource.IResourceServiceProvider serviceProvider = injector.getInstance(org.eclipse.xtext.resource.IResourceServiceProvider.class);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("modelicamlconditionalattribute", resourceFactory);
		org.eclipse.xtext.resource.IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().put("modelicamlconditionalattribute", serviceProvider);
		




	}
}

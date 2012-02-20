
package org.openmodelica.modelicaml.editor.xtext.activity;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.ISetup;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Generated from StandaloneSetup.xpt!
 */
@SuppressWarnings("all")
public class ActivitycontrolflowguardexpressionStandaloneSetupGenerated implements ISetup {

	public Injector createInjectorAndDoEMFRegistration() {
		org.openmodelica.modelicaml.editor.xtext.model.ModeleditorStandaloneSetup.doSetup();

		Injector injector = createInjector();
		register(injector);
		return injector;
	}
	
	public Injector createInjector() {
		return Guice.createInjector(new org.openmodelica.modelicaml.editor.xtext.activity.ActivitycontrolflowguardexpressionRuntimeModule());
	}
	
	public void register(Injector injector) {
	if (!EPackage.Registry.INSTANCE.containsKey("http://www.openmodelica.org/modelicaml/editor/xtext/activity/Activitycontrolflowguardexpression")) {
		EPackage.Registry.INSTANCE.put("http://www.openmodelica.org/modelicaml/editor/xtext/activity/Activitycontrolflowguardexpression", org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.ActivitycontrolflowguardexpressionPackage.eINSTANCE);
	}

		org.eclipse.xtext.resource.IResourceFactory resourceFactory = injector.getInstance(org.eclipse.xtext.resource.IResourceFactory.class);
		org.eclipse.xtext.resource.IResourceServiceProvider serviceProvider = injector.getInstance(org.eclipse.xtext.resource.IResourceServiceProvider.class);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("activitycontrolflowguardexpression", resourceFactory);
		org.eclipse.xtext.resource.IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().put("activitycontrolflowguardexpression", serviceProvider);
		




	}
}

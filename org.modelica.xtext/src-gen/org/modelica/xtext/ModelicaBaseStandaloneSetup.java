package org.modelica.xtext;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.ISetup;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Generated from StandaloneSetup.xpt!
 */
public class ModelicaBaseStandaloneSetup implements ISetup {

	public static void doSetup() {
		new ModelicaBaseStandaloneSetup().createInjectorAndDoEMFRegistration();
	}

	public Injector createInjectorAndDoEMFRegistration() {
		org.eclipse.xtext.common.TerminalsStandaloneSetup.doSetup();

		Injector injector = createInjector();
		register(injector);
		return injector;
	}
	
	public Injector createInjector() {
		return Guice.createInjector(new org.modelica.xtext.ModelicaBaseRuntimeModule());
	}
	
	public void register(Injector injector) {
	if (!EPackage.Registry.INSTANCE.containsKey("http://www.modelica.org/xtext/ModelicaBase")) {
		EPackage.Registry.INSTANCE.put("http://www.modelica.org/xtext/ModelicaBase", org.modelica.xtext.modelicaBase.ModelicaBasePackage.eINSTANCE);
	}

		org.eclipse.xtext.resource.IResourceFactory resourceFactory = injector.getInstance(org.eclipse.xtext.resource.IResourceFactory.class);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("mo", resourceFactory);
		

	//TODO registration of EValidators should be added here, too


	}
}

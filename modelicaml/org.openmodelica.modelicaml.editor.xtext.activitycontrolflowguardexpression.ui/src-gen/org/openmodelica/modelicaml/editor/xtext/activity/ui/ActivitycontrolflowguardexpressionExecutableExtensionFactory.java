
/*
 * generated by Xtext
 */
 
package org.openmodelica.modelicaml.editor.xtext.activity.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

/**
 *@generated
 */
public class ActivitycontrolflowguardexpressionExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return org.openmodelica.modelicaml.editor.xtext.activity.ui.internal.ActivitycontrolflowguardexpressionActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return org.openmodelica.modelicaml.editor.xtext.activity.ui.internal.ActivitycontrolflowguardexpressionActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.activity.Activitycontrolflowguardexpression");
	}
	
}

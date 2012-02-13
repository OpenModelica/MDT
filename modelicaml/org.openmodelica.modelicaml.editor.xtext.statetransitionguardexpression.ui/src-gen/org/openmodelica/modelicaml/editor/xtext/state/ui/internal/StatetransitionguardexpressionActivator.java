
/*
 * generated by Xtext
 */
package org.openmodelica.modelicaml.editor.xtext.state.ui.internal;

import org.apache.log4j.Logger;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

import java.util.Map;
import java.util.HashMap;

/**
 * Generated
 */
public class StatetransitionguardexpressionActivator extends AbstractUIPlugin {

	private Map<String,Injector> injectors = new HashMap<String,Injector>();
	private static StatetransitionguardexpressionActivator INSTANCE;

	public Injector getInjector(String languageName) {
		return injectors.get(languageName);
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		INSTANCE = this;
		try {
			
			injectors.put("org.openmodelica.modelicaml.editor.xtext.state.Statetransitionguardexpression", Guice.createInjector(
				Modules.override(Modules.override(getRuntimeModule("org.openmodelica.modelicaml.editor.xtext.state.Statetransitionguardexpression")).with(getUiModule("org.openmodelica.modelicaml.editor.xtext.state.Statetransitionguardexpression"))).with(getSharedStateModule())
			));
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage(), e);
			throw e;
		}
	}
	
	public static StatetransitionguardexpressionActivator getInstance() {
		return INSTANCE;
	}
	
	protected Module getRuntimeModule(String grammar) {
		
		if ("org.openmodelica.modelicaml.editor.xtext.state.Statetransitionguardexpression".equals(grammar)) {
		  return new org.openmodelica.modelicaml.editor.xtext.state.StatetransitionguardexpressionRuntimeModule();
		}
		
		throw new IllegalArgumentException(grammar);
	}
	protected Module getUiModule(String grammar) {
		
		if ("org.openmodelica.modelicaml.editor.xtext.state.Statetransitionguardexpression".equals(grammar)) {
		  return new org.openmodelica.modelicaml.editor.xtext.state.ui.StatetransitionguardexpressionUiModule(this);
		}
		
		throw new IllegalArgumentException(grammar);
	}
	
	protected Module getSharedStateModule() {
		return new org.eclipse.xtext.ui.shared.SharedStateModule();
	}
	
}

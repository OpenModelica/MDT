/*
 * generated by Xtext
 */
package org.openmodelica.modelicaml.editor.xtext.equation.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.autoedit.AbstractEditStrategyProvider;
import org.openmodelica.modelicaml.editor.xtext.model.ui.autoedit.ModelicaMLAutoEditStrategyProvider;

/**
 * Use this class to register components to be used within the IDE.
 */
public class EquationsectionUiModule extends org.openmodelica.modelicaml.editor.xtext.equation.ui.AbstractEquationsectionUiModule {
	public EquationsectionUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public Class<? extends AbstractEditStrategyProvider> bindAbstractEditStrategyProvider() {
		return ModelicaMLAutoEditStrategyProvider.class;
	}
}

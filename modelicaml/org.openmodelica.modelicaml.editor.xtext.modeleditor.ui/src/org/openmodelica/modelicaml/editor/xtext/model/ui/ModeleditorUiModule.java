/*
 * generated by Xtext
 */
package org.openmodelica.modelicaml.editor.xtext.model.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.autoedit.AbstractEditStrategyProvider;
import org.openmodelica.modelicaml.editor.xtext.model.ui.autoedit.ModelicaMLAutoEditStrategyProvider;

/**
 * Use this class to register components to be used within the IDE.
 */
public class ModeleditorUiModule extends org.openmodelica.modelicaml.editor.xtext.model.ui.AbstractModeleditorUiModule {
	public ModeleditorUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}
	
	
	@Override
	public Class<? extends AbstractEditStrategyProvider> bindAbstractEditStrategyProvider() {
		return ModelicaMLAutoEditStrategyProvider.class;
	}
}

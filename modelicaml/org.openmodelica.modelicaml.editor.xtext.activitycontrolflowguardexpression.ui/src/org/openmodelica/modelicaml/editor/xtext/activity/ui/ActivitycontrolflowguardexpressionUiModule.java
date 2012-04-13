/*
 * generated by Xtext
 */
package org.openmodelica.modelicaml.editor.xtext.activity.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.autoedit.AbstractEditStrategyProvider;
import org.eclipse.xtext.ui.editor.contentassist.XtextContentAssistProcessor;
import org.openmodelica.modelicaml.editor.xtext.model.ui.autoedit.ModelicaMLAutoEditStrategyProvider;

import com.google.inject.Binder;

/**
 * Use this class to register components to be used within the IDE.
 */
public class ActivitycontrolflowguardexpressionUiModule extends org.openmodelica.modelicaml.editor.xtext.activity.ui.AbstractActivitycontrolflowguardexpressionUiModule {
	public ActivitycontrolflowguardexpressionUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public Class<? extends AbstractEditStrategyProvider> bindAbstractEditStrategyProvider() {
		return ModelicaMLAutoEditStrategyProvider.class;
	}
	
	@Override
	public void configure(Binder binder) {
		super.configure(binder);
		binder.bind(String.class).annotatedWith(com.google.inject.name.Names.named((XtextContentAssistProcessor.COMPLETION_AUTO_ACTIVATION_CHARS))).toInstance(".,:");
	}
}

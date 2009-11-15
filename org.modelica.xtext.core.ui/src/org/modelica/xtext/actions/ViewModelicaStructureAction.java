package org.modelica.xtext.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.xtext.ui.common.editor.outline.XtextContentOutlinePage;
import org.eclipse.xtext.ui.common.editor.outline.actions.AbstractOutlineAction;
import org.eclipse.xtext.ui.core.internal.XtextPluginImages;
import org.modelica.xtext.internal.ModelicaASTActivator;

public class ViewModelicaStructureAction extends OutlineViewAlternativeAction {

	
	public ViewModelicaStructureAction(XtextContentOutlinePage outlinePage) {
		super("ViewModelicaStructure", outlinePage);
		setToolTipText("View Modelica structure");
		setDescription("View Modelica structure");
		setImageDescriptor(
				org.modelica.xtext.internal.ModelicaASTActivator.imageDescriptorFromPlugin(
						"org.modelica.xtext.core.ui", "icons/modelica_outline.png")
		);
		//setDisabledImageDescriptor(XtextPluginImages.DESC_ALPHAB_SORT_CO_DISABLED);
	}


	public void performRun() {
		ModelicaASTActivator.getInstance().getPreferenceStore().setValue("Outline.ViewType", 1);
	}
	
}

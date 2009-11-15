package org.modelica.xtext.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.ui.common.editor.outline.XtextContentOutlinePage;
import org.eclipse.xtext.ui.common.editor.outline.actions.AbstractOutlineAction;
import org.eclipse.xtext.ui.core.internal.XtextPluginImages;
import org.modelica.xtext.internal.ModelicaASTActivator;

public class ViewASTAction extends OutlineViewAlternativeAction {

	public ViewASTAction(XtextContentOutlinePage outlinePage) {
		super("ViewAST", outlinePage);
		setToolTipText("View AST");
		setDescription("View AST");
		setImageDescriptor(
				org.modelica.xtext.internal.ModelicaASTActivator.imageDescriptorFromPlugin(
						"org.modelica.xtext.core.ui", "icons/ast_16.png")
		);
		//setDisabledImageDescriptor(XtextPluginImages.DESC_ALPHAB_SORT_CO_DISABLED);
	}

	@Override
	public void performRun() {
		ModelicaASTActivator.getInstance().getPreferenceStore().setValue("Outline.ViewType", 0);
	}

}

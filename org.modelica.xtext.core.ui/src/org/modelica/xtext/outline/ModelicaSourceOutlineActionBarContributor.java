package org.modelica.xtext.outline;

import org.eclipse.xtext.ui.common.editor.outline.actions.IActionBarContributor.DefaultActionBarContributor;
import org.modelica.xtext.actions.ViewASTAction;
import org.modelica.xtext.actions.ViewModelicaStructureAction;

public class ModelicaSourceOutlineActionBarContributor extends
		DefaultActionBarContributor {
	
	@Override
	protected void addToolbarActions() {
		super.addToolbarActions();
		add(new ViewASTAction(getOutlinePage()));
		add(new ViewModelicaStructureAction(getOutlinePage()));
	}
}

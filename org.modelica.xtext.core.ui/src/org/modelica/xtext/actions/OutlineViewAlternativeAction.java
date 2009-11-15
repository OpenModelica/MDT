package org.modelica.xtext.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.xtext.ui.common.editor.outline.XtextContentOutlinePage;

public abstract class OutlineViewAlternativeAction extends Action {
	protected XtextContentOutlinePage outlinePage;
	protected List<Action> alternativeActions = new ArrayList();

	public OutlineViewAlternativeAction(String text, XtextContentOutlinePage outlinePage) {
		super(text);
		this.outlinePage = outlinePage;
	}

	public void addAlternativeAction(Action action) {
		alternativeActions.add(action);
	}

	@Override
	public void run() {
		setChecked(true);
		performRun();
	}
	
	public abstract void performRun();
}

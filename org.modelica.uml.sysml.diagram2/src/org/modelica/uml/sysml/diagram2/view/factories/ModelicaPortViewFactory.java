package org.modelica.uml.sysml.diagram2.view.factories;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.BasicNodeViewFactory;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPortNameEditPart;
import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

/**
 */
public class ModelicaPortViewFactory extends BasicNodeViewFactory {

	/** 
	 */
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createFontStyle());
		// styles.add(NotationFactory.eINSTANCE.createDescriptionStyle());
		// styles.add(NotationFactory.eINSTANCE.createFillStyle());
		// styles.add(NotationFactory.eINSTANCE.createLineStyle());
		return styles;
	}

	/**
	 */
	protected void decorateView(View containerView, View view,
			IAdaptable semanticAdapter, String semanticHint, int index,
			boolean persisted) {
		if (semanticHint == null) {
			semanticHint = SysmlVisualIDRegistry
					.getType(org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPortEditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		super.decorateView(containerView, view, semanticAdapter, semanticHint,
				index, persisted);
		/*
		getViewService().createNode(
				semanticAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(ModelicaPortNameEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		*/
	}

}

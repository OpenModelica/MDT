package org.modelica.uml.sysml.diagram2.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;

import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;

import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;

import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;

import org.modelica.uml.sysml.diagram2.edit.parts.ModelEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RequirementDescriptionEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RequirementEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RequirementIdEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RequirementLevelEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RequirementNameEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RequirementStatusEditPart;

import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

/**
 * @generated
 */
public class RequirementViewFactory extends AbstractShapeViewFactory {

	/**
	 * @generated 
	 */
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createShapeStyle());
		return styles;
	}

	/**
	 * @generated
	 */
	protected void decorateView(View containerView, View view,
			IAdaptable semanticAdapter, String semanticHint, int index,
			boolean persisted) {
		if (semanticHint == null) {
			semanticHint = SysmlVisualIDRegistry
					.getType(RequirementEditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		super.decorateView(containerView, view, semanticAdapter, semanticHint,
				index, persisted);
		if (!ModelEditPart.MODEL_ID.equals(SysmlVisualIDRegistry
				.getModelID(containerView))) {
			EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE
					.createEAnnotation();
			shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
			shortcutAnnotation.getDetails().put(
					"modelID", ModelEditPart.MODEL_ID); //$NON-NLS-1$
			view.getEAnnotations().add(shortcutAnnotation);
		}
		IAdaptable eObjectAdapter = null;
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null) {
			eObjectAdapter = new EObjectAdapter(eObject);
		}
		getViewService().createNode(
				eObjectAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(RequirementNameEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		getViewService().createNode(
				eObjectAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(RequirementDescriptionEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		getViewService().createNode(eObjectAdapter, view,
				SysmlVisualIDRegistry.getType(RequirementIdEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		getViewService().createNode(
				eObjectAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(RequirementLevelEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		getViewService().createNode(
				eObjectAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(RequirementStatusEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
	}

}

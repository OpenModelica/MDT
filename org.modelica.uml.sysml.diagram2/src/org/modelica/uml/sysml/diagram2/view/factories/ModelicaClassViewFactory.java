package org.modelica.uml.sysml.diagram2.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EcoreFactory;

import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;

import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;

import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;

import org.modelica.uml.sysml.diagram2.edit.parts.ModelEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassConnectionsEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEquationsEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassName2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassNestedEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassNameEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParametersEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassPartsEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassVariablesEditPart;

import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

/**
 * @generated
 */
public class ModelicaClassViewFactory extends AbstractShapeViewFactory {

	/**
	 * @generated 
	 */
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createFontStyle());
		styles.add(NotationFactory.eINSTANCE.createDescriptionStyle());
		styles.add(NotationFactory.eINSTANCE.createFillStyle());
		styles.add(NotationFactory.eINSTANCE.createLineStyle());
		return styles;
	}

	/**
	 * @generated NOT
	 */
	protected void decorateView(View containerView, View view,
			IAdaptable semanticAdapter, String semanticHint, int index,
			boolean persisted) {
		if (semanticHint == null) {
			semanticHint = SysmlVisualIDRegistry
					.getType(org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEditPart.VISUAL_ID);
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
		getViewService().createNode(
				semanticAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(ModelicaClassName2EditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		getViewService().createNode(
				semanticAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(ModelicaClassParametersEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		/*		getViewService().createNode(
		 semanticAdapter,
		 view,
		 SysmlVisualIDRegistry
		 .getType(ModelicaClassPartsEditPart.VISUAL_ID),
		 ViewUtil.APPEND, true, getPreferencesHint());
		 */
		getViewService().createNode(
				semanticAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(ModelicaClassVariablesEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		getViewService().createNode(
				semanticAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(ModelicaClassConnectionsEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		getViewService().createNode(
				semanticAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(ModelicaClassNestedEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		getViewService().createNode(
				semanticAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(ModelicaClassEquationsEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
	}

}

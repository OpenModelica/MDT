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

import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaConnector;
import org.modelica.uml.sysml.ModelicaRecord;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassConnections2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEquations2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassNameEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassNested2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParameters2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParts2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassVariables2EditPart;

import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

/**
 * @generated
 */
public class ModelicaClass2ViewFactory extends AbstractShapeViewFactory {

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
					.getType(org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClass2EditPart.VISUAL_ID);
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
						.getType(ModelicaClassNameEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		getViewService().createNode(
				semanticAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(ModelicaClassParameters2EditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		getViewService().createNode(
				semanticAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(ModelicaClassParts2EditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		getViewService().createNode(
				semanticAdapter,
				view,
				SysmlVisualIDRegistry
						.getType(ModelicaClassVariables2EditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());

		/*		getViewService().createNode(
		 semanticAdapter,
		 view,
		 SysmlVisualIDRegistry
		 .getType(ModelicaClassConnections2EditPart.VISUAL_ID),
		 ViewUtil.APPEND, true, getPreferencesHint());
		 */
		/*		getViewService().createNode(
		 semanticAdapter,
		 view,
		 SysmlVisualIDRegistry
		 .getType(ModelicaClassNested2EditPart.VISUAL_ID),
		 ViewUtil.APPEND, true, getPreferencesHint());
		 */

		ModelicaClass modelicaClass = (ModelicaClass) view.getElement();
		if (!(modelicaClass instanceof ModelicaConnector)
				&& !(modelicaClass instanceof ModelicaRecord)) {
			getViewService()
					.createNode(
							semanticAdapter,
							view,
							SysmlVisualIDRegistry
									.getType(ModelicaClassEquations2EditPart.VISUAL_ID),
							ViewUtil.APPEND, true, getPreferencesHint());
		}
	}

}

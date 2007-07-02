package org.modelica.uml.sysml.diagram2.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.modelica.uml.sysml.diagram2.edit.parts.GeneralizationEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClass2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassConnections2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassConnectionsEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEquations2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEquationsEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassName2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassNameEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassNested2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassNestedEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParameters2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParametersEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParts2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassPartsEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassVariables2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassVariablesEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaCompositionEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaCompositionNameEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaConnectionEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaEquationPropertyEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPortNameEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty3EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty4EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPropertyEditPart;

import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPropertyNameEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaTypeEditPart;

import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaTypeNameEditPart;

import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPortEditPart;

import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

import org.modelica.uml.sysml.diagram2.view.factories.GeneralizationViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClass2ViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassConnections2ViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassConnectionsViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassEquations2ViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassEquationsViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassName2ViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassNameViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassNested2ViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassNestedViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassParameters2ViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassParametersViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassParts2ViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassPartsViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassVariables2ViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassVariablesViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaClassViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaCompositionNameViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaCompositionViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaConnectionViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaEquationPropertyViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaPortNameViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaProperty2ViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaProperty3ViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaProperty4ViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaPropertyNameViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaPropertyViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaTypeNameViewFactory;
import org.modelica.uml.sysml.diagram2.view.factories.ModelicaTypeViewFactory;

import org.modelica.uml.sysml.diagram2.view.factories.ModelicaPortViewFactory;

/**
 * @generated
 */
public class SysmlViewProvider extends AbstractViewProvider {

	/**
	 * @generated
	 */
	protected Class getDiagramViewClass(IAdaptable semanticAdapter,
			String diagramKind) {
		EObject semanticElement = getSemanticElement(semanticAdapter);
		if (ModelEditPart.MODEL_ID.equals(diagramKind)
				&& SysmlVisualIDRegistry.getDiagramVisualID(semanticElement) != -1) {
			return ModelViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated NOT
	 */
	protected Class getNodeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		if (containerView == null) {
			return null;
		}
		IElementType elementType = getSemanticElementType(semanticAdapter);
		if (elementType != null
				&& !SysmlElementTypes.isKnownElementType(elementType)) {
			return null;
		}
		EClass semanticType = getSemanticEClass(semanticAdapter);
		EObject semanticElement = getSemanticElement(semanticAdapter);
		int nodeVID = SysmlVisualIDRegistry.getNodeVisualID(containerView,
				semanticElement, semanticType, semanticHint);
		switch (nodeVID) {
		case ModelicaClassEditPart.VISUAL_ID:
			return ModelicaClassViewFactory.class;
		case ModelicaTypeNameEditPart.VISUAL_ID:
			return ModelicaTypeNameViewFactory.class;
		case ModelicaTypeEditPart.VISUAL_ID:
			return ModelicaTypeViewFactory.class;
		case ModelicaClassName2EditPart.VISUAL_ID:
			return ModelicaClassName2ViewFactory.class;
		case ModelicaPropertyEditPart.VISUAL_ID:
			return ModelicaPropertyViewFactory.class;
		case ModelicaProperty2EditPart.VISUAL_ID:
			return ModelicaProperty2ViewFactory.class;
		case ModelicaProperty3EditPart.VISUAL_ID:
			return ModelicaProperty3ViewFactory.class;
		case ModelicaProperty4EditPart.VISUAL_ID:
			return ModelicaProperty4ViewFactory.class;
		case ModelicaPropertyNameEditPart.VISUAL_ID:
			return ModelicaPropertyNameViewFactory.class;
		case ModelicaClass2EditPart.VISUAL_ID:
			return ModelicaClass2ViewFactory.class;
		case ModelicaClassNameEditPart.VISUAL_ID:
			return ModelicaClassNameViewFactory.class;
		case ModelicaEquationPropertyEditPart.VISUAL_ID:
			return ModelicaEquationPropertyViewFactory.class;
		case ModelicaClassParametersEditPart.VISUAL_ID:
			return ModelicaClassParametersViewFactory.class;
		case ModelicaClassPartsEditPart.VISUAL_ID:
			return ModelicaClassPartsViewFactory.class;
		case ModelicaClassVariablesEditPart.VISUAL_ID:
			return ModelicaClassVariablesViewFactory.class;
		case ModelicaClassConnectionsEditPart.VISUAL_ID:
			return ModelicaClassConnectionsViewFactory.class;
		case ModelicaClassNestedEditPart.VISUAL_ID:
			return ModelicaClassNestedViewFactory.class;
		case ModelicaClassEquationsEditPart.VISUAL_ID:
			return ModelicaClassEquationsViewFactory.class;
		case ModelicaClassParameters2EditPart.VISUAL_ID:
			return ModelicaClassParameters2ViewFactory.class;
		case ModelicaClassParts2EditPart.VISUAL_ID:
			return ModelicaClassParts2ViewFactory.class;
		case ModelicaClassVariables2EditPart.VISUAL_ID:
			return ModelicaClassVariables2ViewFactory.class;
		case ModelicaClassConnections2EditPart.VISUAL_ID:
			return ModelicaClassConnections2ViewFactory.class;
		case ModelicaClassNested2EditPart.VISUAL_ID:
			return ModelicaClassNested2ViewFactory.class;
		case ModelicaClassEquations2EditPart.VISUAL_ID:
			return ModelicaClassEquations2ViewFactory.class;
		case ModelicaCompositionNameEditPart.VISUAL_ID:
			return ModelicaCompositionNameViewFactory.class;
			// contributed code begin
		case ModelicaPortEditPart.VISUAL_ID:
			return ModelicaPortViewFactory.class;
		case ModelicaPortNameEditPart.VISUAL_ID:
			return ModelicaPortNameViewFactory.class;
			// contributed code end
		}
		return null;
	}

	/**
	 * @generated NOT
	 */
	protected Class getEdgeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		IElementType elementType = getSemanticElementType(semanticAdapter);
		if (elementType != null
				&& !SysmlElementTypes.isKnownElementType(elementType)) {
			return null;
		}
		EClass semanticType = getSemanticEClass(semanticAdapter);
		if (semanticType == null) {
			return null;
		}
		EObject semanticElement = getSemanticElement(semanticAdapter);
		int linkVID = SysmlVisualIDRegistry.getLinkWithClassVisualID(
				semanticElement, semanticType);
		switch (linkVID) {
		case GeneralizationEditPart.VISUAL_ID:
			return GeneralizationViewFactory.class;
		case ModelicaCompositionEditPart.VISUAL_ID:
			return ModelicaCompositionViewFactory.class;
		case ModelicaConnectionEditPart.VISUAL_ID:
			return ModelicaConnectionViewFactory.class;
		}
		return getUnrecognizedConnectorViewClass(semanticAdapter,
				containerView, semanticHint);
	}

	/**
	 * @generated
	 */
	private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
		if (semanticAdapter == null) {
			return null;
		}
		return (IElementType) semanticAdapter.getAdapter(IElementType.class);
	}

	/**
	 * @generated
	 */
	private Class getUnrecognizedConnectorViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		// Handle unrecognized child node classes here
		return null;
	}

}

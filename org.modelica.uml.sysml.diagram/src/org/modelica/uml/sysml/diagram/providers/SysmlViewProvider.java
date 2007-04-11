package org.modelica.uml.sysml.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.modelica.uml.sysml.diagram.edit.parts.EnumerationCompartmentEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.EnumerationEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.EnumerationLiteralEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.EnumerationNameEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.GeneralizationEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassNameEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassParameterEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassParametersEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassPartEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassPartsEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassVariablesEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassValuesEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaCompositionEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaCompositionNameEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaProperty2EditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaProperty3EditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaPropertyEditPart;

import org.modelica.uml.sysml.diagram.edit.parts.ModelicaPropertyNameEditPart;

import org.modelica.uml.sysml.diagram.edit.parts.StereotypeLabelEditPart;

import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassVariableEditPart;

import org.modelica.uml.sysml.diagram.part.SysmlVisualIDRegistry;

import org.modelica.uml.sysml.diagram.view.factories.AssociationNameViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.EnumerationCompartmentViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.EnumerationLiteralViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.EnumerationNameViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.EnumerationViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.GeneralizationViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaClassNameViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaClassParameterViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaClassParametersViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaClassPartViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaClassPartsViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaClassVariablesViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaClassValuesViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaClassVariableViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaClassViewFactory;

import org.modelica.uml.sysml.diagram.view.factories.ModelicaCompositionNameViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaCompositionViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaProperty2ViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaProperty3ViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaPropertyNameViewFactory;
import org.modelica.uml.sysml.diagram.view.factories.ModelicaPropertyViewFactory;

import org.modelica.uml.sysml.diagram.view.factories.StereotypeLabelViewFactory;

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
	 * @generated
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
		case ModelicaClassNameEditPart.VISUAL_ID:
			return ModelicaClassNameViewFactory.class;
		case EnumerationEditPart.VISUAL_ID:
			return EnumerationViewFactory.class;
		case EnumerationNameEditPart.VISUAL_ID:
			return EnumerationNameViewFactory.class;
		case ModelicaPropertyEditPart.VISUAL_ID:
			return ModelicaPropertyViewFactory.class;
		case ModelicaProperty2EditPart.VISUAL_ID:
			return ModelicaProperty2ViewFactory.class;
		case ModelicaProperty3EditPart.VISUAL_ID:
			return ModelicaProperty3ViewFactory.class;
		case EnumerationLiteralEditPart.VISUAL_ID:
			return EnumerationLiteralViewFactory.class;
		case ModelicaClassParametersEditPart.VISUAL_ID:
			return ModelicaClassParametersViewFactory.class;
		case ModelicaClassPartsEditPart.VISUAL_ID:
			return ModelicaClassPartsViewFactory.class;
		case ModelicaClassValuesEditPart.VISUAL_ID:
			return ModelicaClassValuesViewFactory.class;
		case EnumerationCompartmentEditPart.VISUAL_ID:
			return EnumerationCompartmentViewFactory.class;
		case ModelicaCompositionNameEditPart.VISUAL_ID:
			return ModelicaCompositionNameViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
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

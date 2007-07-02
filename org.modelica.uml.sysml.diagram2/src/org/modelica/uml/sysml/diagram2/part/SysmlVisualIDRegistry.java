package org.modelica.uml.sysml.diagram2.part;

import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.ModelicaBlock;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.ModelicaEquationProperty;
import org.modelica.uml.sysml.ModelicaConnector;
import org.modelica.uml.sysml.ModelicaFunction;
import org.modelica.uml.sysml.ModelicaModel;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.ModelicaType;
import org.modelica.uml.sysml.ModelicaRecord;
import org.modelica.uml.sysml.SysmlPackage;

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

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented by
 * a domain model object.
 * 
 * @generated
 */
public class SysmlVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = SysmlDiagramEditorPlugin
			.getInstance().getBundle().getSymbolicName()
			+ "/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (ModelEditPart.MODEL_ID.equals(view.getType())) {
				return ModelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				SysmlDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		EClass domainElementMetaclass = domainElement.eClass();
		return getDiagramVisualID(domainElement, domainElementMetaclass);
	}

	/**
	 * @generated
	 */
	private static int getDiagramVisualID(EObject domainElement,
			EClass domainElementMetaclass) {
		if (UMLPackage.eINSTANCE.getModel().isSuperTypeOf(
				domainElementMetaclass)
				&& isDiagramModel_79((Model) domainElement)) {
			return ModelEditPart.VISUAL_ID;
		}
		return getUnrecognizedDiagramID(domainElement);
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		EClass domainElementMetaclass = domainElement.eClass();
		return getNodeVisualID(containerView, domainElement,
				domainElementMetaclass, null);
	}

	/**
	 * @generated NOT
	 */
	public static int getNodeVisualID(View containerView,
			EObject domainElement, EClass domainElementMetaclass,
			String semanticHint) {
		String containerModelID = getModelID(containerView);
		if (!ModelEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (ModelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = ModelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		int nodeVisualID = semanticHint != null ? getVisualID(semanticHint)
				: -1;
		switch (containerVisualID) {
		case ModelicaClassEditPart.VISUAL_ID:
			if (ModelicaClassName2EditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassName2EditPart.VISUAL_ID;
			}
			if (ModelicaClassParametersEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassParametersEditPart.VISUAL_ID;
			}
			if (ModelicaClassPartsEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassPartsEditPart.VISUAL_ID;
			}
			if (ModelicaClassVariablesEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassVariablesEditPart.VISUAL_ID;
			}
			if (ModelicaClassConnectionsEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassConnectionsEditPart.VISUAL_ID;
			}
			if (ModelicaClassNestedEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassNestedEditPart.VISUAL_ID;
			}
			if (ModelicaClassEquationsEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassEquationsEditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClass_1001ChildNodeID(domainElement,
					semanticHint);
		case ModelicaPropertyEditPart.VISUAL_ID:
			return getUnrecognizedModelicaProperty_2001ChildNodeID(
					domainElement, semanticHint);
		case ModelicaProperty2EditPart.VISUAL_ID:
			return getUnrecognizedModelicaProperty_2002ChildNodeID(
					domainElement, semanticHint);
		case ModelicaProperty3EditPart.VISUAL_ID:
			return getUnrecognizedModelicaProperty_2003ChildNodeID(
					domainElement, semanticHint);
		case ModelicaProperty4EditPart.VISUAL_ID:
			if (ModelicaPropertyNameEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaPropertyNameEditPart.VISUAL_ID;
			}

			// contributed source begin
			// if (ModelicaPortEditPart.VISUAL_ID == nodeVisualID) {
			return ModelicaPortEditPart.VISUAL_ID;
			// }
			// contributed source end

			// return getUnrecognizedModelicaProperty_2004ChildNodeID(
			// domainElement, semanticHint);
		case ModelicaClass2EditPart.VISUAL_ID:
			if (ModelicaClassNameEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassNameEditPart.VISUAL_ID;
			}
			if (ModelicaClassParameters2EditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassParameters2EditPart.VISUAL_ID;
			}
			if (ModelicaClassParts2EditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassParts2EditPart.VISUAL_ID;
			}
			if (ModelicaClassVariables2EditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassVariables2EditPart.VISUAL_ID;
			}
			if (ModelicaClassConnections2EditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassConnections2EditPart.VISUAL_ID;
			}
			if (ModelicaClassNested2EditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassNested2EditPart.VISUAL_ID;
			}
			if (ModelicaClassEquations2EditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassEquations2EditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClass_2005ChildNodeID(domainElement,
					semanticHint);
		case ModelicaTypeEditPart.VISUAL_ID:
			if (ModelicaTypeNameEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaTypeNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaType_1002ChildNodeID(domainElement,
					semanticHint);
		case ModelicaEquationPropertyEditPart.VISUAL_ID:
			return getUnrecognizedModelicaEquationProperty_2006ChildNodeID(
					domainElement, semanticHint);
		case ModelicaClassParametersEditPart.VISUAL_ID:
			if ((semanticHint == null || ModelicaPropertyEditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaProperty()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaProperty_2001((ModelicaProperty) domainElement))) {
				return ModelicaPropertyEditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClassParameters_5001ChildNodeID(
					domainElement, semanticHint);
		case ModelicaClassPartsEditPart.VISUAL_ID:
			if ((semanticHint == null || ModelicaProperty2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaProperty()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaProperty_2002((ModelicaProperty) domainElement))) {
				return ModelicaProperty2EditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClassParts_5002ChildNodeID(
					domainElement, semanticHint);
		case ModelicaClassVariablesEditPart.VISUAL_ID:
			if ((semanticHint == null || ModelicaProperty3EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaProperty()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaProperty_2003((ModelicaProperty) domainElement))) {
				return ModelicaProperty3EditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClassVariables_5003ChildNodeID(
					domainElement, semanticHint);
		case ModelicaClassConnectionsEditPart.VISUAL_ID:
			if ((semanticHint == null || ModelicaProperty4EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaProperty()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaProperty_2004((ModelicaProperty) domainElement))) {
				return ModelicaProperty4EditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClassConnections_5004ChildNodeID(
					domainElement, semanticHint);
		case ModelicaClassNestedEditPart.VISUAL_ID:
			// contributed code begin
			if ((semanticHint == null || ModelicaClass2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaModel().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaModel_2005((ModelicaModel) domainElement))) {
				return ModelicaClass2EditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ModelicaClass2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaBlock().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaBlock_2005((ModelicaBlock) domainElement))) {
				return ModelicaClass2EditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ModelicaClass2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaRecord()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaRecord_2005((ModelicaRecord) domainElement))) {
				return ModelicaClass2EditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ModelicaClass2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaConnector()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaConnector_2005((ModelicaConnector) domainElement))) {
				return ModelicaClass2EditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ModelicaClass2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaFunction()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaFunction_2005((ModelicaFunction) domainElement))) {
				return ModelicaClass2EditPart.VISUAL_ID;
			}
			// contributed code end

			if ((semanticHint == null || ModelicaClass2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaClass().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaClass_2005((ModelicaClass) domainElement))) {
				return ModelicaClass2EditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClassNested_5005ChildNodeID(
					domainElement, semanticHint);
		case ModelicaClassEquationsEditPart.VISUAL_ID:
			if ((semanticHint == null || ModelicaEquationPropertyEditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaEquationProperty()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaEquationProperty_2006((ModelicaEquationProperty) domainElement))) {
				return ModelicaEquationPropertyEditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClassEquations_5006ChildNodeID(
					domainElement, semanticHint);
		case ModelicaClassParameters2EditPart.VISUAL_ID:
			if ((semanticHint == null || ModelicaPropertyEditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaProperty()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaProperty_2001((ModelicaProperty) domainElement))) {
				return ModelicaPropertyEditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClassParameters_5007ChildNodeID(
					domainElement, semanticHint);
		case ModelicaClassParts2EditPart.VISUAL_ID:
			if ((semanticHint == null || ModelicaProperty2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaProperty()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaProperty_2002((ModelicaProperty) domainElement))) {
				return ModelicaProperty2EditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClassParts_5008ChildNodeID(
					domainElement, semanticHint);
		case ModelicaClassVariables2EditPart.VISUAL_ID:
			if ((semanticHint == null || ModelicaProperty3EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaProperty()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaProperty_2003((ModelicaProperty) domainElement))) {
				return ModelicaProperty3EditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClassVariables_5009ChildNodeID(
					domainElement, semanticHint);
		case ModelicaClassConnections2EditPart.VISUAL_ID:
			if ((semanticHint == null || ModelicaProperty4EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaProperty()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaProperty_2004((ModelicaProperty) domainElement))) {
				return ModelicaProperty4EditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClassConnections_5010ChildNodeID(
					domainElement, semanticHint);
		case ModelicaClassNested2EditPart.VISUAL_ID:
			// contributed code begin
			if ((semanticHint == null || ModelicaClass2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaModel().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaModel_2005((ModelicaModel) domainElement))) {
				return ModelicaClass2EditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ModelicaClass2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaBlock().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaBlock_2005((ModelicaBlock) domainElement))) {
				return ModelicaClass2EditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ModelicaClass2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaRecord()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaRecord_2005((ModelicaRecord) domainElement))) {
				return ModelicaClass2EditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ModelicaClass2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaConnector()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaConnector_2005((ModelicaConnector) domainElement))) {
				return ModelicaClass2EditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ModelicaClass2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaFunction()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaFunction_2005((ModelicaFunction) domainElement))) {
				return ModelicaClass2EditPart.VISUAL_ID;
			}
			// contributed code end

			if ((semanticHint == null || ModelicaClass2EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaClass().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaClass_2005((ModelicaClass) domainElement))) {
				return ModelicaClass2EditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClassNested_5011ChildNodeID(
					domainElement, semanticHint);
		case ModelicaClassEquations2EditPart.VISUAL_ID:
			if ((semanticHint == null || ModelicaEquationPropertyEditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaEquationProperty()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaEquationProperty_2006((ModelicaEquationProperty) domainElement))) {
				return ModelicaEquationPropertyEditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClassEquations_5012ChildNodeID(
					domainElement, semanticHint);
		case ModelEditPart.VISUAL_ID:
			// contributed code begin
			if ((semanticHint == null || ModelicaClassEditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaModel().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaModel_1001((ModelicaModel) domainElement))) {
				return ModelicaClassEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ModelicaClassEditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaBlock().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaBlock_1001((ModelicaBlock) domainElement))) {
				return ModelicaClassEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ModelicaClassEditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaRecord()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaRecord_1001((ModelicaRecord) domainElement))) {
				return ModelicaClassEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ModelicaClassEditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaConnector()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaConnector_1001((ModelicaConnector) domainElement))) {
				return ModelicaClassEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ModelicaClassEditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaFunction()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaFunction_1001((ModelicaFunction) domainElement))) {
				return ModelicaClassEditPart.VISUAL_ID;
			}
			// contributed code end

			if ((semanticHint == null || ModelicaClassEditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaClass().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaClass_1001((ModelicaClass) domainElement))) {
				return ModelicaClassEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ModelicaTypeEditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaType().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaType_1002((ModelicaType) domainElement))) {
				return ModelicaTypeEditPart.VISUAL_ID;
			}
			return getUnrecognizedModel_79ChildNodeID(domainElement,
					semanticHint);
		case ModelicaCompositionEditPart.VISUAL_ID:
			if (ModelicaCompositionNameEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaCompositionNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaComposition_3002LinkLabelID(semanticHint);
			/*
			 * // contributed code begin case ModelicaPortEditPart.VISUAL_ID: if
			 * (ModelicaPortNameEditPart.VISUAL_ID == nodeVisualID) { return
			 * ModelicaPortNameEditPart.VISUAL_ID; } // contributed code end
			 */
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		EClass domainElementMetaclass = domainElement.eClass();
		return getLinkWithClassVisualID(domainElement, domainElementMetaclass);
	}

	/**
	 * @generated NOT
	 */
	public static int getLinkWithClassVisualID(EObject domainElement,
			EClass domainElementMetaclass) {
		if (UMLPackage.eINSTANCE.getGeneralization().isSuperTypeOf(
				domainElementMetaclass)
				&& (domainElement == null || isLinkWithClassGeneralization_3001((Generalization) domainElement))) {
			return GeneralizationEditPart.VISUAL_ID;
		} else if (SysmlPackage.eINSTANCE.getModelicaComposition()
				.isSuperTypeOf(domainElementMetaclass)
				&& (domainElement == null || isLinkWithClassModelicaComposition_3002((ModelicaComposition) domainElement))) {
			return ModelicaCompositionEditPart.VISUAL_ID;
		} else if (UMLPackage.eINSTANCE.getConnector().isSuperTypeOf(
				domainElementMetaclass)) {
			return ModelicaConnectionEditPart.VISUAL_ID;
		} else {
			return getUnrecognizedLinkWithClassID(domainElement);
		}
	}

	/**
	 * User can change implementation of this method to check some additional
	 * conditions here.
	 * 
	 * @generated
	 */
	private static boolean isDiagramModel_79(Model element) {
		return true;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedDiagramID(EObject domainElement) {
		return -1;
	}

	/**
	 * User can change implementation of this method to check some additional
	 * conditions here.
	 * 
	 * @generated
	 */
	private static boolean isNodeModelicaClass_1001(ModelicaClass element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeModelicaType_1002(ModelicaType element) {
		return true;
	}

	private static boolean isNodeModelicaModel_1001(ModelicaModel element) {
		return true;
	}

	private static boolean isNodeModelicaBlock_1001(ModelicaBlock element) {
		return true;
	}

	private static boolean isNodeModelicaRecord_1001(ModelicaRecord element) {
		return true;
	}

	private static boolean isNodeModelicaConnector_1001(
			ModelicaConnector element) {
		return true;
	}

	private static boolean isNodeModelicaFunction_1001(ModelicaFunction element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional
	 * conditions here.
	 * 
	 * @generated
	 */
	private static boolean isNodeModelicaProperty_2001(ModelicaProperty element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional
	 * conditions here.
	 * 
	 * @generated
	 */
	private static boolean isNodeModelicaProperty_2002(ModelicaProperty element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional
	 * conditions here.
	 * 
	 * @generated
	 */
	private static boolean isNodeModelicaProperty_2003(ModelicaProperty element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional
	 * conditions here.
	 * 
	 * @generated
	 */
	private static boolean isNodeModelicaProperty_2004(ModelicaProperty element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional
	 * conditions here.
	 * 
	 * @generated
	 */
	private static boolean isNodeModelicaClass_2005(ModelicaClass element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional
	 * conditions here.
	 * 
	 * @generated
	 */
	private static boolean isNodeModelicaEquationProperty_2006(
			ModelicaEquationProperty element) {
		return true;
	}

	private static boolean isNodeModelicaModel_2005(ModelicaModel element) {
		return true;
	}

	private static boolean isNodeModelicaBlock_2005(ModelicaBlock element) {
		return true;
	}

	private static boolean isNodeModelicaRecord_2005(ModelicaRecord element) {
		return true;
	}

	private static boolean isNodeModelicaConnector_2005(
			ModelicaConnector element) {
		return true;
	}

	private static boolean isNodeModelicaFunction_2005(ModelicaFunction element) {
		return true;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClass_1001ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedModelicaType_1002ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaProperty_2001ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaProperty_2002ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaProperty_2003ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaProperty_2004ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClass_2005ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaEquationProperty_2006ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClassParameters_5001ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClassParts_5002ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClassVariables_5003ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClassConnections_5004ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClassNested_5005ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClassEquations_5006ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClassParameters_5007ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClassParts_5008ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClassVariables_5009ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClassConnections_5010ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClassNested_5011ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaClassEquations_5012ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModel_79ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedModelicaComposition_3002LinkLabelID(
			String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static int getUnrecognizedLinkWithClassID(EObject domainElement) {
		return -1;
	}

	/**
	 * User can change implementation of this method to check some additional
	 * conditions here.
	 * 
	 * @generated
	 */
	private static boolean isLinkWithClassGeneralization_3001(
			Generalization element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional
	 * conditions here.
	 * 
	 * @generated
	 */
	private static boolean isLinkWithClassModelicaComposition_3002(
			ModelicaComposition element) {
		return true;
	}
}

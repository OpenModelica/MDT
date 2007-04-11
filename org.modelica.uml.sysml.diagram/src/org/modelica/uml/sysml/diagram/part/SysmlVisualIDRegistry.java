package org.modelica.uml.sysml.diagram.part;

import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.ModelicaBlock;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.ModelicaEquationProperty;
import org.modelica.uml.sysml.ModelicaConnector;
import org.modelica.uml.sysml.ModelicaFunction;
import org.modelica.uml.sysml.ModelicaModel;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.ModelicaRecord;
import org.modelica.uml.sysml.SysmlPackage;

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

import org.modelica.uml.sysml.diagram.expressions.SysmlAbstractExpression;
import org.modelica.uml.sysml.diagram.expressions.SysmlOCLFactory;

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
			if (ModelicaClassNameEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassNameEditPart.VISUAL_ID;
			}
			if (ModelicaClassParametersEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassParametersEditPart.VISUAL_ID;
			}
			if (ModelicaClassPartsEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassPartsEditPart.VISUAL_ID;
			}
			if (ModelicaClassValuesEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaClassValuesEditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClass_1001ChildNodeID(domainElement,
					semanticHint);
		case EnumerationEditPart.VISUAL_ID:
			if (EnumerationNameEditPart.VISUAL_ID == nodeVisualID) {
				return EnumerationNameEditPart.VISUAL_ID;
			}
			if (EnumerationCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return EnumerationCompartmentEditPart.VISUAL_ID;
			}
			return getUnrecognizedEnumeration_1002ChildNodeID(domainElement,
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
		case EnumerationLiteralEditPart.VISUAL_ID:
			return getUnrecognizedEnumerationLiteral_2004ChildNodeID(
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
		case ModelicaClassValuesEditPart.VISUAL_ID:
			if ((semanticHint == null || ModelicaProperty3EditPart.VISUAL_ID == nodeVisualID)
					&& SysmlPackage.eINSTANCE.getModelicaProperty()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeModelicaProperty_2003((ModelicaProperty) domainElement))) {
				return ModelicaProperty3EditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaClassValues_5003ChildNodeID(
					domainElement, semanticHint);
		case EnumerationCompartmentEditPart.VISUAL_ID:
			if ((semanticHint == null || EnumerationLiteralEditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getEnumerationLiteral()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeEnumerationLiteral_2004((EnumerationLiteral) domainElement))) {
				return EnumerationLiteralEditPart.VISUAL_ID;
			}
			return getUnrecognizedEnumerationCompartment_5004ChildNodeID(
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

			if ((semanticHint == null || EnumerationEditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getEnumeration().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeEnumeration_1002((Enumeration) domainElement))) {
				return EnumerationEditPart.VISUAL_ID;
			}
			return getUnrecognizedModel_79ChildNodeID(domainElement,
					semanticHint);
		case ModelicaCompositionEditPart.VISUAL_ID:
			if (ModelicaCompositionNameEditPart.VISUAL_ID == nodeVisualID) {
				return ModelicaCompositionNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedModelicaComposition_3002LinkLabelID(semanticHint);
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		EClass domainElementMetaclass = domainElement.eClass();
		return getLinkWithClassVisualID(domainElement, domainElementMetaclass);
	}

	/**
	 * @generated
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
		return ModelicaClass_1001.matches(element);
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeEnumeration_1002(Enumeration element) {
		return true;
	}

	private static boolean isNodeModelicaModel_1001(ModelicaModel element) {
		return ModelicaModel_1001.matches(element);
	}

	private static boolean isNodeModelicaBlock_1001(ModelicaBlock element) {
		return ModelicaBlock_1001.matches(element);
	}

	private static boolean isNodeModelicaRecord_1001(ModelicaRecord element) {
		return ModelicaRecord_1001.matches(element);
	}

	private static boolean isNodeModelicaConnector_1001(
			ModelicaConnector element) {
		return ModelicaConnector_1001.matches(element);
	}

	private static boolean isNodeModelicaFunction_1001(ModelicaFunction element) {
		return ModelicaFunction_1001.matches(element);
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
	private static boolean isNodeEnumerationLiteral_2004(
			EnumerationLiteral element) {
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
	private static int getUnrecognizedEnumeration_1002ChildNodeID(
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
	private static int getUnrecognizedEnumerationLiteral_2004ChildNodeID(
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
	private static int getUnrecognizedModelicaClassValues_5003ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedEnumerationCompartment_5004ChildNodeID(
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

	/**
	 * @generated
	 */
	private static final Matcher ModelicaClass_1001 = new Matcher(
			SysmlOCLFactory.getExpression(
					"not oclIsKindOf(uml::AssociationClass)", //$NON-NLS-1$
					SysmlPackage.eINSTANCE.getModelicaClass()));

	/**
	 * @generated	
	 */
	static class Matcher {

		/**
		 * @generated	
		 */
		private SysmlAbstractExpression condition;

		/**
		 * @generated	
		 */
		Matcher(SysmlAbstractExpression conditionExpression) {
			this.condition = conditionExpression;
		}

		/**
		 * @generated	
		 */
		boolean matches(EObject object) {
			Object result = condition.evaluate(object);
			return result instanceof Boolean
					&& ((Boolean) result).booleanValue();
		}
	}// Matcher

	private static final Matcher ModelicaModel_1001 = new Matcher(
			SysmlOCLFactory.getExpression(
					"not oclIsKindOf(uml::AssociationClass)", //$NON-NLS-1$
					SysmlPackage.eINSTANCE.getModelicaModel()));

	private static final Matcher ModelicaBlock_1001 = new Matcher(
			SysmlOCLFactory.getExpression(
					"not oclIsKindOf(uml::AssociationClass)", //$NON-NLS-1$
					SysmlPackage.eINSTANCE.getModelicaBlock()));

	private static final Matcher ModelicaRecord_1001 = new Matcher(
			SysmlOCLFactory.getExpression(
					"not oclIsKindOf(uml::AssociationClass)", //$NON-NLS-1$
					SysmlPackage.eINSTANCE.getModelicaRecord()));

	private static final Matcher ModelicaConnector_1001 = new Matcher(
			SysmlOCLFactory.getExpression(
					"not oclIsKindOf(uml::AssociationClass)", //$NON-NLS-1$
					SysmlPackage.eINSTANCE.getModelicaConnector()));

	private static final Matcher ModelicaFunction_1001 = new Matcher(
			SysmlOCLFactory.getExpression(
					"not oclIsKindOf(uml::AssociationClass)", //$NON-NLS-1$
					SysmlPackage.eINSTANCE.getModelicaFunction()));

}

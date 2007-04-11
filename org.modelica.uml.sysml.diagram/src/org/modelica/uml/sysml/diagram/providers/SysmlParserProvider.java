package org.modelica.uml.sysml.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.SysmlPackage;

import org.modelica.uml.sysml.diagram.edit.parts.EnumerationLiteralEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.EnumerationNameEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassNameEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaCompositionNameEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaProperty2EditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaProperty3EditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaPropertyNameEditPart;

import org.modelica.uml.sysml.diagram.edit.parts.ModelicaPropertyEditPart;

import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassParameterEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassPartEditPart;
import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassVariableEditPart;

import org.modelica.uml.sysml.diagram.part.SysmlVisualIDRegistry;

/**
 * @generated
 */
public class SysmlParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser modelicaPropertyModelicaProperty_2001Parser;

	/**
	 * @generated
	 */
	private IParser getModelicaPropertyModelicaProperty_2001Parser() {
		if (modelicaPropertyModelicaProperty_2001Parser == null) {
			modelicaPropertyModelicaProperty_2001Parser = createModelicaPropertyModelicaProperty_2001Parser();
		}
		return modelicaPropertyModelicaProperty_2001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createModelicaPropertyModelicaProperty_2001Parser() {
		SysmlStructuralFeatureParser parser = new SysmlStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement().getEStructuralFeature(
						"name")); //$NON-NLS-1$
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser modelicaPropertyModelicaProperty_2002Parser;

	/**
	 * @generated
	 */
	private IParser getModelicaPropertyModelicaProperty_2002Parser() {
		if (modelicaPropertyModelicaProperty_2002Parser == null) {
			modelicaPropertyModelicaProperty_2002Parser = createModelicaPropertyModelicaProperty_2002Parser();
		}
		return modelicaPropertyModelicaProperty_2002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createModelicaPropertyModelicaProperty_2002Parser() {
		SysmlStructuralFeatureParser parser = new SysmlStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement().getEStructuralFeature(
						"name")); //$NON-NLS-1$
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser modelicaPropertyModelicaProperty_2003Parser;

	/**
	 * @generated
	 */
	private IParser getModelicaPropertyModelicaProperty_2003Parser() {
		if (modelicaPropertyModelicaProperty_2003Parser == null) {
			modelicaPropertyModelicaProperty_2003Parser = createModelicaPropertyModelicaProperty_2003Parser();
		}
		return modelicaPropertyModelicaProperty_2003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createModelicaPropertyModelicaProperty_2003Parser() {
		SysmlStructuralFeatureParser parser = new SysmlStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement().getEStructuralFeature(
						"name")); //$NON-NLS-1$
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser enumerationLiteralEnumerationLiteral_2004Parser;

	/**
	 * @generated
	 */
	private IParser getEnumerationLiteralEnumerationLiteral_2004Parser() {
		if (enumerationLiteralEnumerationLiteral_2004Parser == null) {
			enumerationLiteralEnumerationLiteral_2004Parser = createEnumerationLiteralEnumerationLiteral_2004Parser();
		}
		return enumerationLiteralEnumerationLiteral_2004Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createEnumerationLiteralEnumerationLiteral_2004Parser() {
		SysmlStructuralFeatureParser parser = new SysmlStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement().getEStructuralFeature(
						"name")); //$NON-NLS-1$
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser modelicaClassModelicaClassName_4001Parser;

	/**
	 * @generated
	 */
	private IParser getModelicaClassModelicaClassName_4001Parser() {
		if (modelicaClassModelicaClassName_4001Parser == null) {
			modelicaClassModelicaClassName_4001Parser = createModelicaClassModelicaClassName_4001Parser();
		}
		return modelicaClassModelicaClassName_4001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createModelicaClassModelicaClassName_4001Parser() {
		SysmlStructuralFeatureParser parser = new SysmlStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement().getEStructuralFeature(
						"name")); //$NON-NLS-1$
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser enumerationEnumerationName_4002Parser;

	/**
	 * @generated
	 */
	private IParser getEnumerationEnumerationName_4002Parser() {
		if (enumerationEnumerationName_4002Parser == null) {
			enumerationEnumerationName_4002Parser = createEnumerationEnumerationName_4002Parser();
		}
		return enumerationEnumerationName_4002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createEnumerationEnumerationName_4002Parser() {
		SysmlStructuralFeatureParser parser = new SysmlStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement().getEStructuralFeature(
						"name")); //$NON-NLS-1$
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser modelicaCompositionModelicaCompositionName_4003Parser;

	/**
	 * @generated
	 */
	private IParser getModelicaCompositionModelicaCompositionName_4003Parser() {
		if (modelicaCompositionModelicaCompositionName_4003Parser == null) {
			modelicaCompositionModelicaCompositionName_4003Parser = createModelicaCompositionModelicaCompositionName_4003Parser();
		}
		return modelicaCompositionModelicaCompositionName_4003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createModelicaCompositionModelicaCompositionName_4003Parser() {
		SysmlStructuralFeatureParser parser = new SysmlStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement().getEStructuralFeature(
						"name")); //$NON-NLS-1$
		return parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ModelicaPropertyEditPart.VISUAL_ID:
			return getModelicaPropertyModelicaProperty_2001Parser();
		case ModelicaProperty2EditPart.VISUAL_ID:
			return getModelicaPropertyModelicaProperty_2002Parser();
		case ModelicaProperty3EditPart.VISUAL_ID:
			return getModelicaPropertyModelicaProperty_2003Parser();
		case EnumerationLiteralEditPart.VISUAL_ID:
			return getEnumerationLiteralEnumerationLiteral_2004Parser();
		case ModelicaClassNameEditPart.VISUAL_ID:
			return getModelicaClassModelicaClassName_4001Parser();
		case EnumerationNameEditPart.VISUAL_ID:
			return getEnumerationEnumerationName_4002Parser();
		case ModelicaCompositionNameEditPart.VISUAL_ID:
			return getModelicaCompositionModelicaCompositionName_4003Parser();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(SysmlVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(SysmlVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (SysmlElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}
}

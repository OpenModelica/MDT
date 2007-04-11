package org.modelica.uml.sysml.diagram2.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.SysmlPackage;

import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassName2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassNameEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaCompositionNameEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaEquationPropertyEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty3EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPropertyEditPart;

import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPropertyNameEditPart;

import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

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
	private IParser modelicaPropertyModelicaPropertyName_4001Parser;

	/**
	 * @generated
	 */
	private IParser getModelicaPropertyModelicaPropertyName_4001Parser() {
		if (modelicaPropertyModelicaPropertyName_4001Parser == null) {
			modelicaPropertyModelicaPropertyName_4001Parser = createModelicaPropertyModelicaPropertyName_4001Parser();
		}
		return modelicaPropertyModelicaPropertyName_4001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createModelicaPropertyModelicaPropertyName_4001Parser() {
		SysmlStructuralFeatureParser parser = new SysmlStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement().getEStructuralFeature(
						"name")); //$NON-NLS-1$
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser modelicaClassModelicaClassName_4002Parser;

	/**
	 * @generated
	 */
	private IParser getModelicaClassModelicaClassName_4002Parser() {
		if (modelicaClassModelicaClassName_4002Parser == null) {
			modelicaClassModelicaClassName_4002Parser = createModelicaClassModelicaClassName_4002Parser();
		}
		return modelicaClassModelicaClassName_4002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createModelicaClassModelicaClassName_4002Parser() {
		SysmlStructuralFeatureParser parser = new SysmlStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement().getEStructuralFeature(
						"name")); //$NON-NLS-1$
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser modelicaEquationPropertyModelicaEquationProperty_2006Parser;

	/**
	 * @generated
	 */
	private IParser getModelicaEquationPropertyModelicaEquationProperty_2006Parser() {
		if (modelicaEquationPropertyModelicaEquationProperty_2006Parser == null) {
			modelicaEquationPropertyModelicaEquationProperty_2006Parser = createModelicaEquationPropertyModelicaEquationProperty_2006Parser();
		}
		return modelicaEquationPropertyModelicaEquationProperty_2006Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createModelicaEquationPropertyModelicaEquationProperty_2006Parser() {
		SysmlStructuralFeatureParser parser = new SysmlStructuralFeatureParser(
				SysmlPackage.eINSTANCE.getModelicaEquationProperty()
						.getEStructuralFeature("equation")); //$NON-NLS-1$
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser modelicaClassModelicaClassName_4003Parser;

	/**
	 * @generated
	 */
	private IParser getModelicaClassModelicaClassName_4003Parser() {
		if (modelicaClassModelicaClassName_4003Parser == null) {
			modelicaClassModelicaClassName_4003Parser = createModelicaClassModelicaClassName_4003Parser();
		}
		return modelicaClassModelicaClassName_4003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createModelicaClassModelicaClassName_4003Parser() {
		SysmlStructuralFeatureParser parser = new SysmlStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement().getEStructuralFeature(
						"name")); //$NON-NLS-1$
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser modelicaCompositionModelicaCompositionName_4004Parser;

	/**
	 * @generated
	 */
	private IParser getModelicaCompositionModelicaCompositionName_4004Parser() {
		if (modelicaCompositionModelicaCompositionName_4004Parser == null) {
			modelicaCompositionModelicaCompositionName_4004Parser = createModelicaCompositionModelicaCompositionName_4004Parser();
		}
		return modelicaCompositionModelicaCompositionName_4004Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createModelicaCompositionModelicaCompositionName_4004Parser() {
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
		case ModelicaPropertyNameEditPart.VISUAL_ID:
			return getModelicaPropertyModelicaPropertyName_4001Parser();
		case ModelicaClassNameEditPart.VISUAL_ID:
			return getModelicaClassModelicaClassName_4002Parser();
		case ModelicaEquationPropertyEditPart.VISUAL_ID:
			return getModelicaEquationPropertyModelicaEquationProperty_2006Parser();
		case ModelicaClassName2EditPart.VISUAL_ID:
			return getModelicaClassModelicaClassName_4003Parser();
		case ModelicaCompositionNameEditPart.VISUAL_ID:
			return getModelicaCompositionModelicaCompositionName_4004Parser();
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

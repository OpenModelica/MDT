package org.modelica.uml.sysml.diagram2.edit.policies;

import java.util.Collection;
import java.util.HashSet;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.util.Set;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.StructuredClassifier;

import org.eclipse.uml2.uml.UMLPackage;
import org.modelica.uml.sysml.ModelicaEquationProperty;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaEquationPropertyEditPart;

import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPropertyEditPart;
import org.modelica.uml.sysml.diagram2.part.SysmlDiagramUpdater;
import org.modelica.uml.sysml.diagram2.part.SysmlNodeDescriptor;
import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

/**
 * @generated
 */
public class ModelicaClassEquations2CanonicalEditPolicy extends
		CanonicalEditPolicy {

	/**
	 * @generated
	 */
	Set myFeaturesToSynchronize;

	/**
	 * @generated
	 */
	protected List getSemanticChildrenList() {
		View viewObject = (View) getHost().getModel();
		List result = new LinkedList();
		for (Iterator it = SysmlDiagramUpdater
				.getModelicaClassEquations_5012SemanticChildren(viewObject)
				.iterator(); it.hasNext();) {
			result.add(((SysmlNodeDescriptor) it.next()).getModelElement());
		}
		return result;
	}

	/**
	 * @generated
	 */
	protected boolean isOrphaned(Collection semanticChildren, final View view) {
		int visualID = SysmlVisualIDRegistry.getVisualID(view);
		switch (visualID) {
		case ModelicaEquationPropertyEditPart.VISUAL_ID:
			return !semanticChildren.contains(view.getElement())
					|| visualID != SysmlVisualIDRegistry.getNodeVisualID(
							(View) getHost().getModel(), view.getElement());
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected String getDefaultFactoryHint() {
		return null;
	}

	/**
	 * @generated
	 */
	protected Set getFeaturesToSynchronize() {
		if (myFeaturesToSynchronize == null) {
			myFeaturesToSynchronize = new HashSet();
			myFeaturesToSynchronize.add(UMLPackage.eINSTANCE
					.getStructuredClassifier_OwnedAttribute());
		}
		return myFeaturesToSynchronize;
	}

}

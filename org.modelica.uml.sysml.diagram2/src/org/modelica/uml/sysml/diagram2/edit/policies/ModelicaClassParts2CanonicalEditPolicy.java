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
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.PropertyKind;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClass2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty2EditPart;

import org.modelica.uml.sysml.diagram2.part.SysmlDiagramUpdater;
import org.modelica.uml.sysml.diagram2.part.SysmlNodeDescriptor;
import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

/**
 * @generated
 */
public class ModelicaClassParts2CanonicalEditPolicy extends CanonicalEditPolicy {

	/**
	 * @generated
	 */
	Set myFeaturesToSynchronize;

	/**
	 * @generated NOT
	 */
	protected List getSemanticChildrenList() {
		List result = new LinkedList();
		EObject modelObject = ((View) getHost().getModel()).getElement();
		View viewObject = (View) getHost().getModel();
		EObject nextValue;
		int nodeVID;
		for (Iterator values = ((StructuredClassifier) modelObject)
				.getOwnedAttributes().iterator(); values.hasNext();) {
			nextValue = (EObject) values.next();

			if (nextValue instanceof ModelicaProperty) {
				if (((ModelicaProperty) nextValue).getPropertyKind().equals(
						PropertyKind.PART_LITERAL)) {

					nodeVID = SysmlVisualIDRegistry.getNodeVisualID(viewObject,
							nextValue);
					if (ModelicaProperty2EditPart.VISUAL_ID == nodeVID) {
						result.add(nextValue);
					}
				}
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	protected boolean isOrphaned(Collection semanticChildren, final View view) {
		int visualID = SysmlVisualIDRegistry.getVisualID(view);
		switch (visualID) {
		case ModelicaProperty2EditPart.VISUAL_ID:
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

package org.modelica.uml.sysml.diagram.edit.policies;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.StructuredClassifier;

import org.modelica.uml.sysml.diagram.edit.parts.ModelicaProperty2EditPart;

import org.modelica.uml.sysml.diagram.edit.parts.ModelicaClassPartEditPart;

import org.modelica.uml.sysml.diagram.part.SysmlVisualIDRegistry;

/**
 * @generated
 */
public class ModelicaClassPartsCanonicalEditPolicy extends CanonicalEditPolicy {

	/**
	 * @generated
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
			nodeVID = SysmlVisualIDRegistry.getNodeVisualID(viewObject,
					nextValue);
			if (ModelicaProperty2EditPart.VISUAL_ID == nodeVID) {
				result.add(nextValue);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	protected boolean shouldDeleteView(View view) {
		return view.isSetElement() && view.getElement() != null
				&& view.getElement().eIsProxy();
	}

	/**
	 * @generated
	 */
	protected String getDefaultFactoryHint() {
		return null;
	}

}

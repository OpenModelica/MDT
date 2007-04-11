package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.StructuredClassifier;

import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.PropertyKind;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPropertyEditPart;

import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

/**
 * @generated
 */
public class ModelicaClassParameters2CanonicalEditPolicy extends
		CanonicalEditPolicy {

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
						PropertyKind.PARAMETER_LITERAL)) {
					nodeVID = SysmlVisualIDRegistry.getNodeVisualID(viewObject,
							nextValue);
					if (ModelicaPropertyEditPart.VISUAL_ID == nodeVID) {
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

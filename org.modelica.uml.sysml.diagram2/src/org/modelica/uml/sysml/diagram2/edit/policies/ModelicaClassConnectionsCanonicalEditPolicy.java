package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalConnectionEditPolicy;
import org.eclipse.gmf.runtime.notation.View;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.StructuredClassifier;

import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.PropertyKind;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty4EditPart;

import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

/**
 * @generated NOT
 */
public class ModelicaClassConnectionsCanonicalEditPolicy extends
		CanonicalConnectionEditPolicy {

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
					if (ModelicaProperty4EditPart.VISUAL_ID == nodeVID) {
						result.add(nextValue);
					}
				}
			}
		}
		
		return result;
	}

	protected List getSemanticConnectionsList() {
		EObject modelRef = resolveSemanticElement();

		ModelicaClass modelicaClass = (ModelicaClass) modelRef;
		if (modelicaClass == null)
			return Collections.EMPTY_LIST;

		List allChildren = modelicaClass.getOwnedConnectors();
		ListIterator li = allChildren.listIterator();
		UniqueEList connectors = new UniqueEList();
		while (li.hasNext()) {
			Object obj = li.next();
			if (obj instanceof Connector) {
				Connector connector = (Connector) obj;
				if (isConnectorPartOfClass(modelicaClass, connector))
					connectors.add(connector);
			}
		}

		return connectors;
	}

	protected EObject getSourceElement(EObject relationship) {
		Connector connector = (Connector) relationship;
		if (connector == null)
			return null;
		return ((ConnectorEnd) connector.getEnds().get(0)).getRole();
	}

	protected EObject getTargetElement(EObject relationship) {
		Connector connector = (Connector) relationship;
		if (connector == null)
			return null;
		return ((ConnectorEnd) connector.getEnds().get(1)).getRole();
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

	private boolean isConnectorPartOfClass(ModelicaClass modelicaClass,
			Connector connector) {

		if (EcoreUtil.isAncestor(modelicaClass, getSourceElement(connector))
				&& EcoreUtil.isAncestor(modelicaClass,
						getTargetElement(connector))) {
			return true;
		}
		return false;
	}

}

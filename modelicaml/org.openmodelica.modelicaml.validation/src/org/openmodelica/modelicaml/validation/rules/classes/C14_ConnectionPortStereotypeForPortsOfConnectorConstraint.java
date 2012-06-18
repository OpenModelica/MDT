/**
 * 
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Port;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C14:
 * 	Rule :	Any UML Connector with ModelicaML stereotype applied can only connect UML Ports 
 * 			that have the ModelicaML stereotype ConnectionPort applied. 
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C14_ConnectionPortStereotypeForPortsOfConnectorConstraint extends
AbstractModelConstraint {

	public C14_ConnectionPortStereotypeForPortsOfConnectorConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {

		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();

		// In Batch Mode
		if(eType == EMFEventType.NULL){
			if(eObj instanceof Connector){
				Connector connector = (Connector) eObj;

				if(Utility.isElementHaveModelicaMLStereotypeApplied(connector)){
					// List of ConnectorEnds for connector
					List<ConnectorEnd> connector_connectorEnds = connector.getEnds();

					for (ConnectorEnd connectorEnd : connector_connectorEnds) {

						if((connectorEnd.getRole() instanceof Port) && (((Port)connectorEnd.getRole()).getAppliedStereotype(Constants.stereotypeQName_ConnectionPort)) == null){
							return ctx.createFailureStatus(new Object[]{Constants.validationKeyWord_NOT_VALID + ": A ModelicaML connector '"
									+connector.getName()+"' can only connect to ports with <<ConnectionPort>> stereotype applied."});
						}
					}
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}

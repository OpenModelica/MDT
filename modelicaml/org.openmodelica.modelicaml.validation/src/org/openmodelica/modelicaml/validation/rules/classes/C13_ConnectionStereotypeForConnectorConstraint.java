/**
 * 
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Connector;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C13:
 * 	Rule :	Any UML Connector that is owned by a UML Class with the ModelicaML stereotype 
 * 			applied must have the stereotype Connection applied. 
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C13_ConnectionStereotypeForConnectorConstraint extends AbstractModelConstraint {

	public C13_ConnectionStereotypeForConnectorConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {

		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		// In Batch mode
		if(eType == EMFEventType.NULL) {
			if(eObj instanceof Connector) {
				Connector connector = (Connector) eObj;
				
				if(connector.getOwner() instanceof Class) {
					Class clas = (Class) connector.getOwner();

					if(Utility.isElementHaveModelicaMLStereotypeApplied(clas) && (connector.getAppliedStereotype(Constants.stereotypeQName_Connection) == null)){
						return ctx.createFailureStatus(new Object[] { "UML Connector "+connector.getName()+" owned by Class "+clas.getName()+" must have <<Connection>> Stereotype Applied."} );
					}
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}

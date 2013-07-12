/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.PrimitiveType;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C18:
 * 	Rule :	Any UML Generalization that is owned by a UML Class with a ModelicaML stereotype 
 * 			applied must have the stereotype <<TypeExtendsRelation>> if it points to a 
 * 			UML Primitive Type instance or  <<ExtendsRelation>> otherwise.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C18_GeneralizationStereotypeConstraint extends
		AbstractModelConstraint {

	public C18_GeneralizationStereotypeConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		 
		EObject eObj  = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		// In Batch mode
		if(eType == EMFEventType.NULL){
			if( eObj instanceof Generalization){
				Element ownerClass = ((Generalization) eObj).getOwner();
				
				// Check whether Owner element is a UML Class
				if(ownerClass instanceof Class) {
					// Check the Owner Class with ModelicaML stereotype
					if(Utility.isElementHaveModelicaMLStereotypeApplied(ownerClass)) {
						List<Element> targetList = ((Generalization) eObj).getTargets();
						
						for (Element element : targetList) {
							
							if(element instanceof PrimitiveType){
								if((((Generalization) eObj).getAppliedStereotype(Constants.stereotypeQName_TypeRelation) == null)){
									return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID + 
											": UML::Generalization of a class pointing to a primitive type must have stereotype <<TypeRelation>> applied."});
								}
							}
							else if(((Generalization) eObj).getAppliedStereotype(Constants.stereotypeQName_ExtendsRelation) == null){
								return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID + ": UML::Generalization of a class must have <<ExtendsRelation>> stereotype applied."});
							}
						}
					}
					
				}
			}
		}
		return ctx.createSuccessStatus();
	}
}

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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
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
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;

/**
 * C12:
 * 	Rule :	Any Property of a UML::Class with the ModelicaML stereotype <<Block>> applied 
 * 			must have causality input or output. 
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C12_PropertyCausalityOfBlockConstriant extends
		AbstractModelConstraint {

	public C12_PropertyCausalityOfBlockConstriant() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		
		/*
		 * TODO: deactivated for now because the rule is not defined properly ...
		 */
		
//		if(eObj instanceof Class && (((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Block) != null)){
//			Class blockClass = (Class) eObj;
//			List<Element> class_elements = blockClass.allOwnedElements();
//			
//			for (Element element : class_elements) {
//				
//				if((element instanceof Property) && (((Property)element).getAppliedStereotype(Constants.stereotypeQName_Variable) != null))
//				{
//					Property class_property = (Property) element;
//					Element propertyValueElement = (EnumerationLiteral)class_property.getValue(class_property.getAppliedStereotype(Constants.stereotypeQName_Variable), Constants.propertyName_causality);
//					if(propertyValueElement == null)
//					{
////							if(eType != EMFEventType.NULL){
////								System.out.println("C12 : NULL");
////							}
//						return ctx.createFailureStatus(new Object[] { Constants.validationKeyWord_NOT_VALID + ": '" 
//								+ class_property.getName()+"' must have causality set to either 'input' or 'output' " +
//										"because it is owned by the restricted Modelica class 'block'." });
//					}
//				}
//			}
//		}
		return ctx.createSuccessStatus();
	}

}

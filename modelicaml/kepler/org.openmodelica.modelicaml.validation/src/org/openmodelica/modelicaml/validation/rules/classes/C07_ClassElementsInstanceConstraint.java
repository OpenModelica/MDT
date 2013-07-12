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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.StateMachine;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C7:
 * 	Rule : A UML Class with a ModelicaML stereotype applied can only contain elements 
 * 			that are instances of: 
 * 				- UML Class
 * 				- UML Enumeration
 * 				- UML PrimitiveType 
 * 				- UML Property
 * 				- UML Port
 * 				- UML Parameter
 * 				- UML  StateMachine 
 * 				- UML Activity
 * 				- UML OpaqueBehavior
 *				- UML FunctionBehavior
 *				- UML Comment
 *				- UML Connector
 *				- UML Constraint
 *				- UML Generalization 
 *				- UML Dependency
 * 				- UML Region
 * 
 * 	Severity : WARNING
 * 
 *	Mode : Live
 */
	public class C07_ClassElementsInstanceConstraint extends AbstractModelConstraint {

	public C07_ClassElementsInstanceConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {

		EObject eObj = ctx.getTarget();

		// Live and Batch Mode
		if(eObj instanceof Element && ((Element)eObj).getOwner() instanceof NamedElement){
			Element element = (Element) eObj;
			NamedElement elementOwner = (NamedElement) element.getOwner();

			if(elementOwner != null && elementOwner instanceof Class && Utility.isElementHaveModelicaMLStereotypeApplied(elementOwner))
			{
				if((element instanceof Dependency) || (element instanceof Generalization)) 
				{
					// NOP
				}
				else if((element instanceof Constraint) || (element instanceof Connector))
				{
					// NOP
				}
				else if((element instanceof Comment))
				{
					// NOP
				}
				else if((element instanceof OpaqueBehavior) || (element instanceof FunctionBehavior))
				{
					// NOP
				}
				else if((element instanceof Activity) || (element instanceof StateMachine))
				{
					// NOP
				}
				else if((element instanceof Parameter) || (element instanceof Port))
				{
					// NOP
				}
				else if((element instanceof Property) || (element instanceof PrimitiveType))
				{
					// NOP
				}
				else if((element instanceof Enumeration) || (element instanceof Class))
				{
					// NOP
				}
				else if((element instanceof Region))
				{
					// NOP
				}
				else
				{
					return ctx.createFailureStatus(new Object[] {"'"+element.eClass().getName()+"(s)' ('"+((NamedElement)element).getName()+"') is not supported."  } );
				}
			}
		}


		return ctx.createSuccessStatus();
	}

}

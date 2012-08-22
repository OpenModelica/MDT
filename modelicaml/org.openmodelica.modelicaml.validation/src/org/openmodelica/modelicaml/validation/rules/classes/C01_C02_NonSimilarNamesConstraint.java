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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StateMachine;

/**
 * C1:
 *  Rule : UML Packages that have the same owner must not have same names.
 *  
 *	Severity: ERROR
 *
 *	Mode : Live
 *
 * C2:
 * 	Rule : UML Classes (that is not UML Behavior, except UML State Machine) 
 * 		   that have the same owner must not have same names.
 * 
 *  Severity : ERROR
 *  
 *  Mode : Live
 */
public class C01_C02_NonSimilarNamesConstraint extends AbstractModelConstraint {

	/**
	 * 
	 */
	public C01_C02_NonSimilarNamesConstraint() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		// TODO Auto-generated method stub
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		String[] modelica_keywords = {"algorithm", "discrete", "false", "model", "redeclare", 
				  "and", "each", "final",  "not", "replaceable", "annotation", 
				  "else", "flow", "operator", "return", "assert", "elseif", "for", 
				  "or", "stream", "block", "else", "when", "function", "outer", "then", 
				  "break", "encapsulated", "if", "output", "true", "class", "end", "import", 
				  "package", "type", "connect", "enumeration", "in", "parameter", "when","connector", 
				  "equation", "initial", "partial", "while", "constant", "expandable", "inner", "protected", 
				  "within", "constrainedby", "extends", "input", "public",  "der", "external", "loop", "record"};
		List<String> modelicaKeywordList = new ArrayList<String>(Arrays.asList(modelica_keywords));

		
			// In case of both Live and Batch mode
		
			
			if(eObj instanceof NamedElement)
			{
				NamedElement root_element = null;
				if(eType == EMFEventType.NULL){
					root_element = (NamedElement) eObj;
				}
				else{
					root_element = (NamedElement) ((NamedElement) eObj).getOwner();
				}
				
				if (root_element != null) {
					List<Element> elementList = root_element.getOwnedElements();
					for (Element element : elementList) {
						
						for (Element element2 : elementList) {
							
							// Check packages, Classes with StateMachines except Behavior for similar names
							if((element2 instanceof NamedElement) && (element2 instanceof NamedElement) 
									&& (element instanceof Package || ((element instanceof Class && !(element instanceof Behavior)) || element instanceof StateMachine)) 
									&& (element instanceof Package || ((element2 instanceof Class && !(element2 instanceof Behavior)) || element2 instanceof StateMachine))){
//								if(((NamedElement) element2).getName().equalsIgnoreCase(((NamedElement)element).getName()) && !(element2.equals(element)))
								if(((NamedElement) element2).getName().equals(((NamedElement)element).getName()) && !(element2.equals(element))){
									return ctx.createFailureStatus(new Object[]{"'" + root_element.getQualifiedName() 
											+ "' contains packages or classes with same name '"+((NamedElement)element).getName() + "'"});
								}
							}
						}
						
						// Check packages, Classes with StateMachines except Behavior for Modelica keywords
						if(element instanceof Package || element instanceof Class){
							for (String keyword : modelicaKeywordList) {
//								if(keyword.equalsIgnoreCase(((NamedElement) element).getName()))
								if(keyword.equals(((NamedElement) element).getName())){
									return ctx.createFailureStatus(new Object[]{root_element.getQualifiedName()
											+" contains packages or classes with name like Modelica keyword '"+((NamedElement)element).getName() +"'"});
								}
							}
						}
					}
					
					// Check properties of class for similar names
					if(root_element instanceof Class && ! (root_element instanceof Behavior) || root_element instanceof StateMachine){
						List <Property> propertyList = ((Class) root_element).getOwnedAttributes();
											
						for (Property property : propertyList) {
							
							String propertyName = property.getName();
							
							for (Property property2 : propertyList) {
								if(!(property2.equals(property)) && (property2.getName().equals(propertyName))){
									return ctx.createFailureStatus(new Object[] {((Class)root_element).getQualifiedName()+" contains properties with same name "+propertyName});
								}
							}
							
							// Check properties of class for Modelica Keywords
							for (String keyword : modelicaKeywordList) {
								if(keyword.equals(property.getName())){
									return ctx.createFailureStatus(new Object[]{((Class)root_element).getQualifiedName()+" contains elements with name like Modelica keyword "+propertyName});
								}
							}
						}
					}
				}
				
			}
			
		return ctx.createSuccessStatus();
	}

}

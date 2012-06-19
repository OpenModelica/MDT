/**
 * 
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
				if(eType == EMFEventType.NULL)
				{
					root_element = (NamedElement) eObj;
				}
				else
				{
					root_element = (NamedElement) ((NamedElement) eObj).getOwner();
				}
				
				List<Element> elementList = root_element.getOwnedElements();
				for (Element element : elementList) {
					
					for (Element element2 : elementList) {
						
						// Check packages, Classes with StateMachines except Behavior for similar names
						if((element2 instanceof NamedElement) && (element2 instanceof NamedElement) 
								&& (element instanceof Package || ((element instanceof Class && !(element instanceof Behavior)) || element instanceof StateMachine)) 
								&& (element instanceof Package || ((element2 instanceof Class && !(element2 instanceof Behavior)) || element2 instanceof StateMachine))){
//							if(((NamedElement) element2).getName().equalsIgnoreCase(((NamedElement)element).getName()) && !(element2.equals(element)))
							if(((NamedElement) element2).getName().equals(((NamedElement)element).getName()) && !(element2.equals(element))){
								return ctx.createFailureStatus(new Object[]{"'" + root_element.getName() 
										+ "' contains packages or classes with same name '"+((NamedElement)element).getName() + "'"});
							}
						}
					}
					
					// Check packages, Classes with StateMachines except Behavior for Modelica keywords
					if(element instanceof Package || element instanceof Class){
						for (String keyword : modelicaKeywordList) {
//							if(keyword.equalsIgnoreCase(((NamedElement) element).getName()))
							if(keyword.equals(((NamedElement) element).getName())){
								return ctx.createFailureStatus(new Object[]{root_element.getName()
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
							if(!(property2.equals(property)) && (property2.getName().equalsIgnoreCase(propertyName))){
								return ctx.createFailureStatus(new Object[] {((Class)root_element).getName()+" contains properties with same name "+propertyName});
							}
						}
						
						// Check properties of class for Modelica Keywords
						for (String keyword : modelicaKeywordList) {
							if(keyword.equals(property.getName())){
								return ctx.createFailureStatus(new Object[]{((Class)root_element).getName()+" contains elements with name like Modelica keyword "+propertyName});
							}
						}
					}
				}
			}
			
		return ctx.createSuccessStatus();
	}

}

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
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;

/**
 * C15:
 * 	Rule :	UML Class with ModelicaML stereotype applied can only have one UML Dependency 
 * 			with the stereotype <<RedeclareRelation>>.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C15_RedeclareRelationStereotypeForDependencyOfClassConstraint
		extends AbstractModelConstraint {

	/**
	 * 
	 */
	public C15_RedeclareRelationStereotypeForDependencyOfClassConstraint() {
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
		
		// In Batch Mode
		if(eType == EMFEventType.NULL)
		{
			C03_ClassWithModelicaMLStereotypeConstraint classWithModelicaMLStereotypeConstraint = new C03_ClassWithModelicaMLStereotypeConstraint();
			if((eObj instanceof Class) && (classWithModelicaMLStereotypeConstraint.validate(ctx).isOK()))
			{
				Class clas = (Class) eObj;
				List<Dependency> classDependencies = clas.getClientDependencies();
				boolean havingOnlyOneRedeclareRelationStereotype = false;
				
				for (Dependency dependency : classDependencies) {
					
					if(dependency.getAppliedStereotype("ModelicaML::ModelicaRelationsConstructs::RedeclareRelation") != null)
					{ 
						if(havingOnlyOneRedeclareRelationStereotype == true)
						{
							havingOnlyOneRedeclareRelationStereotype = false;
							return ctx.createFailureStatus(new Object[] {clas.getName()+" can have only one UML Dependency with RedeclareRelation Stereotype"});
						}
						else{
							havingOnlyOneRedeclareRelationStereotype = true;
						}
					}
				}
				
				
			}
		}
		
		return ctx.createSuccessStatus();
	}

}

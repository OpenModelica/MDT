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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
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
 * Contributors: 
 *   Wladimir Schamai, EADS Innovation Works 2009-2011
 *   Uwe Pohlmann, University of Paderborn 2009-2010
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011
 */
package org.openmodelica.modelicaml.tabbedproperties.editors.filters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.papyrus.diagram.common.editparts.IUMLEditPart;
import org.eclipse.papyrus.profile.utils.UmlElementFilter;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.State;

// TODO: Auto-generated Javadoc
/**
 * The Class AlgorithmSectionCodeSectionFilter.
 */
public class AlgorithmSectionCodeSectionFilter extends UmlElementFilter {

	/* (non-Javadoc)
	 * @see org.eclipse.papyrus.profile.utils.UmlElementFilter#select(java.lang.Object)
	 */
	@Override
	public boolean select(Object object) {
		EObject element = null;
		Boolean hasAppropriateStereotype = false;
		
		// Get the selected element
		
		// TODO: Find the right meta class for ModelElementItem
		if (object instanceof ModelElementItem) {
			element = ((ModelElementItem)object).getEObject();
		}
		else if (object instanceof IUMLEditPart) {
			element = ((IUMLEditPart)object).getUMLElement();
		}
		
		// Decide if an editor tab should appear
		
		// For UML OpaqueBehavior can include Modelica equation or algorithm code
		if ( element instanceof OpaqueBehavior && !(element instanceof FunctionBehavior) ){
			if (((OpaqueBehavior)element).getAppliedStereotype("ModelicaML::ModelicaBehaviorConstructs::Algorithm(Code)") != null
					|| ((OpaqueBehavior)element).getOwner() instanceof State ) { 
				return true;
			}
		}
		// For UML OpaqueAction, in an Activity (diagram), that can include Modelica equation or algorithm code
		if ( element instanceof OpaqueAction ){
			if (((OpaqueAction)element).getActivity().getAppliedStereotype("ModelicaML::ModelicaBehaviorConstructs::ConditionalAlgorithm(Diagram)") != null) { 
				return true; 
			}
		}
		
		if ( element instanceof FunctionBehavior ){
			if (((FunctionBehavior)element).getAppliedStereotype("ModelicaML::ModelicaClassConstructs::Function") != null) { 
				return true; 
			}
		}

		
		return false;
	}
}

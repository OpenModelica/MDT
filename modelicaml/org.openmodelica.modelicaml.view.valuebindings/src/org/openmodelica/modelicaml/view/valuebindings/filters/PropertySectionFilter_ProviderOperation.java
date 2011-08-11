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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.view.valuebindings.filters;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;


public class PropertySectionFilter_ProviderOperation implements IFilter {

	@Override
	public boolean select(Object toTest) {
		if (toTest instanceof TreeObject) {
			TreeObject item = (TreeObject)toTest;
			
			boolean mediatorIsLoaded = false;
	    	TreeObject mediator = TreeUtls.getNearestMediator(item);
	    	if (mediator != null) {
	    		Element mediatorElement = mediator.getUmlElement();
	    		EList<Dependency> clientDep = TreeUtls.getMediatorDependency((NamedElement)mediatorElement, 
	    				(NamedElement) item.getUmlElement(), Constants.stereotypeQName_ObtainsValueFrom);
	    		if (clientDep.size() == 1) {
	    			mediatorIsLoaded = true;
	    		}
			}
			if (item.isValueProvider()  && !item.isReadOnly() && mediatorIsLoaded) {
				return true;
			}
		}
		return false;
	}
}

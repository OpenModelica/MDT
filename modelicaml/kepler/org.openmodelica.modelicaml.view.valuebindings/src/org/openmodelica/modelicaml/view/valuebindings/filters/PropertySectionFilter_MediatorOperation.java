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
package org.openmodelica.modelicaml.view.valuebindings.filters;
import org.eclipse.jface.viewers.IFilter;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;


public class PropertySectionFilter_MediatorOperation implements IFilter {

	@Override
	public boolean select(Object toTest) {
		if (toTest instanceof TreeObject) {
			TreeObject item = (TreeObject)toTest;
			
//			/* TODO: This seems to be a bug. After saving of imported models the applied stereotypes are null.
//			 * WORKAROUND: If the uml element or the stereotype is null -> ask user to reload the view.  
//			 */
//			if (item.getUmlElement() == null 
//					|| item.getUmlElement().getAppliedStereotype(Constants.stereotypeQName_ValueMediator) == null) {
//				
//				String title = "Value Bindings View - Reload Confirmation";
//				String message = "The value bindings resource were modified. " +
//						"The Value Bindings view must be synchronized in order to allow further action." +
//						"\n\nShould the Value Bindings be reloaded? ";
//				
//				boolean go = MessageDialog.openQuestion(new Shell(), title, message);
//				
//				if (go) {
//					IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.openmodelica.modelicaml.view.valuebindings.views.ValueBindingsView");
//					if (view instanceof ValueBindingsView) {
//						((ValueBindingsView)view).actionReload.run();
//					}
//				}
//			}
			
			if ( item.isValueMediator() && !item.isReadOnly() ) {
				return true;
			}
		}
		return false;
	}
}

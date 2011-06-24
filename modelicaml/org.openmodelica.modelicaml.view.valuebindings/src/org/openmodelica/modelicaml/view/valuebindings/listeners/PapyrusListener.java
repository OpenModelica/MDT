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
package org.openmodelica.modelicaml.view.valuebindings.listeners;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.papyrus.core.listenerservice.IPapyrusListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;
import org.openmodelica.modelicaml.view.valuebindings.views.ValueBindingsView;

public class PapyrusListener implements IPapyrusListener {
	
//	public PapyrusListener() {	
//	}

	
	@Override
	public void notifyChanged(Notification notification) {

		if ( notification.getEventType() == Notification.REMOVE && notification.getNewValue() == null ) {
//			System.err.println("Event type: " + notification.getEventType());
////		System.err.println("Notifier: " + ((NamedElement)notification.getNotifier()).getName() );
//			System.err.println("Notifier: " + notification.getNotifier() );
//			System.err.println("OldValue: " + notification.getOldValue());
//			System.err.println("NewValue: " + notification.getNewValue());
			
			//TODO: this is a workaround for not deleting tree items when its client dependency is deleted...
			if (!(notification.getNotifier() instanceof Dependency)) { 
				
				// if it is a UML element that was deleted
				if (notification.getOldValue() instanceof NamedElement) {
					// get the value bindings view
					IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.openmodelica.modelicaml.view.valuebindings.views.ValueBindingsView");
					ValueBindingsView myView = null;
					
					if (view instanceof ValueBindingsView) {
					    myView = (ValueBindingsView)view;			
					}
					
					if (myView != null) {
						TreeUtls.removeTreeItem(((NamedElement)notification.getOldValue()), myView.getTreeRoot(), myView.getViewer());
//						myView.removeTreeItem(((NamedElement)notification.getOldValue()));
						myView.getViewer().refresh(); // refresh the entire viewer
//						System.err.println("REMOVED: " + ((NamedElement)notification.getOldValue()).getName());
					}
				}
			}
		}
	}
}

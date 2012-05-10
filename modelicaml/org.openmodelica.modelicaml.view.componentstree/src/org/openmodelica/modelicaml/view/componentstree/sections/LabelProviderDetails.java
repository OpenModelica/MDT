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
package org.openmodelica.modelicaml.view.componentstree.sections;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.componentstree.Activator;


public class LabelProviderDetails implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getImage(Object element) {
		Assert.isTrue(element instanceof IStructuredSelection);
	    Object input = ((IStructuredSelection) element).getFirstElement();
	    Assert.isTrue(input instanceof TreeObject);
	    TreeObject item = (TreeObject) input;
	    if (item != null) {
	    	if (item.isRoot()) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/Class.gif");
			}
	    	if (item.getUmlElement() instanceof Port) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/port.gif");
			}
	    	if (item.getUmlElement() instanceof Property) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/Property.gif");
			}
	    	if (item.getUmlElement() instanceof Signal) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/Signal.gif");
			}
	    	if (item.getUmlElement() instanceof StateMachine) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/StateMachine.gif");
			}
	    	if (item.getUmlElement() instanceof Region) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/Region.gif");
			}
	    	if (item.getUmlElement() instanceof State) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/State.gif");
			}
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		Assert.isTrue(element instanceof IStructuredSelection);
	    Object input = ((IStructuredSelection) element).getFirstElement();
	    Assert.isTrue(input instanceof TreeObject);
	    TreeObject item = (TreeObject) input;
	    if (item != null) {
	    	if (((TreeObject)item).getDotPath().trim().equals("")) {
	    		return ((TreeObject)item).getName();
	    	}
		    return ((TreeObject)item).getDotPath();
		}
	    return " ";
		// TODO Auto-generated method stub
//		return "Component details";
	}

}

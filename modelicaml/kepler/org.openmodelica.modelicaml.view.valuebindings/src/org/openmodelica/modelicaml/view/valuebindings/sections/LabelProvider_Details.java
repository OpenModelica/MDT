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
package org.openmodelica.modelicaml.view.valuebindings.sections;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.valuebindings.Activator;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;


public class LabelProvider_Details implements ILabelProvider {

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
	    	 if (item.isModel()) {
				return SWTResourceManager.getImage(Activator.class, "/icons/Model.gif");
			}
	    	if (item.isPackage()) {
				return SWTResourceManager.getImage(Activator.class, "/icons/Package.gif");
			}
		    else if (item.isValueMediatorContainer()) {
		    	return SWTResourceManager.getImage(Activator.class, "/icons/valueMediatorsContainer.png");
			}
		    else if (item.isValueMediator() || item.isValueMediatorsNode()) {
		    	return SWTResourceManager.getImage(Activator.class, "/icons/valueMediator.png");
			}
		    else if (item.isValueClientsNode()) {
		    	return SWTResourceManager.getImage(Activator.class, "/icons/addValueClient.png");
			}
		    else if (item.isValueProvidersNode()) {
		    	return SWTResourceManager.getImage(Activator.class, "/icons/addValueProviders.png");
			}
		    else if (item.isValueClient() || item.isValueProvider()) {
		    	return SWTResourceManager.getImage(Activator.class, "/icons/Property.gif");
			}
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		Assert.isTrue(element instanceof IStructuredSelection);
	    Object input = ((IStructuredSelection) element).getFirstElement();
//	    Assert.isTrue(input instanceof TreeObject);
	    if (input instanceof TreeObject) {
	    	TreeObject item = (TreeObject) input;
    		Element umlElement = item.getUmlElement(); 
	    	if (item.isValueClient()) {
	    		if (umlElement instanceof NamedElement) {
					return "Client '" + ((NamedElement)umlElement).getName() + "'   (" + ((NamedElement)umlElement).getQualifiedName() + ")";
				}
	    		return ((TreeObject)item).getName() + " (Value Client)";
			}
	    	if (item.isValueMediator()) {
	    		if (umlElement instanceof NamedElement) {
					return "Mediator '" + ((NamedElement)umlElement).getName() + "'   (" + ((NamedElement)umlElement).getQualifiedName() + ")";
				}
	    		return ((TreeObject)item).getName() + " (Value Mediator)";
			}
	    	if (item.isValueProvider()) {
	    		if (umlElement instanceof NamedElement) {
					return "Provider '" + ((NamedElement)umlElement).getName() + "'   (" + ((NamedElement)umlElement).getQualifiedName() + ")";
				}
	    		return ((TreeObject)item).getName() + " (Value Provider)";
			}
		    return ((TreeObject)item).getName();
		}
	    return " ";
	}

}

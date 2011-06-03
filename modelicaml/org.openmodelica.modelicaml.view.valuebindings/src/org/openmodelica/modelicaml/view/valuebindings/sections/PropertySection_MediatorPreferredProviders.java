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
package org.openmodelica.modelicaml.view.valuebindings.sections;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.profile.ImageManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.tabbedproperties.editors.sections.AbstractMultiStringSection;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;

// TODO: Auto-generated Javadoc
/**
 * The Class InstanceModificationsSection.
 */
public class PropertySection_MediatorPreferredProviders extends AbstractMultiStringSection {

	/**
	 * Instantiates a new instance modifications section.
	 */
	public PropertySection_MediatorPreferredProviders() {
		super();
		stereotypPropertieName = Constants.propertyName_preferredProviders; 
	}

	/* (non-Javadoc)
	 * @see org.openmodelica.modelicaml.tabbedproperties.editors.sections.AbstractMultiStringSection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		// TODO Auto-generated method stub
		super.createControls(parent, tabbedPropertySheetPage);
		FormData data;
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.width = 400;
		data.top = new FormAttachment(overviewLabel, 5);
		data.height = 150;
		modificationList.setLayoutData(data);
		
		// remove the edit listener
		modificationList.removeMouseListener(listlistener);

	}

	protected void addButtons(Composite composite, FormData data){
		// ///////////////////////////////////////////////////////////////////////////
		// Create and place button vertically on the left side
		// Button Delete Element
		
		removeButton = new Button(composite, SWT.PUSH);
		removeButton.setVisible(true);
		removeButton.setImage(ImageManager.IMG_DELETE);
		removeButton.setToolTipText("Delete selected element(s)");
		data = new FormData();
		data.left = new FormAttachment(modificationList, 5);
		data.top = new FormAttachment(overviewLabel, 0);
		removeButton.setLayoutData(data);
		removeButton.addMouseListener(deleteButtonlistener);
	}
	
	
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		// get the selectedUmlElement
		Object input = ((IStructuredSelection) selection).getFirstElement();
		if (input instanceof TreeObject) {
			this.selectedUmlElement = ((TreeObject)input).getUmlElement();	
		}
		super.setInput(part, selection);
	}


}

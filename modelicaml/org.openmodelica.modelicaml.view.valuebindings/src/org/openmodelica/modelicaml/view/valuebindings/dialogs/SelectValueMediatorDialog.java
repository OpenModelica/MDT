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
package org.openmodelica.modelicaml.view.valuebindings.dialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.valuebindings.helpers.BindingsDataCollector;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeUtls;

public class SelectValueMediatorDialog extends Dialog {

	private String title;
	private List<Element> elementsForSelection;
	
	private String textSourceTreeItem;
	private String toolTipTextSourceTreeItem;
	
	private String groupTitle;
	private String messageText;
	
	private BindingsDataCollector dataCollection;
	
	private Element selectedElement = null;
	
	public SelectValueMediatorDialog(Shell parentShell, 
			String title, 
			String textSourceTreeItem,
			String toolTipTextSourceTreeItem,
			String groupTitle,
			String messageText,
			ArrayList<Element> elementsForSelection,
			BindingsDataCollector dataCollection
			) {
	
		super(parentShell);
		
		this.title = title;
		this.textSourceTreeItem = textSourceTreeItem;
		this.toolTipTextSourceTreeItem = toolTipTextSourceTreeItem;
		this.groupTitle = groupTitle;
		this.messageText = messageText;
		this.elementsForSelection = elementsForSelection;
		this.dataCollection = dataCollection;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText(this.title);
		super.configureShell(newShell);
	}

	
	@Override
	protected Control createDialogArea(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
//		Link sourceElementInfo = new Link(composite, 0);
//		Label valueClientInfo = new Label(composite, 0);
		Button valueClientInfo = new Button(composite, SWT.RADIO);
		valueClientInfo.setSelection(true);
//		valueClientInfo.setEnabled(false);
		valueClientInfo.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "/icons/addValueClient.png"));
		valueClientInfo.setToolTipText(toolTipTextSourceTreeItem);
		valueClientInfo.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
		valueClientInfo.setText(textSourceTreeItem);
		
		Link message = new Link(composite, SWT.NONE);
		message.setText(messageText);
		
		Group group = new Group(composite, SWT.NONE);
		group.setLayout(new RowLayout(SWT.VERTICAL));
		GridData gd_group = new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1);
		gd_group.widthHint = 357;
		group.setLayoutData(gd_group);
		group.setText(groupTitle);

		HashMap<Element, HashSet<Element>> map = dataCollection.getMediatorToProviders();

		for (Element element : elementsForSelection) {
			if (element instanceof Property) {
				
				Property mediator = (Property)element;
				
				final Button rb = new Button(group, SWT.RADIO);
				rb.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "/icons/valueMediator.png"));
				
				// name
				String toolTipTextSelectionItem = "Q.Name: " + mediator.getQualifiedName();
				
				// operation
				String mediatorOperation = DeriveValueBindingCodeUtls.getOperationSpecification(mediator, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
				if (mediatorOperation == null) {
					toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\nOperation: NONE.";
				}
				else if (DeriveValueBindingCodeUtls.isValidMediatorMultipleItemsScript(mediatorOperation)) {
					toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\nOperation: MULTIPLE providers operation.";
				}
				else if (DeriveValueBindingCodeUtls.isValidMediatorSingleItemsScript(mediatorOperation)) {
					toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\nOperation: SINGLE provider operation.";
				}

				// type
				if (mediator instanceof TypedElement) {
					Type mediatorType = ((TypedElement)mediator).getType();
					if (mediatorType != null) {
						String type = "\r\nType: " + mediatorType.getName().replaceFirst("Modelica", "");
						toolTipTextSelectionItem = toolTipTextSelectionItem + type;
					}
				}
				
				// variability 
				Stereotype stereotype = mediator.getAppliedStereotype(Constants.stereotypeQName_ValueMediator);
				if (stereotype != null) {
					Object o = mediator.getValue(stereotype, Constants.propertyName_variability);
					if (o instanceof EnumerationLiteral) {
						toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\nVariability: " + ((EnumerationLiteral)o).getName();
					}
				}
				
				if (map != null) {// of there are mediators with providers at all
					HashSet<Element> providers = map.get(mediator); // get the providers for this mediator
					if (providers != null) { 
						if (providers.size() > 0 ) {
							toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\n\r\nProviders available in the instantiated class: ";
						}
						for (Element provider : providers) {
							HashSet<TreeObject> treeObjects = dataCollection.getInstantiationTreeObjects(provider);
							for (TreeObject treeObject : treeObjects) {
								toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\n          - " + treeObject.getDotPath();
							}
						}
					}
				}
				
				toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\n";
				
				rb.setToolTipText(toolTipTextSelectionItem);
				rb.setText(mediator.getName());
				rb.setData(mediator);
				rb.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						setSelectedElement((Element) rb.getData());
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						setSelectedElement((Element) rb.getData());
					}
				});
			}
		}
		return parent;
	}


	
	public void setSelectedElement(Element selectedElement) {
		this.selectedElement = selectedElement;
	}

	public Element getSelectedElement() {
		return selectedElement;
	}

}

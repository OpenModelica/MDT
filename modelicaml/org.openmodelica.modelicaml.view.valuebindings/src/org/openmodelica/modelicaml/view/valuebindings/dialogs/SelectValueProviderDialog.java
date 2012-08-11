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
package org.openmodelica.modelicaml.view.valuebindings.dialogs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.EList;
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
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.valuebindings.helpers.BindingsDataCollector;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeUtls;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;

public class SelectValueProviderDialog extends Dialog {

	private String title;
	private String groupTitle;
	private String messageText;

	
	// providers list
	private List<TreeObject> elementsForSelection;
	
	// the mediator
	private Element mediatorElement;
	
	// deduced client instantiation tree item
	private TreeObject clientTreeObject; 
	
	// data collection for getting information about the mediator and providers 
	private BindingsDataCollector dataCollection;
	
	// provider selected by the user
	private TreeObject selectedProvider = null;

	// preferred providers option
	private boolean addToPrefferedProviders = false;
	private String selectedProviderPath = "";
	
	private String preferredProvidersSelectionOptionToolTipText = "This action will remember " +
					"your selection decision by storing the client and selected provider pair as follows:";

	private String preferredProvidersSelectionOptionToolTipTextExplanation = 
			"\nThis information will be used to pre-select providers in this diaolog. " +
			"\nMoreover, the 'Update binding' feature will use this information to " +
			"\nautomatically select one provider if there are multiple.";

	
	
	public SelectValueProviderDialog(Shell parentShell, 
			String title, 
			TreeObject clientTreeObject,
			Element mediatorElement,
			String groupTitle,
			String messageText,
			List<TreeObject> elementsForSelection,
			BindingsDataCollector dataCollection
			) {
	
		super(parentShell);
		
		this.title = title;
		this.clientTreeObject = clientTreeObject;
		this.mediatorElement = mediatorElement;
//		this.toolTipTextValueMediator = toolTipTextValueMediator;
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
//		Label valueMediatorInfo = new Label(composite, 0);
		Button valueMediatorInfo = new Button(composite, SWT.RADIO);
		valueMediatorInfo.setSelection(true);
		valueMediatorInfo.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "/icons/valueMediator.png"));

		
		// name
		String toolTipTextMediator = "Q.Name: " + ((NamedElement)mediatorElement).getQualifiedName();
		
		// operation
		String mediatorOperation = DeriveValueBindingCodeUtls.getOperationSpecification(mediatorElement, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
		if (mediatorOperation == null) {
			toolTipTextMediator = toolTipTextMediator + "\r\nOperation: NONE.";
		}
		else if (DeriveValueBindingCodeUtls.isValidMediatorMultipleItemsScript(mediatorOperation)) {
			toolTipTextMediator = toolTipTextMediator + "\r\nOperation: MULTIPLE providers operation.";
		}
		else if (DeriveValueBindingCodeUtls.isValidMediatorSingleItemsScript(mediatorOperation)) {
			toolTipTextMediator = toolTipTextMediator + "\r\nOperation: SINGLE provider operation.";
		}

		// type
		Type type = ((TypedElement)mediatorElement).getType();
		if (type != null ) {
			String typeString = "\r\nType: " + type.getName().replaceFirst("Modelica", "");
			toolTipTextMediator = toolTipTextMediator + typeString;
		}
		
		// variability 
		Stereotype stereotype = mediatorElement.getAppliedStereotype(Constants.stereotypeQName_ValueMediator);
		if (stereotype != null) {
			Object o = mediatorElement.getValue(stereotype, Constants.propertyName_variability);
			if (o instanceof EnumerationLiteral) {
				toolTipTextMediator = toolTipTextMediator + "\r\nVariability: " + ((EnumerationLiteral)o).getName();
			}
		}
		HashMap<Element, HashSet<Element>> map = dataCollection.getMediatorToProviders();
		if (map != null) {// of there are mediators with providers at all
			HashSet<Element> providers = map.get(mediatorElement); // get the providers for this mediator
			if (providers != null) { 
				if (providers.size() > 0 ) {
					toolTipTextMediator = toolTipTextMediator + "\r\n\r\nProviders available in the instantiated class: ";
				}
				for (Element provider : providers) {
					HashSet<TreeObject> treeObjects = dataCollection.getInstantiationTreeObjects(provider);
					for (TreeObject treeObject : treeObjects) {
						toolTipTextMediator = toolTipTextMediator + "\r\n          - " + treeObject.getDotPath();
					}
				}
			}
		}
		valueMediatorInfo.setToolTipText(toolTipTextMediator);
		
		valueMediatorInfo.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
		valueMediatorInfo.setText( "Value Mediator: " + ((NamedElement)mediatorElement).getName());
		
		Link message = new Link(composite, SWT.NONE);
		message.setText(messageText);
		
		Group group = new Group(composite, SWT.NONE);
		group.setLayout(new RowLayout(SWT.VERTICAL));
		GridData gd_group = new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1);
		gd_group.widthHint = 474;
		group.setLayoutData(gd_group);
		group.setText(groupTitle);
		
		final Button btnAddtoPreferredProviders = new Button(composite, SWT.CHECK);
		btnAddtoPreferredProviders.setEnabled(false);
		btnAddtoPreferredProviders.setToolTipText("This action will remember " +
				"the selection decision by storing the client and the selected provider pair as follows:" +
				"\n" + clientTreeObject.getDotPath() + " --> " +  selectedProviderPath
				+ "\n");
		btnAddtoPreferredProviders.setText("Remember this selection (i.e., add to preferred providers)");
		
		btnAddtoPreferredProviders.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnAddtoPreferredProviders.getSelection()) {
					setAddToPrefferedProviders(true);
				}
				else {
					setAddToPrefferedProviders(false);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		EList<String> preferredProviders = TreeUtls.getStringListPropertyFromElement(mediatorElement, Constants.stereotypeQName_ValueMediator, Constants.propertyName_preferredProviders);
		
		for (TreeObject provider : elementsForSelection) {
			
			// a class component may be client and provider at the same time
			// avoid referencing themself
			if (provider != clientTreeObject) { 
			
				NamedElement providerElement = (NamedElement) provider.getUmlElement();
				
				if (providerElement instanceof TypedElement) { // TODO: what if it is a State?
					
					final Button rb = new Button(group, SWT.RADIO);
					rb.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "/icons/addValueProviders.png"));
					
					// name
					String toolTipTextSelectionItem = "Provider Path: " + provider.getDotPath()
							+ "\nElement: " + providerElement.getQualifiedName();
					
					// operation
					EList<Dependency> depList = TreeUtls.getMediatorDependency((NamedElement)mediatorElement, providerElement, Constants.stereotypeQName_ObtainsValueFrom);
					String providerOperation = DeriveValueBindingCodeUtls.getOperationSpecification(depList.get(0), Constants.stereotypeQName_ObtainsValueFrom, Constants.propertyName_operation);
					if (providerOperation == null) {
						toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\nOperation: NONE.";
					}
					else {
						toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\nOperation: " + providerOperation;
					}

					// type
					if (providerElement instanceof TypedElement) {
						Type providerType = ((TypedElement)providerElement).getType();
						if (providerType != null ) {
							String providerTypeString = "\r\nType: " + providerType.getName().replaceFirst("Modelica", "");
							toolTipTextSelectionItem = toolTipTextSelectionItem + providerTypeString;
						}
					}
					
					// variability 
					Stereotype varStereotype = providerElement.getAppliedStereotype(Constants.stereotypeQName_Variable);
					if (varStereotype != null) {
						Object o = providerElement.getValue(varStereotype, Constants.propertyName_variability);
						if (o instanceof EnumerationLiteral) {
							toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\nVariability: " + ((EnumerationLiteral)o).getName();
						}	
					}
					
					toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\n";
					
					rb.setToolTipText(toolTipTextSelectionItem);
					rb.setText(provider.getDotPath());
					rb.setData(provider);
					
					
					// preselect the radio button if this provider is a preferred provider for the client.
					if (TreeUtls.isPreferredProviderForClient(preferredProviders, clientTreeObject.getDotPath(), provider.getDotPath())) {
						rb.setSelection(true);
						setSelectedElement(provider);
					}
					
					rb.addSelectionListener(new SelectionListener() {
						
						@Override
						public void widgetSelected(SelectionEvent e) {
							providerSelectionAction((TreeObject) rb.getData(), btnAddtoPreferredProviders);
						}
						
						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							providerSelectionAction((TreeObject) rb.getData(), btnAddtoPreferredProviders);
						}
					});
				}				
			}
		}
		
		return parent;
	}

	
	private void providerSelectionAction(TreeObject selectedProvider, Button btnAddtoPreferredProviders){
		
		// set the selected provider
		setSelectedElement(selectedProvider);
		
		//enable the remember button and set its tool tip text
		if (getSelectedElement() != null) {
			btnAddtoPreferredProviders.setEnabled(true);
			selectedProviderPath = getSelectedElement().getDotPath();
			btnAddtoPreferredProviders.setToolTipText(preferredProvidersSelectionOptionToolTipText 
					+ "\n\n" + TreeUtls.getPreferredProviderAssignmentString(clientTreeObject.getDotPath(), selectedProviderPath) + "\n"
					+ preferredProvidersSelectionOptionToolTipTextExplanation + "\n");
			
		}
		// disable the remember button and set its tool tip text
		else {
			btnAddtoPreferredProviders.setEnabled(false);
			selectedProviderPath = "";
			btnAddtoPreferredProviders.setToolTipText(preferredProvidersSelectionOptionToolTipText
					+ "\n\n" + TreeUtls.getPreferredProviderAssignmentString(clientTreeObject.getDotPath(), selectedProviderPath) + "\n"
					+ preferredProvidersSelectionOptionToolTipTextExplanation + "\n");
		}
	}
	
	
	public void setSelectedElement(TreeObject selectedElement) {
		this.selectedProvider = selectedElement;
	}

	public TreeObject getSelectedElement() {
		return selectedProvider;
	}

	public void setAddToPrefferedProviders(boolean addToPrefferedProviders) {
		this.addToPrefferedProviders = addToPrefferedProviders;
	}

	public boolean isAddToPrefferedProviders() {
		return addToPrefferedProviders;
	}

}

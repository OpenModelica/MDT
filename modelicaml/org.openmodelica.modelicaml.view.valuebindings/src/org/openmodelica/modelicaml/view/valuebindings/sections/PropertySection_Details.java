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
package org.openmodelica.modelicaml.view.valuebindings.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;
import org.openmodelica.modelicaml.view.valuebindings.views.ValueBindingsView;

public class PropertySection_Details extends AbstractPropertySection {

    private TreeObject item;

	private Text textName;
	private Link textType;
	private Link textOwner;

	private Label lblName;
	private Label lblType;

	private Label lblOwner;

	// only for clients
	private Label lblIsRequired;
	private Button btIsRequired;
	
	private TreeViewer viewer;

	 ModifyListener modifyListener = new ModifyListener() {
			
	    	@Override
			public void modifyText(ModifyEvent e) {
				if (item != null && item.getUmlElement() != null && item.getUmlElement() instanceof NamedElement) {
					storeText();
				}
			}
		};
		
	SelectionListener selectionListener = new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setValueClient_isRequired(btIsRequired.getSelection());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				if (item.isValueClient_required()) {
					btIsRequired.setSelection(true);
				}
				else {
					btIsRequired.setSelection(false);
				}
			}
		};
		
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        
        parent.setLayout(new GridLayout(1, false));
        Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        
		lblName = new Label(composite, SWT.NONE);
		lblName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblName.setBounds(10, 13, 49, 13);
		lblName.setText("Name:");
		
		textName = new Text(composite, SWT.BORDER);
		textName.setBounds(65, 10, 579, 19);
//		textName.addModifyListener(new ModifyListener() {
//			@Override
//			public void modifyText(ModifyEvent e) {
//				if (item != null && item.getUmlElement() != null && item.getUmlElement() instanceof NamedElement) {
//					storeText();
//				}
//			}
//		});
		
		lblType = new Label(composite, SWT.NONE);
		lblType.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblType.setBounds(10, 62, 49, 13);
		lblType.setText("Type:");
		
		textType = new Link(composite, SWT.NONE);
		textType.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textType.setBounds(65, 62, 579, 13);
		textType.setText("<a>New Link</a>");
		
		lblOwner = new Label(composite, SWT.NONE);
		lblOwner.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblOwner.setBounds(10, 43, 49, 13);
		lblOwner.setText("Owner:");
		
		textOwner = new Link(composite, SWT.NONE);
		textOwner.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textOwner.setBounds(65, 43, 579, 13);
		textOwner.setText("<a>New Link</a>");
		
		lblIsRequired = new Label(composite, SWT.NONE);
		lblIsRequired.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblIsRequired.setBounds(10, 82, 49, 13);
		lblIsRequired.setText("mandatory:");
		String toolTipText_isRequired = "If the client is mandatory then " +
										"\nthere must be a binding equation " +
										"\nfor this components when it is instantiated.";
		lblIsRequired.setToolTipText(toolTipText_isRequired);
		
		btIsRequired = new Button(composite, SWT.CHECK);
		btIsRequired.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btIsRequired.setBounds(65, 82, 579, 13);
		btIsRequired.setToolTipText(toolTipText_isRequired);
		
    }

   
    
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        Assert.isTrue(selection instanceof IStructuredSelection);
        Object input = ((IStructuredSelection) selection).getFirstElement();
        Assert.isTrue(input instanceof TreeObject);
        this.item = (TreeObject) input;
        
        if (getPart() != null && getPart() instanceof ValueBindingsView) {
			viewer = ((ValueBindingsView)getPart()).getViewer();
		}
    }

    public void refresh() {

    	boolean typeTextVisibility = item.isValueClient() || item.isValueMediator() || item.isValueProvider();
    	boolean ownerTextVisibility = !item.isValueClientsNode() && !item.isValueProvidersNode() && item.getUmlElement() != null &&  item.getUmlElement().getOwner() instanceof NamedElement;

    	boolean mediatorIsLoaded = false;
    	
    	TreeObject mediator = TreeUtls.getNearestMediator(item);
    	if (mediator != null) {
    		Element mediatorElement = mediator.getUmlElement();
    		EList<Dependency> clientDep = TreeUtls.getMediatorDependency((NamedElement)mediatorElement, (NamedElement) item.getUmlElement(), Constants.stereotypeQName_ProvidesValueFor);
    		
    		if (clientDep.size() == 1) {
    			mediatorIsLoaded = true;
    		}
		}
    	boolean isRequiredVisibility = item.isValueClient()
    									&& item.getUmlElement() != null 
    									&& mediatorIsLoaded;
    	
    	lblType.setVisible(typeTextVisibility);
		textType.setVisible(typeTextVisibility);
		
		// START: required value client
		lblIsRequired.setVisible(isRequiredVisibility);
		btIsRequired.removeSelectionListener(selectionListener);
		btIsRequired.setVisible(isRequiredVisibility);
		btIsRequired.setSelection(item.isValueClient_required());
		btIsRequired.addSelectionListener(selectionListener);
		// END: required value client
		
		lblOwner.setVisible(ownerTextVisibility);    		
		textOwner.setVisible(ownerTextVisibility);

		// START: name
    	textName.removeModifyListener(modifyListener);
    	textName.setText(item.getName());
    	if (item != null  
    			&& (item.isValueMediator() || item.isValueMediatorContainer()) 
    			&& item.getUmlElement() != null
    			&& !((EObject)item.getUmlElement()).eIsProxy() 
    			) {
    		textName.setEditable(true);		}
    	else {
    		textName.setEditable(false);
    	}
    	textName.addModifyListener(modifyListener);
		// END: required value client
    	
    	// type
    	String type = "";
    	final Element element = item.getUmlElement();
    	if (element instanceof Property) {
			if ( ((Property)element).getType() != null) {
				if (((Property)element).getType() instanceof PrimitiveType) {
					type = ((Property)element).getType().getName();
				}
				else {
					type = "<a>" + ((Property)element).getType().getName() + "</a>";
					textType.setToolTipText( ((Property)element).getType().getQualifiedName());
					textType.addListener(SWT.Selection, new Listener() {
					      public void handleEvent(Event event) {
					    	  EObject object = ((Property)element).getType();
					    	  locate(object); // locate in Papyrus
					      }
					    });
				}
				type = ((Property)element).getType().getName();
			}
		}
    	textType.setText(type);
    	
    	// owner 
    	String owner = "";
    	if (item.getUmlElement() != null &&  item.getUmlElement().getOwner() instanceof NamedElement) {
//    		owner = ((NamedElement)item.getUmlElement().getOwner()).getQualifiedName();
    		owner = "<a>" + ((NamedElement)item.getUmlElement().getOwner()).getName() + "</a>";
    		textOwner.setToolTipText(((NamedElement)item.getUmlElement().getOwner()).getQualifiedName());
    		textOwner.addListener(SWT.Selection, new Listener() {
			      public void handleEvent(Event event) {
			    	  EObject object = (NamedElement)item.getUmlElement().getOwner();
			    	  locate(object); // locate in Papyrus
			      }
			    });
		}
    	textOwner.setText(owner);
    }

    
    private void storeText(){
    	final NamedElement element = (NamedElement)item.getUmlElement();
		//########## storing start
//		TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
		ServicesRegistry serviceRegistry;
		TransactionalEditingDomain editingDomain = null;

		try {
			serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
			editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CompoundCommand cc = new CompoundCommand();
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				String newName = textName.getText();
				element.setName(newName);
				item.setName(newName);
				viewer.update(item, null);
			}
		};
		cc.append(command);
		if (editingDomain != null) {
			editingDomain.getCommandStack().execute(cc);
		}
		else {
			System.err.println("Cannot access the editing domain ...");
		}
		//########## storing end
    }
    
    private void setValueClient_isRequired(final Boolean isRequired){
    	Dependency dependencyToClient = null;
    	Element element = item.getUmlElement();
		
		if (item.isValueClient() && element instanceof NamedElement) {
			TreeObject mediator = TreeUtls.getNearestMediator(item);
			Element mediatorElement = mediator.getUmlElement();

			if (mediatorElement instanceof NamedElement) {
				EList<Dependency> depList = TreeUtls.getMediatorDependency((NamedElement)mediatorElement, (NamedElement)element, Constants.stereotypeQName_ProvidesValueFor);
				if (depList != null && depList.size() > 0 && depList.size() == 1) {
					dependencyToClient = depList.get(0);
				}
				else {
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Value Mediator Inconsitency", "There are multiple links form the Mediator '" + mediator.getName() + "' " +
							" to the Value Client '" + item.getName() + "'. \n\nThis is not allowed."  );
				}
			}
		}
		
		final NamedElement dependency = dependencyToClient;
		if (dependency != null) {
			
			//########## storing start
//			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			ServicesRegistry serviceRegistry;
			TransactionalEditingDomain editingDomain = null;

			try {
				serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
				editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);

			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					Stereotype s_providesValueFor = dependency.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor);
					if (s_providesValueFor != null) {
						dependency.setValue(s_providesValueFor, Constants.propertyName_isRequired, isRequired);
						item.setValueClient_required(isRequired);
					}
				}
			};
			cc.append(command);
			if (editingDomain != null) {
				editingDomain.getCommandStack().execute(cc);
			}
			else {
				System.err.println("Cannot access the editing domain ...");
			}

		}
    }
    
    private void locate(Object object){
		if (object instanceof EObject) {
			
			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELEXPLORER);

			ModelExplorerPageBookView modelExplorerPageBookView = null;
			if (view instanceof ModelExplorerPageBookView) {
				modelExplorerPageBookView = (ModelExplorerPageBookView)view;
			}
			CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
			List<Object> items = new ArrayList<Object>();
//			items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
			items.add((EObject) object);
			ModelExplorerView.reveal(items, modelExplorerView);
			
//			modelExplorerView.setSelection(new StructuredSelection(items), true);
      	}
	}

}

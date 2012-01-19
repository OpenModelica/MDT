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

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeBuilder;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeParent;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;

public class ElementSelectionDialog extends Dialog {
	protected Shell sShell = null; 
	protected Button bttOK = null;
	protected Button bttCancel = null;

	private int value = 0;
	
	protected Label message;
	protected Group valueMediatorGroup;
	protected Label vmIcon;
	protected Label vmName;
	protected Group selectedElementGroup;
	protected Label seIcon;
	protected Label seName;

	// Constants 
	protected static final Image errorIcon = SWTResourceManager.getImage(ElementSelectionDialog.class, "/org/eclipse/jface/dialogs/images/message_error.gif");
	protected static final Image propertyIcon = SWTResourceManager.getImage(ElementSelectionDialog.class, "/icons/Property.gif");
	protected static final Image valueMediatorIcon = SWTResourceManager.getImage(ElementSelectionDialog.class, "/icons/valueMediator.png");

	private org.openmodelica.modelicaml.common.instantiation.TreeObject selectedInstantiationTreeObject = null;
	protected EObject selectedElement = null;
//	private Object preSelectedElement = null;
	
	TreeParent valueMediatorTreeItem = null; 
	protected TreeViewer viewer = null;

//	private List<String> listOfAllowedMetaClassNames = new ArrayList<String>();
	protected String title = "";
	protected Image image = null;
	protected int mode;
	
	protected TreeBuilder treeBuilder;
	
	public ElementSelectionDialog(	Shell parent, 
									Image image,
									String title, 
									String message, 
									List<String> listOfAllowedMetaClassNames, 
									TreeParent valueMediatorTreeItem, 
									TreeViewer viewer, 
									int mode,
									Object preSelectedElement, 
									TreeBuilder treeBuilder
									) {
		super(parent);

//		this.listOfAllowedMetaClassNames = listOfAllowedMetaClassNames;
		this.valueMediatorTreeItem = valueMediatorTreeItem;
		this.viewer = viewer;
		this.mode = mode;
		
		// set pre-selected element
//		this.preSelectedElement = preSelectedElement;
		if (adaptSelectedElement(preSelectedElement) instanceof EObject) {
			this.selectedElement = adaptSelectedElement(preSelectedElement);	
		}
		if (preSelectedElement instanceof org.openmodelica.modelicaml.common.instantiation.TreeObject) {
			selectedInstantiationTreeObject = (org.openmodelica.modelicaml.common.instantiation.TreeObject) preSelectedElement;
		}

		this.treeBuilder = treeBuilder;
		
		this.title = title;
		this.image = image;
		
		// create shell
		createSShell();
		
		// set the message text
		this.message.setText(message);
		
		// set the value mediator icon and text. 
		String typeString = "Not Defined!";
		String ownerString = "";
		if (valueMediatorTreeItem.getUmlElement().getOwner() instanceof NamedElement) {
			ownerString = "owner(" + ((NamedElement)valueMediatorTreeItem.getUmlElement().getOwner()).getName() + ")";
		}
		if (valueMediatorTreeItem.getUmlElement() != null 
				&& valueMediatorTreeItem.getUmlElement() instanceof Property  
				&& ((Property)valueMediatorTreeItem.getUmlElement()).getType() != null ) {
			typeString = ((Property)valueMediatorTreeItem.getUmlElement()).getType().getName().replaceFirst("Modelica", "");
		}
		vmName.setText(valueMediatorTreeItem.getName() + "   -   type(" + typeString + ")" + ", " + ownerString);
		vmIcon.setImage(valueMediatorIcon);
		
		// set default values for element selection
		seName.setText("Click on a model element ...");
		seIcon.setImage(errorIcon); // nothing is selected yet.
		
		Label lblNote = new Label(sShell, SWT.NONE);
		lblNote.setFont(SWTResourceManager.getFont("Tahoma", 7, SWT.NORMAL));
		lblNote.setBounds(10, 168, 517, 17);
		lblNote.setText("Note, after selection, the bindings view should be reloaded in order to reflect the selection in other tree nodes.");

		if (mode == Constants.MODE_ADD_PROVIDER) {
			selectedElementGroup.setText("Value Provider"); 
		}
		else if (mode == Constants.MODE_ADD_CLIENT) {
			selectedElementGroup.setText("Value Client");
		}
		else {
			selectedElementGroup.setText("Selected Element");
		}
		
		// validate the selection and set the enablement of the ok button. 
		validate();
		
		// add the selection listener
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);

	}

	protected ISelectionListener selectionListener = new ISelectionListener() {
	        public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
	        if (sourcepart != ElementSelectionDialog.this && selection instanceof IStructuredSelection) {
	
	        	if (getCurrentSelections() != null && getCurrentSelections().size() > 0 ) {
					selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
					if (getCurrentSelections().get(0) instanceof org.openmodelica.modelicaml.common.instantiation.TreeObject) {
						selectedInstantiationTreeObject = (org.openmodelica.modelicaml.common.instantiation.TreeObject) getCurrentSelections().get(0); 
					}
				}
	        	// validate the selection and set the enablement of the ok button.
	        	validate();
	        }
	    }
	};
	   
	public void validate(){
		if (	selectedElement instanceof Property
				&& ((Property)selectedElement).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) == null
				&& selectedElement != valueMediatorTreeItem.getUmlElement()
				) {

			String typeString = "Not Defined!";
			String ownerString = "";
			if (((Element) selectedElement).getOwner() instanceof NamedElement) {
				NamedElement owner = (NamedElement) ( (NamedElement) selectedElement).getOwner();
				ownerString = "owner(" + owner.getName() + ")";
			}
			if (selectedElement instanceof Property  
					&& ((Property)selectedElement).getType() != null ) {
				typeString = ((Property)selectedElement).getType().getName().replaceFirst("Modelica", "");
			}
			seName.setText( ((Property)selectedElement).getName() + "   -   type(" + typeString + ")" + ", " + ownerString);
			seIcon.setImage(propertyIcon);

			// enable the OK button
			bttOK.setEnabled(true);
			
		} else {
			// reset  
			seName.setText("");
			seIcon.setImage(errorIcon);
			
			if (selectedElement == valueMediatorTreeItem.getUmlElement()) { // link to its self
				String text = "It is not allowed to link an element with itself.";
				seName.setText(text);
			}
			else if ( selectedElement instanceof Property
					&& ((Property)selectedElement).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null){
				String text = "It is not allowed to link a Value Mediator to another Value Mediator.";
				seName.setText(text);
			}
			else if (selectedElement != null) {
				String text = selectedElement.eClass().getName() + " is not allowed!";
				seName.setText(text);
			}
			else {
				String text = "Not a valid selection";
				seName.setText(text);
			}

			// disable the OK button
			bttOK.setEnabled(false);
		}
	}
	
	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		// SWT.APPLICATION_MODAL, SWT.SYSTEM_MODAL do not work, 
		// SWT.PRIMARY_MODAL, SWT.MODELESS work for the window to not block the underlying Eclipse window
		sShell = new Shell(getParentShell(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP | SWT.SHELL_TRIM);
		
		sShell.setText(this.title);
		sShell.setImage(this.image); 
		
//		sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		sShell.setSize(545, 258);
		
		sShell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);
			}
		});
		
		Shell shell = sShell;
		
		message = new Label(shell, SWT.NONE);
		message.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		message.setBounds(20, 10, 507, 17);
		message.setText("Click on the element to be associated.");

		Composite messageBg = new Composite(shell, SWT.NONE);
		messageBg.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		messageBg.setBounds(0, 0, 537, 34);
		
		valueMediatorGroup = new Group(shell, SWT.NONE);
		valueMediatorGroup.setText("Value Mediator");
		valueMediatorGroup.setBounds(10, 40, 517, 55);
		
		vmName = new Label(valueMediatorGroup, SWT.NONE);
		vmName.setBounds(32, 23, 475, 13);
		
		vmIcon = new Label(valueMediatorGroup, SWT.NONE);
		vmIcon.setBounds(10, 23, 16, 16);
		
		selectedElementGroup = new Group(shell, SWT.NONE);
		selectedElementGroup.setText("Value Client");
		selectedElementGroup.setBounds(10, 101, 517, 55);
		
		seName = new Label(selectedElementGroup, SWT.NONE);
		seName.setBounds(32, 23, 475, 13);
		
		seIcon = new Label(selectedElementGroup, SWT.NONE);
		seIcon.setBounds(10, 23, 16, 16);
		
		bttOK = new Button(sShell, SWT.NONE);
		bttOK.setBounds(385, 191, 68, 23);
		bttOK.setText("OK");
		
		bttOK.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				setValue(SWT.OK);
				
				if (selectedElement instanceof NamedElement) {
					// create a dependency from value mediator to client/provider
					boolean newElementCreated = TreeUtls.storeMediatorReference(valueMediatorTreeItem.getUmlElement(), selectedElement, mode); 
					
					// only update the tree if the new element was created in the UML model
					if (newElementCreated) {
						TreeObject item = new TreeParent( ((NamedElement)selectedElement).getName());
						item.setUmlElement((NamedElement)selectedElement);
						
						TreeObject[] children = valueMediatorTreeItem.getChildren();
						
						for (int i = 0; i < children.length; i++) {
							if (mode == Constants.MODE_ADD_CLIENT && children[i].isValueClientsNode() ) {

								// add the new item
								((TreeParent) children[i]).addChild(item);
								item.setIsValueClient();
								if (selectedInstantiationTreeObject != null ) {
									item.setInstantiationTreeObject(selectedInstantiationTreeObject);
								}

								viewer.add(children[i], item);
								viewer.setSelection(new StructuredSelection(item), true);
								
							}
							else if (mode == Constants.MODE_ADD_PROVIDER && children[i].isValueProvidersNode()) {
								// add the new item
								((TreeParent) children[i]).addChild(item);
								item.setIsValueProvider();

								if (selectedInstantiationTreeObject != null ) {
									item.setInstantiationTreeObject(selectedInstantiationTreeObject);
								}
								
								viewer.add(children[i], item);
								viewer.setSelection(new StructuredSelection(item), true);
							}
						}
						
						viewer.refresh();
						sShell.dispose();
					}
				}
			}
		});
		
		bttCancel = new Button(sShell, SWT.NONE);
		bttCancel.setBounds(459, 191, 68, 23);
		bttCancel.setText("Cancel");
		bttCancel.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				setValue(SWT.CANCEL);
				sShell.dispose();
			}
		});
	}
	
	
	public int open() {
		this.sShell.open();
		return getValue();
	}

	
	@SuppressWarnings("unchecked")
	private List<Object> getCurrentSelections() {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			return structuredSelection.toList();
		}
		return null;
	}
	
	
	protected EObject adaptSelectedElement( Object selection ) {
		EObject eObject = null;
		if(selection != null) {
			
			if (selection instanceof org.openmodelica.modelicaml.common.instantiation.TreeParent) { // this is an object from components tree view plugin
				return ((org.openmodelica.modelicaml.common.instantiation.TreeParent)selection).getProperty();
			}
			if(selection instanceof IAdaptable) {
				selection = ((IAdaptable)selection).getAdapter(EObject.class);
			}
			Object businessObject = BusinessModelResolver.getInstance().getBusinessModel(selection);
			if(businessObject instanceof EObject) {
				eObject = (EObject)businessObject;
			}
		}
		return eObject;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public EObject getSelectedElement() {
		return selectedElement;
	}

	public void setSelectedElement(EObject selectedElement) {
		this.selectedElement = selectedElement;
	}
}

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
package org.openmodelica.modelicaml.development.profile.update.helper.dialogs;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.infra.emf.utils.BusinessModelResolver;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.development.profile.update.helper.impl.StereotypeApplier;
import org.openmodelica.modelicaml.development.profile.update.helper.impl.StereotypeUnApplier;


public class ElementSelectionDialog extends Dialog {
//	protected Shell sShell = null; 
	private Shell sShell_1;
	protected Button bttOK = null;
	protected Button bttCancel = null;

	private int value = 0;
	
	protected Label message;
	protected Group selectedElementGroup;
	protected Label seIcon;
	protected Label seName;

	// Constants 
	protected static final Image errorIcon = SWTResourceManager.getImage(ElementSelectionDialog.class, "/org/eclipse/jface/dialogs/images/message_error.gif");
	protected static final Image elementIcon = SWTResourceManager.getImage(ElementSelectionDialog.class, "/icons/Class.gif");
	protected static final Image selectIcon = SWTResourceManager.getImage(ElementSelectionDialog.class, "/icons/selectItem.gif");

	protected EObject selectedElement = null;
	
	protected String title = "";
	protected Image image = null;
	
	protected int mode;
	
	private Button btnApplystereotypes;
	private Button btnUnapplystereotypes;

	// options
	protected boolean applyStereotypes = true;
	protected boolean unapplyStereotypes = false;
	
	
	public ElementSelectionDialog(	Shell parent, 
									Image image,
									String title, 
									String message,
									Element preselectedElement
									) {
		super(parent);
		
		this.title = title;
		this.image = image;
		
		// create shell
		createSShell();
		
		// set the message text
		this.message.setText(message);
		
		this.selectedElement = preselectedElement;
		
		// set default values for element selection
		seName.setText("Click on a model element ...");
		seIcon.setImage(selectIcon);
		
		Group grpOptions = new Group(sShell_1, SWT.NONE);
		grpOptions.setText("Options");
		grpOptions.setBounds(10, 101, 464, 77);
		
		btnApplystereotypes = new Button(grpOptions, SWT.RADIO);
		btnApplystereotypes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnApplystereotypes.getSelection()) {
					applyStereotypes = true;
				}
				else {
					applyStereotypes = false;
				}
			}
		});
		btnApplystereotypes.setSelection(true);
		btnApplystereotypes.setBounds(10, 28, 444, 16);
		btnApplystereotypes.setText("apply ModelicaML stereotypes");
		
		btnUnapplystereotypes = new Button(grpOptions, SWT.RADIO);
		btnUnapplystereotypes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnUnapplystereotypes.getSelection()) {
					unapplyStereotypes = true;
				}
				else {
					unapplyStereotypes = false;
				}
			}		});
		btnUnapplystereotypes.setBounds(10, 50, 444, 16);
		btnUnapplystereotypes.setText("unapply ModelicaML stereotypes");

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
				}
	        	// validate the selection and set the enablement of the ok button.
	        	validate();
	        }
	    }
	};

	
	private String getMetaClassName(EObject selectedElement){
		return selectedElement.eClass().getName() + ": ";
	}
	
	public void validate(){
		if ( selectedElement instanceof Class || selectedElement instanceof Package) {
			
			String ownerString = "";
			
			if (((Element) selectedElement).getOwner() instanceof NamedElement) {
//				ownerString = "owner(" + ModelicaMLServices.getName( ((NamedElement) selectedElement).getOwner()) + ")";
			}
			seName.setText( getMetaClassName(selectedElement) + ((NamedElement)selectedElement).getName() + "     " + ownerString);
			seIcon.setImage(ModelicaMLServices.getImage((Element) selectedElement));

			// enable the OK button
			bttOK.setEnabled(true);
			
		} else if (selectedElement == null) {
			// reset  
			seName.setText("");
			seIcon.setImage(selectIcon);
			
			String text = "Select a model ...";
			seName.setText(text);

			// disable the OK button
			bttOK.setEnabled(false);
		} 
		else {
			// reset  
			seName.setText("");
			seIcon.setImage(errorIcon);
			
			if (selectedElement != null) {
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
		sShell_1 = new Shell(getParentShell(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP | SWT.SHELL_TRIM );
		
		sShell_1.setText(this.title);
		sShell_1.setImage(this.image); 
		
//		sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		sShell_1.setSize(500, 258);
		
		sShell_1.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);
			}
		});
		
		Shell shell = sShell_1;
		
		message = new Label(shell, SWT.NONE);
		message.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		message.setBounds(20, 10, 464, 17);
		message.setText("In the Model Explorer click on the design model to be analysed.");

		Composite messageBg = new Composite(shell, SWT.NONE);
		messageBg.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		messageBg.setBounds(0, 0, 484, 34);
		
		selectedElementGroup = new Group(shell, SWT.NONE);
		selectedElementGroup.setText("Selected Element");
		selectedElementGroup.setBounds(10, 40, 464, 55);
		
		seName = new Label(selectedElementGroup, SWT.NONE);
		seName.setBounds(32, 23, 422, 22);
		
		seIcon = new Label(selectedElementGroup, SWT.NONE);
		seIcon.setBounds(10, 23, 16, 16);
		
		bttOK = new Button(sShell_1, SWT.NONE);
		bttOK.setBounds(332, 187, 68, 23);
		bttOK.setText("OK");
		
		bttOK.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {

			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				setValue(SWT.OK);
				
				if (selectedElement instanceof NamedElement) {
					
					// close the dialog
					sShell_1.dispose();
					
					if (applyStereotypes) {
						StereotypeApplier sa = new StereotypeApplier();
						sa.applyStereotypes(selectedElement);
						
						if (sa.getUnhandledElements().size() > 0) {
							// report 
							ResultsUnhandledElementsDialogHTML results = new ResultsUnhandledElementsDialogHTML(
									new Shell(), 
									"Unhandled Elements",
									SWTResourceManager.getImage(ElementSelectionDialog.class,"/icons/error_log.gif"), 
									sa.getUnhandledElements()
									);
							
							results.open();
						}
					}
					else if (unapplyStereotypes) {
						StereotypeUnApplier su = new StereotypeUnApplier();
						su.unApplyStereotypes(selectedElement);
					}
				}
			}
		});
		
		bttCancel = new Button(sShell_1, SWT.NONE);
		bttCancel.setBounds(406, 187, 68, 23);
		bttCancel.setText("Cancel");
		bttCancel.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				setValue(SWT.CANCEL);
				sShell_1.dispose();
			}
		});
	}
	
	
	public int open() {
		this.sShell_1.open();
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
	
	public void dispose(){
		// close the dialog
		sShell_1.dispose();
	}
}

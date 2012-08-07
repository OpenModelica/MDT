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
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.papyrus.infra.core.utils.BusinessModelResolver;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
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
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeUtls;
import org.openmodelica.modelicaml.common.services.PapyrusServices;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.eclipse.swt.layout.GridData;

public class NOT_USED_SelectValueClientOrProviderDialog extends Dialog {
	protected Shell sShell; 
	protected Button bttOK = null;
	protected Button bttCancel = null;

	private int value = 0;
	protected Label message;
	
	private Group groupSelectedElement;
	private Label selectedElementIcon;
	private Label selectedElementIconName;
	private Group grpValueMediator;
	private Label lblValueMediatorName;
	private Label lblValueMediatorIcon;
	private Group groupElementToBeAdded;
	private Label lblElementToBeAddedIconName;
	private Label lblElementToBeAddedIcon;
	private CCombo comboValueMediator;
	
	// Constants 
	protected static final Image errorIcon = SWTResourceManager.getImage(NOT_USED_SelectValueClientOrProviderDialog.class, "/org/eclipse/jface/dialogs/images/message_error.gif");
	protected static final Image propertyIcon = SWTResourceManager.getImage(NOT_USED_SelectValueClientOrProviderDialog.class, "/icons/Property.gif");
	protected static final Image valueMediatorIcon = SWTResourceManager.getImage(NOT_USED_SelectValueClientOrProviderDialog.class, "/icons/valueMediator.png");

	protected EObject selectedElement = null;

//	private List<String> listOfAllowedMetaClassNames = new ArrayList<String>();
	protected String title = "";
	protected Image image = null;
	protected int mode;
	private TreeViewer viewer;
	private TreeObject selectedItem;
	
	EList<Element> valueMediators = new BasicEList<Element>(); 
	private EObject selectedValueMediator = null;
	private HashMap<String,Object> valueMediatorComboSelectionToUMLElementMap = new HashMap<String,Object>(); 

	public final static int MODE_ADD_CLIENT = 0;
	public final static int MODE_ADD_PROVIDER = 1;
	
	private Action actionEditModification;
	private Action actionLinkWithEditor;
	
	private boolean actionLinkWithEditorLastStatus;
	
	public NOT_USED_SelectValueClientOrProviderDialog(	Shell parent, 
									Image image,
									String title, 
									String message, 
									List<String> listOfAllowedMetaClassNames, 
									TreeObject selectedItem, 
									TreeViewer viewer, 
									int mode,
									Action actionEditModification,
									Action actionLinkWithEditor) {
		super(parent);

//		this.listOfAllowedMetaClassNames = listOfAllowedMetaClassNames;
		
		this.viewer = viewer;
		
		this.actionEditModification = actionEditModification;  // used to start the edit modification dialog after element selection
		this.actionLinkWithEditor = actionLinkWithEditor;
		// remember the last status of the action
		actionLinkWithEditorLastStatus = this.actionLinkWithEditor.isChecked();
		// uncheck the link with editor action in order to avoid that the components tree changes because while the user selects an element
		actionLinkWithEditor.setChecked(false);
		
		this.selectedItem = selectedItem;
		if (this.selectedItem != null) {
			this.selectedElement = selectedItem.getUmlElement();
		}
		
		this.mode = mode;
		this.title = title;
		this.image = image;
		
		// create shell
		createSShell();
		
		// set the message text
		this.message.setText(message);
		
		// collect data
		setData();
		
		// set labels and icons
		setTextAndIcons();
		
		// add the selection listener
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);

	}
	private void setData(){
		if (selectedElement instanceof Element) {
			// a provider should be added to added to the client then get the mediators from client
			if (mode == MODE_ADD_PROVIDER) { 
//				valueMediators.addAll(TreeUtls.getValueMediators((Element)selectedElement, Constants.stereotypeQName_ValueClient, Constants.stereotypeQName_ValueClient_obtainsValueFrom));
			}
			// a client should be added to added to the provider then get the mediators from provider
			else if (mode == MODE_ADD_CLIENT) {
//				valueMediators.addAll(TreeUtls.getValueMediators((Element)selectedElement, Constants.stereotypeQName_ValueProvider, Constants.stereotypeQName_ValueProvider_providesValueFor));	
			}
			
			// set the selected valueMediator by default to the first one
			if (valueMediators.size() > 0 ) {
				selectedValueMediator = valueMediators.get(0); 
			}
		}
		else {
			System.err.println("Cannot adopt the selected item to an UML Element");
		}
	}

	
	private void setTextAndIcons(){
		// set groups
		if (mode == MODE_ADD_PROVIDER) {
			groupSelectedElement.setText("Value Client");
			groupElementToBeAdded.setText("Value Provider");
		}
		else if (mode == MODE_ADD_CLIENT) {
			groupSelectedElement.setText("Value Provider");
			groupElementToBeAdded.setText("Value Client");
		}
		else {
			groupSelectedElement.setText("???");
		}

		
		// set the selected element icon and text. 
		String nameString;
		String typeString;
		String ownerString;
		
		nameString = getNameString(selectedItem.getUmlElement());
		typeString = getTypeString(selectedItem.getUmlElement());
		ownerString = getOwnerString(selectedItem.getUmlElement());
		selectedElementIconName.setText( getNameString(nameString, typeString, ownerString) );
		selectedElementIcon.setImage( getValueClientOrProviderIcon() );
		
		// value mediator icon
		if (valueMediators.size() > 0 ) { // there must be a mediator for the selected element 
			if (valueMediators.size() > 1 ) {
				
				// hide the lable
				lblValueMediatorName.setVisible(false);
				
				// set the combo items
				EList<String> valueMediatorItems = new BasicEList<String>();
				for (int i = 0; i < valueMediators.size(); i++) {
					Element element = valueMediators.get(i);
					if (element instanceof NamedElement) {
						
						nameString = getNameString(element);
						typeString = getTypeString(element);
						ownerString = getOwnerString(element);
						
						String key = getNameString(nameString, typeString, ownerString);
						valueMediatorItems.add(key);
						valueMediatorComboSelectionToUMLElementMap.put(key, element);
					}
				}
				comboValueMediator.setItems(valueMediatorItems.toArray(new String[valueMediators.size()]));
				comboValueMediator.setText(comboValueMediator.getItems()[0]);
				
			}
			else{
				Element firstMediator = valueMediators.get(0);
				selectedValueMediator = firstMediator;
				if (firstMediator instanceof NamedElement) {
					
					// get type and owner 
					nameString = getNameString(firstMediator);
					typeString = getTypeString(firstMediator);
					ownerString = getOwnerString(firstMediator);
					lblValueMediatorName.setText(getNameString( nameString, typeString, ownerString));
				}
				
				// hide the combo 
				comboValueMediator.setVisible(false);
			}
			lblValueMediatorIcon.setImage(valueMediatorIcon);
		}
		
		// set default values for element selection
		lblElementToBeAddedIconName.setText("Click on a model element ...");
		lblElementToBeAddedIcon.setImage(errorIcon);

	}
	
	
	
	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		// SWT.APPLICATION_MODAL, SWT.SYSTEM_MODAL do not work, 
		// SWT.PRIMARY_MODAL, SWT.MODELESS work
		sShell = new Shell(getParentShell(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP | SWT.SHELL_TRIM);
		
		sShell.setText(this.title);
		sShell.setImage(this.image); 
		
//		sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		sShell.setSize(543, 295);
		sShell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);
			}
		});
		GridLayout gl_sShell_1 = new GridLayout(1, false);
		gl_sShell_1.marginHeight = 0;
		gl_sShell_1.marginWidth = 0;
		sShell.setLayout(gl_sShell_1);

		Composite messageBg = new Composite(sShell, SWT.NONE);
		GridData gd_messageBg = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_messageBg.heightHint = 35;
		gd_messageBg.widthHint = 537;
		messageBg.setLayoutData(gd_messageBg);
		messageBg.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		messageBg.setBounds(0, 0, 537, 34);
		
		message = new Label(messageBg, SWT.NONE);
		message.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		message.setBounds(10, 10, 511, 13);
		message.setText("Click on the element to be associated.");
		
		Composite groupsComposite = new Composite(sShell, SWT.NONE);
		groupsComposite.setLayout(new GridLayout(1, false));
		groupsComposite.setBounds(0, 35, 544, 274);
		
		groupSelectedElement = new Group(groupsComposite, SWT.NONE);
		groupSelectedElement.setText("Value Client or Provider");
		
		selectedElementIconName = new Label(groupSelectedElement, SWT.NONE);
		selectedElementIconName.setText("asdfasdf");
		selectedElementIconName.setBounds(32, 23, 486, 13);
		
		selectedElementIcon = new Label(groupSelectedElement, SWT.NONE);
//		selectedElementIcon.setImage(SWTResourceManager.getImage(ValueClientOrProviderSelectionDialog.class, "/icons/full/etool16/delete.gif"));
		selectedElementIcon.setBounds(10, 23, 16, 16);
		
		grpValueMediator = new Group(groupsComposite, SWT.NONE);
		grpValueMediator.setText("Value Mediator");
		
		lblValueMediatorName = new Label(grpValueMediator, SWT.NONE);
		lblValueMediatorName.setBounds(32, 23, 486, 13);
		lblValueMediatorName.setText("asdfasdf");
		
		lblValueMediatorIcon = new Label(grpValueMediator, SWT.NONE);
		lblValueMediatorIcon.setBounds(10, 23, 16, 16);
		
		comboValueMediator = new CCombo(grpValueMediator, SWT.NONE);
		comboValueMediator.setEditable(false);
		comboValueMediator.setBackground(new Color(null, new RGB(255,255,255)));
		comboValueMediator.setBounds(32, 20, 486, 20);
		
		groupElementToBeAdded = new Group(groupsComposite, SWT.NONE);
		groupElementToBeAdded.setText("Value Client or Provider");
		
		lblElementToBeAddedIconName = new Label(groupElementToBeAdded, SWT.NONE);
		lblElementToBeAddedIconName.setText("asdfasdf");
		lblElementToBeAddedIconName.setBounds(32, 23, 486, 13);
		
		lblElementToBeAddedIcon = new Label(groupElementToBeAdded, SWT.NONE);
//		lblElementToBeAddedIcon.setImage(SWTResourceManager.getImage(ValueClientOrProviderSelectionDialog.class, "/icons/full/etool16/delete.gif"));
		lblElementToBeAddedIcon.setBounds(10, 23, 16, 16);

		comboValueMediator.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = comboValueMediator.getSelectionIndex();
				String key = comboValueMediator.getItem(index);
				selectedValueMediator = (EObject) valueMediatorComboSelectionToUMLElementMap.get(key);
			}
		});
		
		Composite composite = new Composite(sShell, SWT.NONE);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.widthHint = 533;
		composite.setLayoutData(gd_composite);
		
		bttOK = new Button(composite, SWT.NONE);
		bttOK.setBounds(388, 0, 71, 23);
		bttOK.setText("OK");
		
		bttCancel = new Button(composite, SWT.NONE);
		bttCancel.setBounds(467, 0, 66, 23);
		bttCancel.setText("Cancel");
		bttCancel.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {

				setValue(SWT.CANCEL);
				
//				sShell.dispose();
				close(sShell);
				
				viewer.setSelection(new StructuredSelection(selectedItem));
				
			}
		});
		
		bttOK.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				
				setValue(SWT.OK);
				
				if (elementToBeSet instanceof NamedElement) {
					addValueToStereotypeProperty_List();
				}
//				sShell.dispose();
				close(sShell);
				
//				viewer.refresh();
//				viewer.expandToLevel(2);

				// set selection and open Edit modification dialog
				if (mode == MODE_ADD_PROVIDER) {
					// check if the selection was successful and open the edit modification dialog
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection) selection).getFirstElement();
					if (obj instanceof TreeObject) {
						TreeObject item = (TreeObject)obj;
						if (item == selectedItem) {
							actionEditModification.run();
						}
					}
				}
			}
		});
		
	}
	
	
	private void close(Shell shell){
		shell.dispose();

		// set the last status of the action
		actionLinkWithEditor.setChecked(actionLinkWithEditorLastStatus);
		
		// set the selection to the item that was selected in the beginning  
		viewer.setSelection(new StructuredSelection(selectedItem));
		
	}
	
	private Image getValueClientOrProviderIcon(){
		if (mode == MODE_ADD_CLIENT) {
			return SWTResourceManager.getImage(NOT_USED_SelectValueClientOrProviderDialog.class, "/icons/addValueClient.png");
		}
		if (mode == MODE_ADD_PROVIDER) {
			return SWTResourceManager.getImage(NOT_USED_SelectValueClientOrProviderDialog.class, "/icons/addValueProviders.png");
		}
		return null;
	}
	
	
	protected ISelectionListener selectionListener = new ISelectionListener() {
	        public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
	        if (sourcepart != NOT_USED_SelectValueClientOrProviderDialog.this &&
	            selection instanceof IStructuredSelection) {
	
	        	if (getCurrentSelections() != null && getCurrentSelections().size() > 0 ) {
	        		elementToBeSet = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
				}
	        	
				if (	elementToBeSet instanceof Property
						&& ((Property)elementToBeSet).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) == null
						&& elementToBeSet != selectedElement
						) {

					String nameString  = getNameString((Element) elementToBeSet);
					String typeString = getTypeString((Element) elementToBeSet);
					String ownerString = getOwnerString((Element) elementToBeSet);
					lblElementToBeAddedIconName.setText( getNameString(nameString, typeString, ownerString) );
					lblElementToBeAddedIcon.setImage(propertyIcon);

					// enable the OK button
					bttOK.setEnabled(true);
					
				} else {
					// reset  
					lblElementToBeAddedIconName.setText("");
					lblElementToBeAddedIcon.setImage(errorIcon);
					
					if (elementToBeSet == selectedElement) { // link to its self
						String text = "It is not allowed to link an element with itself.";
						lblElementToBeAddedIconName.setText(text);
					}
					else if ( elementToBeSet instanceof Property
							&& ((Property)elementToBeSet).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null){
						String text = "It is not allowed to link a Value Mediator to another Value Mediator.";
						lblElementToBeAddedIconName.setText(text);
					}
					else if (elementToBeSet != null) {
						String text = elementToBeSet.eClass().getName() + " is not allowed!";
						lblElementToBeAddedIconName.setText(text);
					}
					else {
						String text = "Not a valid selection";
						lblElementToBeAddedIconName.setText(text);
					}

					// disable the OK button
					bttOK.setEnabled(false);
				}
	        }
	    }
	};
	private EObject elementToBeSet;
	
	private Stereotype getStereotype(){
		String stereotypeQName = "";
		Stereotype stereotype = null;
		
		if (this.mode == MODE_ADD_CLIENT) {
			stereotypeQName = Constants.stereotypeQName_ValueClient;
		}
		else if (this.mode == MODE_ADD_PROVIDER) {
			stereotypeQName = Constants.stereotypeQName_ValueProvider;
		}
		
		if (this.elementToBeSet instanceof NamedElement) {
			// get stereotype
			stereotype = ((NamedElement)this.elementToBeSet).getAppliedStereotype(stereotypeQName);
			
			// apply stereotype if not yet applied
			if (stereotype == null) {
				final Stereotype s = ((NamedElement)this.elementToBeSet).getApplicableStereotype(stereotypeQName);
				if (s != null) {
					
					//########## storing start
					TransactionalEditingDomain editingDomain = PapyrusServices.getPapyrusEditingDomain();
					CompoundCommand cc = new CompoundCommand();
					Command command = new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							((NamedElement)elementToBeSet).applyStereotype(s);
						}
					};
					cc.append(command);
					editingDomain.getCommandStack().execute(cc);
					
					
					// get stereotype
					stereotype = ((NamedElement)this.elementToBeSet).getAppliedStereotype(stereotypeQName); 
				}
			}
		}
		return stereotype;
	}

	private String getStereotypePropertyName(){
		String stereotypeProperty_name = null;
		if (this.mode == MODE_ADD_CLIENT) {
			stereotypeProperty_name = Constants.stereotypeQName_ValueClient_obtainsValueFrom;
		}
		else if (this.mode == MODE_ADD_PROVIDER) {
			stereotypeProperty_name = Constants.stereotypeQName_ValueProvider_providesValueFor;
		}
		return stereotypeProperty_name;
	}
	
	@SuppressWarnings("rawtypes")
	private boolean containsObject(EList list, EObject eObject){
		if (list instanceof EList) {
			for (Object object : (EList)list) {
				if (object instanceof EObject) {
//					if (((EObject)object).eCrossReferences().get(0) == eObject) {
					if (UMLUtil.getBaseElement((EObject)object) == eObject) {
						return true;
					}
				}	
			}
		}
		return false;
	}
	
	void addValueToStereotypeProperty_List(){
		
		if (this.selectedValueMediator != null && this.elementToBeSet instanceof NamedElement) {
			// get stereotype
			final Stereotype stereotype = getStereotype();
			final String stereotypeProperty_name = getStereotypePropertyName();
			if ( stereotype != null && stereotypeProperty_name != null) {
				
				final Object list = ((NamedElement)this.elementToBeSet).getValue(stereotype, stereotypeProperty_name);
				
				if (list instanceof EList) {
//					########## storing start
					TransactionalEditingDomain editingDomain = PapyrusServices.getPapyrusEditingDomain();
					CompoundCommand cc = new CompoundCommand("Add value mediator reference");
					Command command = new RecordingCommand(editingDomain) {
						@SuppressWarnings({ "unchecked", "rawtypes" })
						@Override
						protected void doExecute() {
							if (!containsObject((EList) list, selectedValueMediator)) {
								
								// get the value mediator stereotype
								Stereotype valueMediatorStereotype = ((Element)selectedValueMediator).getAppliedStereotype(Constants.stereotypeQName_ValueMediator);
								
								// Important: use the getStereotypeApplication to get an EObject! 
								DynamicEObjectImpl eObject =(DynamicEObjectImpl) ((Element)selectedValueMediator).getStereotypeApplication(valueMediatorStereotype);
								
								// add value to the list
								((EList)list).add(eObject);
							}
						}
					};
					cc.append(command);
					editingDomain.getCommandStack().execute(cc);
				}
			}
		}
		else {
			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", "Could not finish the operation. No updates were performed.");
		}
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
	
	
	protected EObject adaptSelectedElement( Object selection) {
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
	
	private String getNameString(String nameString, String typeString, String ownerString) {
		return nameString + "   -   " + typeString + ", " + ownerString;
	}
	
	private String getTypeString(Element element){
		if (element instanceof Property) {
			Type pType = ((Property)element).getType();
			if (pType != null) {
				return "type(" + pType.getName().replaceFirst("Modelica", "") + ")";
			} 
		}
		return "ERROR: No type is defined!";
	}
	
	private String getNameString(Element element){
		if (element instanceof NamedElement) {
			return  ((NamedElement)element ).getName();
		} 
		return "";
	}
	
	private String getOwnerString(Element element){
		Element owner = element.getOwner();
		if (owner instanceof NamedElement) {
				return  "owner(" + ((NamedElement)owner).getName() + ")";
		} 
		return "";
	}
}

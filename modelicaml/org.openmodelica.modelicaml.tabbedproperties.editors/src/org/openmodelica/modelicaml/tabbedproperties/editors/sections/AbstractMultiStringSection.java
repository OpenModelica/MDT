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
package org.openmodelica.modelicaml.tabbedproperties.editors.sections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmt.modisco.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.core.utils.EditorUtils;
import org.eclipse.papyrus.diagram.common.editparts.IUMLEditPart;
import org.eclipse.papyrus.profile.ImageManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.tabbedproperties.editors.dialogs.EditorDialog;


// TODO: Auto-generated Javadoc
/**
 * The Class AbstractMultiStringSection.
 */
public abstract class AbstractMultiStringSection extends AbstractPropertySection {

	/**
	 * Instantiates a new abstract multi string section.
	 */
	public AbstractMultiStringSection() {
		super();
		// TODO Auto-generated constructor stub
	}

	/** The shell. */
	private Shell shell = null;
	
	/** The modification list. */
	protected org.eclipse.swt.widgets.List modificationList = null;
	
	/** The selected uml element. */
	protected Element selectedUmlElement;
	
	
	/** The i dialog. */
	private EditorDialog iDialog = null;
	
	/** The current string array. */
	protected String[] currentStringArray = {} ;
	
	/**
	 * Listener for the add button.
	 */
	protected Listlistener listlistener = new Listlistener();
	
	/**
	 * Listener for the add button.
	 */
	protected EditButtonlistener editButtonlistener = new EditButtonlistener();
	/**
	 * Listener for the add button.
	 */
	protected AddButtonlistener addButtonlistener = new AddButtonlistener();
	/**
	 * Listener for the add button.
	 */
	protected DeleteButtonlistener deleteButtonlistener = new DeleteButtonlistener();

	/** The stereotyp propertie name. */
	protected String stereotypPropertieName  = null;
	

	/**
	 * The TITLE.
	 */
	private String TITLE = stereotypPropertieName+" editor";

	/**
	 * The LABEL.
	 */
	private String LABEL = stereotypPropertieName+":";
	
	
	/** The button. */
	protected Button button = null;
	
	
	/**
	 * Button that adds an element.
	 */
	protected Button addButton;

	/**
	 * Button that removes an element.
	 */
	protected Button removeButton;

	/**
	 * button that moves the element up.
	 */
	protected Button upButton;

	/**
	 * button that moves the element down.
	 */
	protected Button downButton;
	
	/** The overview label. */
	protected CLabel overviewLabel;

	
	
	
	/**
	 * Creates the controls.
	 * 
	 * @param parent
	 *            the parent
	 * @param tabbedPropertySheetPage
	 *            the tabbed property sheet page
	 */
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		shell=parent.getShell();
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		FormData data;


		overviewLabel = getWidgetFactory().createCLabel(composite, stereotypPropertieName+":"); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.width = 300;
		data.top = new FormAttachment(modificationList, 0, SWT.TOP);
		overviewLabel.setLayoutData(data);

		this.modificationList = this.getWidgetFactory().createList(composite, SWT.READ_ONLY |SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.width = 300;
		data.top = new FormAttachment(overviewLabel, 0);
		data.height = 30;
		this.modificationList.setLayoutData(data);
		this.modificationList.addMouseListener(listlistener);
		
		addButtons(composite, data);
		
//		// ///////////////////////////////////////////////////////////////////////////
//		// Create and place button vertically on the left side
//		// Button : Add Element
//		// Button Delete Element
//
//		button = new Button(composite, SWT.PUSH);
//		button.setText("Edit");
//		data = new FormData();
//		data.left = new FormAttachment(modificationList, 5);
//		data.top = new FormAttachment(overviewLabel, 0);
//		button.setLayoutData(data);
//		button.addMouseListener(editButtonlistener);
//		
//		
//		addButton = new Button(composite, SWT.PUSH);
//		addButton.setVisible(true);
//		addButton.setImage(ImageManager.IMG_ADD);
//		addButton.setToolTipText("Add a new element");
//
//		data = new FormData();
//		data.top = new FormAttachment(overviewLabel, 0);
//		data.left = new FormAttachment(button, 5);
//		addButton.setLayoutData(data);
//		addButton.addMouseListener(addButtonlistener);
//		
//		
//		removeButton = new Button(composite, SWT.PUSH);
//		removeButton.setVisible(true);
//		removeButton.setImage(ImageManager.IMG_DELETE);
//		removeButton.setToolTipText("Delete selected element(s)");
//		data = new FormData();
//		data.top = new FormAttachment(overviewLabel, 0);
//		data.left = new FormAttachment(addButton, 5);
//		removeButton.setLayoutData(data);
//		removeButton.addMouseListener(deleteButtonlistener);
	}

	
	protected void addButtons(Composite composite, FormData data){
		// ///////////////////////////////////////////////////////////////////////////
		// Create and place button vertically on the left side
		// Button : Add Element
		// Button Delete Element

		button = new Button(composite, SWT.PUSH);
		button.setText("Edit");
		data = new FormData();
		data.left = new FormAttachment(modificationList, 5);
		data.top = new FormAttachment(overviewLabel, 0);
		button.setLayoutData(data);
		button.addMouseListener(editButtonlistener);
		
		
		addButton = new Button(composite, SWT.PUSH);
		addButton.setVisible(true);
		addButton.setImage(ImageManager.IMG_ADD);
		addButton.setToolTipText("Add a new element");

		data = new FormData();
		data.top = new FormAttachment(overviewLabel, 0);
		data.left = new FormAttachment(button, 5);
		addButton.setLayoutData(data);
		addButton.addMouseListener(addButtonlistener);
		
		
		removeButton = new Button(composite, SWT.PUSH);
		removeButton.setVisible(true);
		removeButton.setImage(ImageManager.IMG_DELETE);
		removeButton.setToolTipText("Delete selected element(s)");
		data = new FormData();
		data.top = new FormAttachment(overviewLabel, 0);
		data.left = new FormAttachment(addButton, 5);
		removeButton.setLayoutData(data);
		removeButton.addMouseListener(deleteButtonlistener);
	}
	
	/**
	 * Checks for attribute.
	 * 
	 * @param stereotype
	 *            the stereotype
	 * @param attribute
	 *            the attribute
	 * @return the boolean
	 */
	public static Boolean hasAttribute(Stereotype stereotype, String attribute){
		Boolean tmpbool = false;
		EList<Property> allAttributes = stereotype.getAllAttributes();
		Iterator<Property> iter = allAttributes.iterator();
		while (iter.hasNext()) {
			Property property = (Property) iter.next();
			if(property.getName().equals(attribute))
				tmpbool = true;
		}
		return tmpbool;
	}
	
	/*
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	@Override
	public void refresh() {
		TITLE = stereotypPropertieName+" editor";
		LABEL = stereotypPropertieName+":";
		if (!modificationList.isDisposed()) {
				Element element = this.selectedUmlElement;
				EList<Stereotype> sList = element.getAppliedStereotypes();
				
				if(!sList.isEmpty())			
				{
					Iterator<Stereotype> iterator = sList.iterator();
					while (iterator.hasNext()) {
					Stereotype stereotype = ((Stereotype)iterator.next());
						if(hasAttribute(stereotype, stereotypPropertieName)){
							if (button != null ) {
								button.setEnabled(true);								
							}
							if (addButton != null ) {
								addButton.setEnabled(true);	
							}
							if (removeButton != null ) {
								removeButton.setEnabled(true);	
							}
							modificationList.setEnabled(true);
							EList<?> list =(EList<?>) element.getValue(stereotype, stereotypPropertieName);
							ArrayList<String> stateList = new ArrayList<String>();
							if(!list.isEmpty())
							{
								if(list.get(0) instanceof State)
								{
									button.setEnabled(false);
									for (Object object : list) {
											stateList.add(((State)object).getQualifiedName());
										}
									currentStringArray = stateList.toArray(new String[list.size()]);;

								}
								else
								{
									currentStringArray = list.toArray(new String[list.size()]);
								}
							}
							else
							{
								currentStringArray = null;
							}
							
							if(currentStringArray!=null){
								int tmpint = modificationList.getSelectionIndex();

								modificationList.setItems(currentStringArray);
								modificationList.select(tmpint);
							}
							else
							{
								modificationList.removeAll();
							}
							break; // once a stereotype with the right property was detected stop searching and enable the buttons ...
								
						}
						else{
							button.setEnabled(false);
							addButton.setEnabled(false);
							removeButton.setEnabled(false);
							modificationList.setEnabled(false);
							modificationList.removeAll();
						}
	
					}
	
				
				}
				else{
					button.setEnabled(false);
					addButton.setEnabled(false);
					removeButton.setEnabled(false);
					modificationList.setEnabled(false);
					modificationList.removeAll();
				}


		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cea.papyrus.core.ui.properties.tabbed.PropertyViewSection#dispose()
	 */
	/**
	 * 
	 */
	public void dispose() {
		super.dispose();
		if (button != null && !button.isDisposed())
			button.removeMouseListener(editButtonlistener);
		if (addButton != null && !addButton.isDisposed())
			addButton.removeMouseListener(addButtonlistener);
		if (removeButton != null && !removeButton.isDisposed())
			removeButton.removeMouseListener(deleteButtonlistener);
		if (modificationList != null && !modificationList.isDisposed())
			modificationList.removeMouseListener(listlistener);

	}

	
	/**
	 * Sets the input.
	 * 
	 * @param part
	 *            the part
	 * @param selection
	 *            the selection
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		// get the selectedUmlElement
		Object input = ((IStructuredSelection) selection).getFirstElement();

		if (input instanceof ModelElementItem) {
			EObject eObject = ((ModelElementItem)input).getEObject();
			if ( eObject instanceof Element ) {
				this.selectedUmlElement = (Element)eObject;
			}
		}
		else if (input instanceof IUMLEditPart) {
			this.selectedUmlElement = ((IUMLEditPart)input).getUMLElement();
		}
		super.setInput(part, selection);
	}
	
	
	/**
	 * action executed when the down button is pressed.
	 */
	public void editButtonPressed(){
		if(modificationList.getSelectionIndex()!=-1)
		{
						
			//System.err.println("modificationList.getSelectionIndex(): " + modificationList.getSelectionIndex());
			
			iDialog = new EditorDialog(shell, TITLE, LABEL, modificationList.getItem(modificationList.getSelectionIndex()) , null, this.selectedUmlElement, stereotypPropertieName, modificationList.getSelectionIndex() );
			iDialog.open();
			modificationList.setItem(modificationList.getSelectionIndex(), iDialog.getValue()) ; 		
			modificationList.select(modificationList.getSelectionIndex());
			currentStringArray = modificationList.getItems();
			iDialog.close();
			setProperty();
		}
		else
		{
			addButtonPressed();
		}
		
	}
	
	/**
	 * Sets the property.
	 */
	public void setProperty(){
		EList<Stereotype> stereoList = this.selectedUmlElement.getAppliedStereotypes();
		Iterator<Stereotype> iterator = stereoList.iterator();
		while (iterator.hasNext()) {
			final Stereotype stereotype = (Stereotype) iterator.next();
			if(hasAttribute(stereotype, stereotypPropertieName))
			{
				EList<Property> properties = stereotype.getAllAttributes();
				Iterator<Property> it = properties.iterator();
				while (it.hasNext()) {
					Property property = (Property) it.next();
					if(property.getName().equals(stereotypPropertieName))
					{
						final java.util.List<String> currentInstanceModificationsList = Arrays.asList(currentStringArray);

						CompoundCommand cc = new CompoundCommand();
						// Get Papyrus editing domain.
						TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
						
						// Record command
						Command command = new RecordingCommand(editingDomain) {
							@Override
							protected void doExecute() {
								selectedUmlElement.setValue(stereotype, stereotypPropertieName, currentInstanceModificationsList);
							}
						};
						cc.append(command);
						// Execute command
						editingDomain.getCommandStack().execute(cc);
					}
				}
			}
		}
	}
	
	/**
	 * action executed when the down button is pressed.
	 */
	public void addButtonPressed(){
		iDialog = new EditorDialog(shell, TITLE, LABEL,"" , null, this.selectedUmlElement, stereotypPropertieName, modificationList.getItemCount() );
		iDialog.open();
		if(iDialog.getReturnCode() == 0 && iDialog.getValue()!=null && !iDialog.getValue().trim().equals(""))
		{
			modificationList.add(iDialog.getValue());
		}
		currentStringArray = modificationList.getItems();	
		iDialog.close();
		modificationList.select(currentStringArray.length-1);
		setProperty();
		
	}
	
	/**
	 * action executed when the down button is pressed.
	 */
	public void deleteButtonPressed(){
		if (modificationList.getSelectionIndex() > -1) {
			modificationList.remove(modificationList.getSelectionIndex());
			modificationList.select(0);
			currentStringArray = modificationList.getItems();
			setProperty();
		}
	}
	
	
	/**
	 * The Class EditButtonlistener.
	 */
	private class EditButtonlistener implements MouseListener {

		
		/**
		 * Mouse double click.
		 * 
		 * @param e
		 *            the e
		 */
		public void mouseDoubleClick(MouseEvent e) {
			// do nothing
		}

		/**
		 * Mouse down.
		 * 
		 * @param e
		 *            the e
		 */
		public void mouseDown(MouseEvent e) {
			// do nothing
		}

		/**
		 * Mouse up.
		 * 
		 * @param e
		 *            the e
		 */
		public void mouseUp(MouseEvent e) {
			editButtonPressed();
			refresh();
		}
	}
	
/**
 * The Class Listlistener.
 */
private class Listlistener implements MouseListener {

		
		/**
		 * Mouse double click.
		 * 
		 * @param e
		 *            the e
		 */
		public void mouseDoubleClick(MouseEvent e) {
			// do nothing
			editButtonPressed();
			refresh();
		}

		/**
		 * Mouse down.
		 * 
		 * @param e
		 *            the e
		 */
		public void mouseDown(MouseEvent e) {
			// do nothing
		}

		/**
		 * Mouse up.
		 * 
		 * @param e
		 *            the e
		 */
		public void mouseUp(MouseEvent e) {

		}
	}
	
/**
 * The Class AddButtonlistener.
 */
private class AddButtonlistener implements MouseListener {

		
		/**
		 * Mouse double click.
		 * 
		 * @param e
		 *            the e
		 */
		public void mouseDoubleClick(MouseEvent e) {
			// do nothing
		}

		/**
		 * Mouse down.
		 * 
		 * @param e
		 *            the e
		 */
		public void mouseDown(MouseEvent e) {
			// do nothing
		}

		/**
		 * Mouse up.
		 * 
		 * @param e
		 *            the e
		 */
		public void mouseUp(MouseEvent e) {
			addButtonPressed();
			refresh();
		}
	}

/**
 * The Class DeleteButtonlistener.
 */
private class DeleteButtonlistener implements MouseListener {

	
	/**
	 * Mouse double click.
	 * 
	 * @param e
	 *            the e
	 */
	public void mouseDoubleClick(MouseEvent e) {
		// do nothing
	}

	/**
	 * Mouse down.
	 * 
	 * @param e
	 *            the e
	 */
	public void mouseDown(MouseEvent e) {
		// do nothing
	}

	/**
	 * Mouse up.
	 * 
	 * @param e
	 *            the e
	 */
	public void mouseUp(MouseEvent e) {
		deleteButtonPressed();
		refresh();
	}
}

}

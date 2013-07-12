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
package org.openmodelica.modelicaml.tabbedproperties.editors.sections;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.tabbedproperties.editors.dialogs.StringInputDialog;



// TODO: Auto-generated Javadoc
/**
 * The Class AbstractSingleStringSection.
 */
public abstract class AbstractSingleStringSection extends AbstractPropertySection {

	/**
	 * Instantiates a new abstract single string section.
	 */
	public AbstractSingleStringSection() {
		super();
		this.editButtonlistener = new EditButtonlistener();
		// TODO Auto-generated constructor stub
	}


	/** The body. */
	protected Text body = null;
	
	/** The shell. */
	private Shell shell = null;
	
	
	
	/** The i dialog. */
	private StringInputDialog iDialog = null;
	
	/** The current string. */
	private String currentString = null;
	
	/**
	 * Listener for the add button.
	 */
	protected MouseListener editButtonlistener;

	/** The direct edit focus listener. */
	private DirectEditFocusListener directEditFocusListener = new DirectEditFocusListener();

	/** The stereotyp propertie name. */
	protected String stereotypPropertieName = null;
	
	
	/**
	 * The TITLE.
	 */
	private String TITLE = stereotypPropertieName+" value editing";

	/**
	 * The LABEL.
	 */
	private String LABEL = stereotypPropertieName+":";
	
	/** The button. */
	private Button button = null;
	
	/** The overview label. */
	protected CLabel overviewLabel;

	/** The selected uml element. */
	private Element selectedUmlElement;
	
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
		data.top = new FormAttachment(body, 0, SWT.TOP);
		overviewLabel.setLayoutData(data);
		
		body = getWidgetFactory().createText(composite, "", SWT.BORDER|SWT.V_SCROLL);
		body.setEditable(true);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.width = 300;
		data.top = new FormAttachment(overviewLabel, 0);
		data.height = 15;
		body.setLayoutData(data);
		body.addFocusListener(directEditFocusListener);

		button = new Button(composite, SWT.PUSH);
		button.setText("Edit");
		data = new FormData();
		data.left = new FormAttachment(body, 5);
		data.top = new FormAttachment(overviewLabel, 0);
		button.setLayoutData(data);
		button.addMouseListener(editButtonlistener);
		

	}

	/*
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	@Override
	public void refresh() {
		TITLE = stereotypPropertieName+" value editing";
		LABEL = stereotypPropertieName+":";
		if (!body.isDisposed()) {
				Element element = this.selectedUmlElement;
				EList<Stereotype> sList = element.getAppliedStereotypes();
				
				if(!sList.isEmpty())			
				{
					Iterator<Stereotype> iterator = sList.iterator();
					while (iterator.hasNext()) {
					Stereotype stereotype = ((Stereotype)iterator.next());
					
						if(Utils.hasAttribute(stereotype, stereotypPropertieName))
						{
							body.setEnabled(true);
							body.setEditable(true);
							button.setEnabled(true);
							body.removeFocusListener(directEditFocusListener);
							currentString = (String)(element.getValue(stereotype, stereotypPropertieName));
							if(currentString!=null){
								body.setText(currentString);
							}
							else {
								body.setText("");
							}
							body.addFocusListener(directEditFocusListener);
					
						}
						else {
							body.setEnabled(false);
							body.setEditable(false);
							button.setEnabled(false);
							body.setText("");
						}
					}
		
					
				}
				else {
					body.setEnabled(false);
					body.setEditable(false);
					button.setEnabled(false);
					body.setText("");
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
		if (body != null && !body.isDisposed())
			body.removeFocusListener(directEditFocusListener);
		if (button != null && !button.isDisposed())
			button.removeMouseListener(editButtonlistener);
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
		
		Object input = ((IStructuredSelection) selection).getFirstElement();
		// Get the selected element
        EObject selectedElement = ModelicaMLServices.adaptSelectedElement(input);
        if (selectedElement instanceof Element) {
        	this.selectedUmlElement = (Element)selectedElement;
		}
		
		super.setInput(part, selection);

	}
	
	/**
	 * Gets the selected element.
	 * 
	 * @return the selected element
	 */
	private Element getSelectedElement(){
		return this.selectedUmlElement;
	}
	
	
	/**
	 * action executed when the down button is pressed.
	 */
	public void editButtonPressed(){

		iDialog = new StringInputDialog(shell, TITLE, LABEL,currentString , null);
		iDialog.open();
		currentString = iDialog.getValue();
		iDialog.close();
		
		
		
		EList<Stereotype> stereoList = this.selectedUmlElement.getAppliedStereotypes();
		Iterator<Stereotype> iterator = stereoList.iterator();
		while (iterator.hasNext()) {
			Stereotype stereotype = (Stereotype) iterator.next();
			if(Utils.hasAttribute(stereotype, stereotypPropertieName))
			{
				EList<Property> properties = stereotype.getAllAttributes();
				Iterator<Property> it = properties.iterator();
				while (it.hasNext()) {
					Property property = (Property) it.next();
					if(property.getName().equals(stereotypPropertieName))
					{
						((Element) this.selectedUmlElement).setValue(stereotype, stereotypPropertieName, currentString);
	
						//getElement().eNotify(new NotificationImpl(PapyrusNotification.SET, null, null));
					}
				}
			}
		}
		
	}
	
	/**
	 * Listener for a direct edit manager.
	 * 
	 * @author Remi SCHNEKENBURGER
	 */
	protected class DirectEditFocusListener implements org.eclipse.swt.events.FocusListener {

		/**
		 * Focus gained.
		 * 
		 * @param e
		 *            the e
		 */
		public void focusGained(FocusEvent e) {
		}

		/**
		 * Focus lost.
		 * 
		 * @param e
		 *            the e
		 */
		public void focusLost(FocusEvent e) {
			if (getSelectedElement() != null) {
				
				EList<Stereotype> stereoList = getSelectedElement().getAppliedStereotypes();
				Iterator<Stereotype> iterator = stereoList.iterator();
				while (iterator.hasNext()) {
					Stereotype stereotype = (Stereotype) iterator.next();
					if(Utils.hasAttribute(stereotype, stereotypPropertieName))
					{
						EList<Property> properties = stereotype.getAllAttributes();
						Iterator<Property> it = properties.iterator();
						while (it.hasNext()) {
							Property property = (Property) it.next();
							if(property.getName().equals(stereotypPropertieName))
							{
								currentString=body.getText();
								((Element)getSelectedElement()).setValue(stereotype, stereotypPropertieName, currentString);

							}
						}
					}
			}

		}
	}
	}
	
	/**
	 * The Class EditButtonlistener.
	 */
	protected class EditButtonlistener implements MouseListener {

		
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
}

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
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.validation.services.ModelicaMLMarkerSupport;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.internal.ProviderActivator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part.PropertiesSectionXtextEditorHelper;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;

import com.google.inject.Injector;


public class PropertySection_ProviderOperationEditor extends AbstractPropertySection {
	
//	private Link textOperation;
	private TreeObject item = null;
	
	
	/** The LANGUAGE. */
	protected static String LANGUAGE = "Modelica";
	
	/** The selected uml element. */
	private Element storeLocation;

	/** The parent. */
	private Composite parent;
	
	/** The editor composite. */
	private Composite editorComposite;
	
	/** The editing domain. */
	private TransactionalEditingDomain editingDomain;
	
	/** The editor. */
	private PropertiesSectionXtextEditorHelper editor;
	
	/** The injector. */
	private Injector injector;
	
	/** The file extension. */
	private String fileExtension;
	
	/** The text to edit. */
	private String textToEdit = "";
	
	/** The owning class. */
	private Element owningClass;
	
	/** The is new selection. */
	private boolean isNewSelection;

	// Dependency stereotype name:
	// from Mediator to Client -> ProvidesValueFor
	// from Mediator to Provider -> ObtainsValueFrom
	private String stereotypeQName = Constants.stereotypeQName_ObtainsValueFrom;

	
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        Assert.isTrue(selection instanceof IStructuredSelection);
        Object input = ((IStructuredSelection) selection).getFirstElement();
//        Assert.isTrue(input instanceof TreeObject);
        this.item = (TreeObject) input;
  
//        EObject selectedElement = null;
        if (input instanceof TreeObject) {
			TreeObject item = (TreeObject)input;
			Element element = item.getUmlElement();
// ################################ Adjust start
			if (item.isValueProvider() && element instanceof NamedElement) {
// ################################ Adjust end
				TreeObject mediator = TreeUtls.getNearestMediator(item);
				Element mediatorElement = mediator.getUmlElement();

				if (mediatorElement instanceof NamedElement) {
					EList<Dependency> depList = TreeUtls.getMediatorDependency((NamedElement)mediatorElement, (NamedElement)element, stereotypeQName);
					if (depList != null && depList.size() == 1) {
						storeLocation = depList.get(0);
					}
					else {
						MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Value Mediator Inconsitency", "There are multiple links form the Mediator '" + mediator.getName() + "' " +
								" to the the Value Provider '" + item.getName() + "'. This is not allowed."  );
					}
				}
			}
		}
        
//        if (selectedElement instanceof Element) {
//        	storeLocation = (Element)selectedElement;
			if (storeLocation instanceof Dependency) {
				isNewSelection = true;
			}
//		}
   }
	
	

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		this.parent = parent;
		this.parent.setLayout(new GridLayout());
		this.parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		this.editorComposite = new Composite(this.parent, SWT.BORDER);
		this.editorComposite.setLayout(new GridLayout());
		this.editorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		// ################################ Adjust start
		injector = ProviderActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.valuebinding.Provider");
		fileExtension = ".valuebindingprovideroperation";
		
		editor = new PropertiesSectionXtextEditorHelper(storeLocation, injector, null, textToEdit, fileExtension); 
		// ################################ Adjust end
		
		editor.showEditor(editorComposite, SWT.BORDER);
		editor.getEditorWidget().addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (storeLocation != null) {
					storeText(storeLocation, editor.getText());					
				}
			}
		});
		
		// Get Papyrus editing domain
//		editingDomain = EditorUtils.getTransactionalEditingDomain();
		ServicesRegistry serviceRegistry;
		try {
			serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
			editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Boolean isValidElement(){
		// ################################ Adjust start
		if ( this.storeLocation instanceof Dependency && ((Dependency)this.storeLocation).getAppliedStereotype(stereotypeQName) != null) {
			return true;
		}
		// ################################ Adjust end
		return false;
	}
	
	
	@Override
	public void refresh() {
//		if (isNewSelection && isValidElement() ) { // Only react if a different (new) element was selected
		if (isValidElement() ) { // Only react if a different (new) element was selected
			
			//############## Adjust here: start
			
			// build the content assistance proposals list.
			Element element = this.item.getUmlElement();
			if (element instanceof Property) {
				ModelicaMLContentAssist.setSelectedSourceElement(element);
				owningClass = ((Property)element).getOwner();

				if (owningClass instanceof Class) {
					ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
					ModelicaMLContentAssist.setPropertyName(StringUtls.replaceSpecChar( ((Property)element).getName() ));
				}
			}
			// ################################ Adjust end

			//Marker support
			ModelicaMLContentAssist.setSelectedSourceElement(storeLocation);
			
			if (storeLocation != null ) {
				textToEdit = getOperationString();
			}
			else {
				textToEdit = "";
			}

			editor.setTextToEdit(textToEdit);
			editor.setContextElement(storeLocation);
			
			generateUMLModelMarker();
		}
	}
	
	/**
	 * Store text.
	 * 
	 * @param element
	 *            the element
	 * @param bodyText
	 *            the body text
	 */
	private void storeText(final EObject element, final String bodyText) {
		CompoundCommand cc = new CompoundCommand("Update operation code");
		
		// get the stereotype
//##################### ADOPT HERE 
		final Stereotype stereotype = storeLocation.getAppliedStereotype(stereotypeQName);
		
		if (stereotype != null) {
			// Record command
			// ################################## Adjust start
			if (bodyText.trim().equals("")) {
				Command command = new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						storeLocation.setValue(stereotype, Constants.propertyName_operation, null);
					}
				};
				cc.append(command);
			}
			if (storeLocation != null && bodyText != null) {
//				if (element instanceof OpaqueBehavior) {
					Command command = new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							storeLocation.setValue(stereotype, Constants.propertyName_operation, bodyText);
						}
					};
					cc.append(command);
//				}
			}
			// ################################## Adjust end
			
			// Execute command
			editingDomain.getCommandStack().execute(cc);
			
			isNewSelection = false; 	// Resets the indicator for a new selection, 
										// i.e., if the new text is put into the EObject 
										// then there was no new selection until the setInput() method determines a new selection.
			
			
			generateUMLModelMarker(); 	// Generate UML model marker
		}
	}
	

	
	
	// Marker support #########################################################
	/**
	 * Generate uml model marker.
	 */
	private void generateUMLModelMarker(){
		// create a marker for the uml model element
		String message = "The " + ((NamedElement)storeLocation).eClass().getName() 
							+ " '" + ((NamedElement)storeLocation).getName() + "' has errors in its Value Provider operation code.";
		
		if (editor.isDocumentHasErrors()) {
			ModelicaMLMarkerSupport.generateMarker(message, "error", (NamedElement)storeLocation, Constants.MARKERTYPE_ACTION_CODE);
		}
		else {
			ModelicaMLMarkerSupport.deleteMarker( message, (NamedElement)storeLocation, Constants.MARKERTYPE_ACTION_CODE);
		}
	}
	
	
	private String getOperationString(){
//##################### ADOPT HERE 		
		if (storeLocation != null) {
			Stereotype stereotype = storeLocation.getAppliedStereotype(stereotypeQName );
			if (stereotype != null) {
				Object object = storeLocation.getValue(stereotype, Constants.propertyName_operation);
				if (object instanceof String) {
					return (String)object;
				}
			}
		}
		return "";
	}
}

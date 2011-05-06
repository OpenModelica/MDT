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

package org.openmodelica.modelicaml.tabbedproperties.editors.sections;

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmt.modisco.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.core.utils.EditorUtils;
import org.eclipse.papyrus.diagram.common.editparts.IUMLEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.validation.services.ModelicaMLMarkerSupport;
import org.openmodelica.modelicaml.editor.xtext.declaration.ui.internal.DeclarationActivator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part.PropertiesSectionXtextEditorHelper;

import com.google.inject.Injector;

// TODO: Auto-generated Javadoc
/**
 * The Class DeclarationCodeSection.
 */
public class DeclarationCodeSection extends AbstractPropertySection  {

	/** The LANGUAGE. */
	protected static String LANGUAGE = "Modelica";
	
	/** The selected uml element. */
	private Element selectedUmlElement;

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
	
	/** The context element. */
	private Element contextElement;
	
	/** The owning class. */
	private Element owningClass;
	
	/** The is new selection. */
	private boolean isNewSelection;
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		this.parent = parent;
		this.parent.setLayout(new GridLayout());
		this.parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		this.editorComposite = new Composite(this.parent, SWT.BORDER);
		this.editorComposite.setLayout(new GridLayout());
		this.editorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		// ################################ Adjust start
		injector = DeclarationActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.declaration.Declaration");
		fileExtension = ".modelicamldeclaration"; // TODO rename the dsl extension to "modelicamlequationoralgorithm"
		
		editor = new PropertiesSectionXtextEditorHelper(selectedUmlElement, injector, null, textToEdit, fileExtension);
		// ################################ Adjust end
		
		editor.showEditor(editorComposite, SWT.BORDER);
		
//		FocusListener was replaced by ModifyListener (see below)
//		editor.getEditorWidget().addFocusListener(new FocusListener() {
//			public void focusLost(FocusEvent e) {
//				storeText(contextElement, editor.getText());
//			}			
//			public void focusGained(FocusEvent e) {
//			}
//		}) ;

		editor.getEditorWidget().addModifyListener(new ModifyListener() {
			// TODO: observe if it has impact on performance ...
			@Override
			public void modifyText(ModifyEvent e) {
				storeText(contextElement, editor.getText());
			}
		});
		
		// Get Papyrus editing domain.
		editingDomain = EditorUtils.getTransactionalEditingDomain();
	}
	
	/**
	 * Checks if is valid element.
	 * 
	 * @return the boolean
	 */
	private Boolean isValidElement(){
		// TODO: do we need this method?! This is already checked by the filter defined for the section
		
		// ################################ Adjust start
		if ( this.selectedUmlElement instanceof Property && !(this.selectedUmlElement instanceof Port)
				&& ((Property)this.selectedUmlElement).getAppliedStereotype(Constants.stereotypeQName_Variable) != null ) {
			return true;
		}
		if ( this.selectedUmlElement instanceof Parameter 
				&& ((Parameter)this.selectedUmlElement).getAppliedStereotype(Constants.stereotypeQName_FunctionArgument) != null){
			return true; 
		}
		
		// ################################ Adjust end
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	@Override
	public void refresh() {
		if (isNewSelection && isValidElement() ) { // Only react if a different (new) element was selected
			
			// ################################ Adjust start
			if (selectedUmlElement instanceof Property) {
				contextElement = ((Property)selectedUmlElement);
				Stereotype stereotype = ((Property)this.selectedUmlElement).getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::Variable");
				textToEdit =  "";
				if (stereotype != null ) {
					textToEdit = (String)(selectedUmlElement.getValue(stereotype, "declarationEquationOrAssignment"));
					if ( textToEdit == null ) { textToEdit = ""; }
				}
				
				// build the content assistance proposals list.
				ModelicaMLContentAssist.setSelectedSourceElement(selectedUmlElement);
				owningClass = ((Property)selectedUmlElement).getOwner(); 
				if (owningClass instanceof Class) {
					ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
				}
			}
			else if (selectedUmlElement instanceof Parameter) {
				contextElement = ((Parameter)selectedUmlElement);
				Stereotype stereotype = ((Parameter)this.selectedUmlElement).getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::FunctionArgument");
				textToEdit =  "";
				if (stereotype != null ) {
					textToEdit = (String)(selectedUmlElement.getValue(stereotype, "declarationEquationOrAssignment"));
					if ( textToEdit == null ) { textToEdit = ""; }
				}
				
				// build the content assistance proposals list.
				ModelicaMLContentAssist.setSelectedSourceElement(selectedUmlElement);
				owningClass = ((Parameter)selectedUmlElement).getOwner(); 
				if (owningClass instanceof Class) {
					ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
				}
			}
			// ################################ Adjust end
			
			editor.setTextToEdit(textToEdit);
			editor.setContextElement(contextElement);
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
		CompoundCommand cc = new CompoundCommand();
		
		// Record command
		// ################################## Adjust start
		if (element instanceof Property) {
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					Stereotype stereotype = ((Property)element).getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::Variable");
					if (stereotype != null) {
						((Element) element).setValue(stereotype, "declarationEquationOrAssignment", bodyText);
					}
					else {
						System.err.println("Cannot find ModelicaML stereotype applied to " + element);
					}
				}
			};
			cc.append(command);
		}
		else if (element instanceof Parameter) {
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					Stereotype stereotype = ((Parameter)element).getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::FunctionArgument");
					if (stereotype != null) {
						((Element) element).setValue(stereotype, "declarationEquationOrAssignment", bodyText);
					}
					else {
						System.err.println("Cannot find ModelicaML stereotype applied to " + element);
					}
				}
			};
			cc.append(command);
		}
		// ################################## Adjust end
		
		// Execute command
		editingDomain.getCommandStack().execute(cc);
		
		isNewSelection = false; 	// Resets the indicator for a new selection, 
									// i.e., if the new text is put into the EObject 
									// then there was no new selection until the setInput() method determines a new selection.
		
		generateUMLModelMarker(); 	// Generate UML model marker
		
	}
	
	
	
	// Marker support #########################################################
	/**
	 * Generate uml model marker.
	 */
	private void generateUMLModelMarker(){
		// create a marker for the uml model element
		String message = "The " + ((NamedElement)selectedUmlElement).eClass().getName() 
							+ " '" + ((NamedElement)selectedUmlElement).getName() + "' has errors in its Modelica 'declaration' code.";
		
		if (editor.isDocumentHasErrors()) {
			ModelicaMLMarkerSupport.generateMarker(message, "error", (NamedElement)selectedUmlElement);
		}
		else {
			ModelicaMLMarkerSupport.deleteMarker( message, (NamedElement)selectedUmlElement);
		}
	}

	// Utl #########################################################
	/**
	 * Gets the body index.
	 * 
	 * @param behavior
	 *            the behavior
	 * @param language
	 *            the language
	 * @return the body index
	 */
	public static int getBodyIndex(OpaqueAction behavior, String language) {
		int index = 0;
		boolean isFound = false;

		// test if the language exists
		Iterator<String> it = behavior.getLanguages().iterator();
		while (it.hasNext() && !isFound) {
			String lang = it.next();
			if (lang.equalsIgnoreCase(language)) {
				isFound = true;
			} else {
				index++;
			}
		}
		// returns -1 if not found
		if (!isFound) {
			index = -1;
		}
		return index;
	}
	

	// Same for all sections #########################################################
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		
		// get the selectedUmlElement
		Object input = ((IStructuredSelection) selection).getFirstElement();

		if (input instanceof ModelElementItem) {
			EObject eObject = ((ModelElementItem)input).getEObject();
			if ( eObject instanceof Element ) {
				this.selectedUmlElement = (Element)eObject;
				isNewSelection = true;
			}
		}
		else if (input instanceof IUMLEditPart) {
			this.selectedUmlElement = ((IUMLEditPart)input).getUMLElement();
			isNewSelection = true;
		}
	}

//	@Override
//	was used in combination with focusLost listener
//	public void aboutToBeHidden() { 
//		// This is necessary in order to enforce saving when user changes Eclipse views 
//		// (e.g. from Properties view to Console View, etc.). 
//		storeText(contextElement, editor.getText());
//	}


	
}

	
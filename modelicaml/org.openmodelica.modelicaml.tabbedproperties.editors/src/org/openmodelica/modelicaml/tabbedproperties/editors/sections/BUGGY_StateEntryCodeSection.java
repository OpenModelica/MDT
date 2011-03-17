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
import org.eclipse.papyrus.umlutils.OpaqueBehaviorUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.UMLPackage;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.validation.services.ModelicaMLMarkerSupport;
import org.openmodelica.modelicaml.editor.xtext.algorithm.ui.internal.AlgorithmsectionActivator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part.PropertiesSectionXtextEditorHelper;

import com.google.inject.Injector;


// TODO: Auto-generated Javadoc
/**
 * The Class StateEntryCodeSection.
 */
public class BUGGY_StateEntryCodeSection extends AbstractPropertySection  {

	
	/** The state behavior type. */
	protected String stateBehaviorType = "entry";
	
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
	
	/** The state machine state. */
	private State stateMachineState;

	/** The initial. */
	private boolean initial = true;

	
	
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
		injector = AlgorithmsectionActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.algorithm.Algorithmsection");
		fileExtension = ".modelicamlalgorithmsection";
		
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
		
		// Get Papyrus editing domain
		editingDomain = EditorUtils.getTransactionalEditingDomain();
	}
	
	/**
	 * Checks if is valid element.
	 * 
	 * @return the boolean
	 */
	private Boolean isValidElement(){
		// ################################ Adjust start
		//Note UML FunctionBehavior is a sub-type of OpaqueBehavior 
		if ( this.selectedUmlElement instanceof State ) {
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
			
			// build the content assistance proposals list.
			owningClass = ((State)selectedUmlElement).getOwner().getOwner().getOwner(); 
			if (owningClass instanceof Class) {
				ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
			}
			
			// redirect the selected element to the respective OpaqueBehavior 
			//############## Adjust here: start
			Behavior definedBehavior = ((State)this.selectedUmlElement).getEntry();
			
			if ( definedBehavior == null) {
				//Behavior behavior = createStateCodeBehavior((State)this.selectedUmlElement, "entry");
				//this.selectedUmlElement = behavior;
			}
			//############## Adjust here: end
			else if (definedBehavior instanceof OpaqueBehavior) {
				this.selectedUmlElement = definedBehavior;
			}
			else {
				System.err.println(definedBehavior.eClass().getName() + " cannot be used as input to ModelicaML code editor.");
			}
			
			//Marker support: It is important to set it after the redirection of the selected element.
			ModelicaMLContentAssist.setSelectedSourceElement(selectedUmlElement);
			
			// ################################ Adjust start
			if (selectedUmlElement instanceof OpaqueBehavior && !(selectedUmlElement instanceof FunctionBehavior) ) {
				contextElement = ((OpaqueBehavior)selectedUmlElement);
				textToEdit = (String) OpaqueBehaviorUtil.getBody((OpaqueBehavior) selectedUmlElement,LANGUAGE);
			}
			// ################################ Adjust end
			
			editor.setTextToEdit(textToEdit);
			editor.setContextElement(contextElement);
			
			if (initial) { initial = false; } // indicate that this is not the initial case where the editor text is empty.
			
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
		// ################################## Adjust start
		if ( bodyText != null ) {
			CompoundCommand cc = new CompoundCommand();
			
			if (this.selectedUmlElement instanceof State) { // the initial case when no OpaqueBehavior is defined for the selected State
				Behavior definedBehavior = ((State)this.selectedUmlElement).getEntry();
				if ( definedBehavior == null) {
					Behavior behavior = createStateCodeBehavior((State)this.selectedUmlElement, stateBehaviorType);
					this.selectedUmlElement = behavior; // redirect the selected element to be the OpaqueBehavoir
				}
			}
			else if (this.selectedUmlElement instanceof OpaqueBehavior) { // The case when the State OpaqueBehavior is already in place.
				
			}

			final EObject el = this.selectedUmlElement;
			System.err.println("initial: " + initial);

			
//			if (el instanceof OpaqueBehavior) {
				if (!initial && bodyText.trim().equals("")) {
					System.err.println("bodyText.trim().equals('') ! ");
					
					Command command = new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							if (el instanceof OpaqueBehavior) {
								((OpaqueBehavior) el).destroy();
							}
						}
					};
					cc.append(command);
				}
				else {
					System.err.println("bodyText: " + bodyText);
					System.err.println(el);
					
					Command command = new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							OpaqueBehaviorUtil.setBody((OpaqueBehavior) el, bodyText, LANGUAGE);
						}
					};
					cc.append(command);
				}
//			}
			
			// Execute command
			editingDomain.getCommandStack().execute(cc);
			isNewSelection = false; 	// Resets the indicator for a new selection, 
										// i.e., if the new text is put into the EObject 
										// then there was no new selection until the setInput() method determines a new selection.
			generateUMLModelMarker(); 	// Generate UML model marker

		}
		// ################################## Adjust end
	}
	
	

	
	
	// Marker support #########################################################
	/**
	 * Generate uml model marker.
	 */
	private void generateUMLModelMarker(){
		// create a marker for the uml model element
		String message = "The " + ((NamedElement)selectedUmlElement).eClass().getName() 
							+ " '" + ((NamedElement)selectedUmlElement).getName() + "' has errors in its Modelica code.";
		
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
	
	/**
	 * Creates the state code behavior.
	 * 
	 * @param state
	 *            the state
	 * @param typeOfStateCode
	 *            the type of state code
	 * @return the behavior
	 */
	private Behavior createStateCodeBehavior(final State state, final String typeOfStateCode){
		CompoundCommand cc = new CompoundCommand();

		// Record command
		// ################################## Adjust start
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				if (typeOfStateCode.equals("entry")) {
					state.createEntry("Entry code" ,UMLPackage.Literals.OPAQUE_BEHAVIOR);
				}
				else if (typeOfStateCode.equals("do")) {
					state.createDoActivity("Do code" ,UMLPackage.Literals.OPAQUE_BEHAVIOR);
				}
				else if (typeOfStateCode.equals("exit")) {
					state.createExit("Exitcode" ,UMLPackage.Literals.OPAQUE_BEHAVIOR);	
				}
			}
		};
		cc.append(command);
		// ################################## Adjust end
		
		// Execute command
		editingDomain.getCommandStack().execute(cc);
		
		if (typeOfStateCode.equals("entry")) {
			return state.getEntry();
		}
		else if (typeOfStateCode.equals("do")) {
			return state.getDoActivity();
		}
		else if (typeOfStateCode.equals("exit")) {
			return state.getExit();	
		}
		
		return null;
	}
	
	
//	private void deleteStateCodeBehavior(final State state, final String typeOfStateCode){
//		CompoundCommand cc = new CompoundCommand();
//
//		// Record command
//		// ################################## Adjust start
//		Command command = new RecordingCommand(editingDomain) {
//			@Override
//			protected void doExecute() {
//				if (typeOfStateCode.equals("entry")) {
//					Behavior b = state.getEntry();
//					if (b != null) {  b.destroy(); }
//				}
//				else if (typeOfStateCode.equals("do")) {
//					Behavior b = state.getDoActivity();
//					if (b != null) {  b.destroy(); }
//				}
//				else if (typeOfStateCode.equals("exit")) {
//					Behavior b = state.getExit();
//					if (b != null) {  b.destroy(); }
//				}
//			}
//		};
//		cc.append(command);
//		// ################################## Adjust end
//		
//		// Execute command
//		editingDomain.getCommandStack().execute(cc);
//	}
	
	
	
	
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

	
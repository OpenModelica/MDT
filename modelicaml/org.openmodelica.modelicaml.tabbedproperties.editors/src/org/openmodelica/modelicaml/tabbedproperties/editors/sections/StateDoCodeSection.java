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

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.umlutils.OpaqueBehaviorUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
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
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.validation.services.ModelicaMLMarkerSupport;
import org.openmodelica.modelicaml.editor.xtext.algorithm.ui.internal.AlgorithmsectionActivator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part.PropertiesSectionXtextEditorHelper;

import com.google.inject.Injector;


// TODO: Auto-generated Javadoc
/**
 * The Class StateDoCodeSection.
 */
public class StateDoCodeSection extends AbstractPropertySection  {
	/** The LANGUAGE. */
	protected static String LANGUAGE = "Modelica";

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
	private boolean isNewSelection = false;
	
	/** The selected uml element. */
	private Element selectedUmlElement;

	private State selectedState = null;
	
	private OpaqueBehavior stateBehavior = null;
	
	private final String stateActionKind = "do"; 
	
	
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
		
		editor = new PropertiesSectionXtextEditorHelper(selectedState, injector, null, textToEdit, fileExtension); // TODO: delete editpart from the constuctor
		// ################################ Adjust end
		
		editor.showEditor(editorComposite, SWT.BORDER);
		
		//FocusListener to make sure that text is stored if the last modification was undo or redo actions
		editor.getEditorWidget().addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				storeText(stateBehavior, editor.getText());
			}			
			public void focusGained(FocusEvent e) {
			}
		});

		editor.getEditorWidget().addModifyListener(new ModifyListener() {
			// TODO: observe if it has impact on performance ...
			@Override
			public void modifyText(ModifyEvent e) {
				storeText(stateBehavior, editor.getText());
			}
		});
		
		// Get Papyrus editing domain
//		editingDomain = EditorUtils.getTransactionalEditingDomain();
	}
	
	/**
	 * Checks if is valid element.
	 * 
	 * @return the boolean
	 */
	private Boolean isValidElement(){
		// ################################ Adjust start
		if ( selectedState instanceof State ) { return true; }
		// ################################ Adjust end
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	@Override
	public void refresh() {
		if (isNewSelection && isValidElement() ) { // Only react if a different (new) element was selected
			
			stateBehavior = null; // reset the stateBehavior. important when another state is selected.
			
			// build the content assistance proposals list.
			owningClass = Utils.getContextClass(selectedUmlElement);
			if (owningClass instanceof Class) {
				ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
			}
			
			//############## Adjust here: start
			Behavior definedBehavior = ((State)selectedState).getDoActivity();
			if (definedBehavior instanceof OpaqueBehavior && !(definedBehavior instanceof FunctionBehavior)) {
				stateBehavior = (OpaqueBehavior)definedBehavior;
			}
			else if (definedBehavior != null ) {
				System.err.println(definedBehavior.eClass().getName() + " cannot be used as input to ModelicaML code editor.");
			}
			
			//Marker support
			ModelicaMLContentAssist.setSelectedSourceElement(stateBehavior);
			
			// ################################ Adjust start
			if (stateBehavior != null && !(stateBehavior instanceof FunctionBehavior) ) {
				textToEdit = (String) OpaqueBehaviorUtil.getBody(stateBehavior, LANGUAGE);
			}
			else {
				textToEdit = "";
			}
			// ################################ Adjust end
			
			editor.setTextToEdit(textToEdit);
			editor.setContextElement(stateBehavior);
			
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
		CompoundCommand cc = new CompoundCommand("Update " + stateActionKind + " action code of " + selectedState.getQualifiedName());
		
		// Record command
		// ################################## Adjust start
		if (stateBehavior == null) {
			stateBehavior = (OpaqueBehavior) createStateCodeBehavior(selectedState, stateActionKind);
		}
		if (bodyText.trim().equals("")) {
			deleteStateCodeBehavior(selectedState, stateActionKind);
		}
		if (stateBehavior != null && bodyText != null ) {
//			if (element instanceof OpaqueBehavior) {
				Command command = new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
//						OpaqueBehaviorUtil.setBody((OpaqueBehavior) element, bodyText, LANGUAGE);
						OpaqueBehaviorUtil.setBody(stateBehavior, bodyText, LANGUAGE);
					}
				};
				cc.append(command);
//			}
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
							+ " '" + ((NamedElement)selectedUmlElement).getName() + "' has errors in its Modelica code.";
		
		if (editor.isDocumentHasErrors()) {
			ModelicaMLMarkerSupport.generateMarker(message, "error", (NamedElement)selectedUmlElement, Constants.MARKERTYPE_ACTION_CODE);
		}
		else {
			ModelicaMLMarkerSupport.deleteMarker( message, (NamedElement)selectedUmlElement, Constants.MARKERTYPE_ACTION_CODE);
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
	
	
	private void deleteStateCodeBehavior(final State state, final String typeOfStateCode){
		CompoundCommand cc = new CompoundCommand();

		// Record command
		// ################################## Adjust start
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				if (typeOfStateCode.equals("entry")) {
					Behavior b = state.getEntry();
					if (b != null) {  b.destroy(); stateBehavior = null; }
				}
				else if (typeOfStateCode.equals("do")) {
					Behavior b = state.getDoActivity();
					if (b != null) {  b.destroy(); stateBehavior = null; }
				}
				else if (typeOfStateCode.equals("exit")) {
					Behavior b = state.getExit();
					if (b != null) {  b.destroy(); stateBehavior = null; }
				}
			}
		};
		cc.append(command);
		// ################################## Adjust end
		
		// Execute command
		editingDomain.getCommandStack().execute(cc);
	}
	
	
	
	
	// Same for all sections #########################################################
	/* (non-Javadoc)
 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
 */
@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		
		// get the selectedUmlElement
		Object input = ((IStructuredSelection) selection).getFirstElement();
		// Get the selected element
        EObject selectedElement = ModelicaMLServices.adaptSelectedElement(input);
        if (selectedElement instanceof State) {
        	this.selectedUmlElement = (Element)selectedElement;
        	isNewSelection = true;
			selectedState = (State)selectedUmlElement;
		}
        
		// Get Papyrus editing domain.
		editingDomain = (TransactionalEditingDomain) Utils.getEditingDomain(part);
	}

//	@Override
//	was used in combination with focusLost listener
//	public void aboutToBeHidden() { 
//		// This is necessary in order to enforce saving when user changes Eclipse views 
//		// (e.g. from Properties view to Console View, etc.). 
//		storeText(contextElement, editor.getText());
//	}


}

	
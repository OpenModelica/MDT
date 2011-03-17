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
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.validation.services.ModelicaMLMarkerSupport;
import org.openmodelica.modelicaml.editor.xtext.activity.ui.internal.ActivitycontrolflowguardexpressionActivator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part.PropertiesSectionXtextEditorHelper;

import com.google.inject.Injector;


// TODO: Auto-generated Javadoc
/**
 * The Class ActivityEdgeGuardPropertySectionCodeSection.
 */
public class ActivityEdgeGuardCodeSection extends AbstractPropertySection  {

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
		injector = ActivitycontrolflowguardexpressionActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.activity.Activitycontrolflowguardexpression");
		fileExtension = ".activitycontrolflowguardexpression";
		
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
		if ( this.selectedUmlElement instanceof ActivityEdge ) {
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
			if (selectedUmlElement instanceof ActivityEdge ) {
				contextElement = ((ActivityEdge)selectedUmlElement);

				if ((LiteralString)((ControlFlow)selectedUmlElement).getGuard() != null) {
					textToEdit = ((LiteralString)((ControlFlow)selectedUmlElement).getGuard()).getValue();
				}
				else {
					textToEdit = "";					
				}
				
				// build the content assistance proposals list.
				ModelicaMLContentAssist.setSelectedSourceElement(selectedUmlElement);
				//owningClass = ((ActivityEdge)selectedUmlElement).getActivity().getOwner();
				owningClass = Utils.getContextClass(selectedUmlElement);
				
				if (owningClass instanceof Class) {
					ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
				}
			}
			// ################################ Adjust end
			
			editor.setTextToEdit(textToEdit);
			editor.setContextElement(contextElement);
			
			// marker support
			if (selectedUmlElement instanceof NamedElement) {
				generateUMLModelMarker(); 	// Generate UML model marker
			}
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
		if (element instanceof ActivityEdge) {
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					
					//TODO: still buggy, does not work!
					String activityEdgeGuard = bodyText.trim();
					ValueSpecification v = ((ActivityEdge) element).getGuard();
					if (v == null) {
						LiteralString guardString = (LiteralString) ((ActivityEdge) element).createGuard("guard code", null, UMLPackage.Literals.LITERAL_STRING);
						guardString.setValue(bodyText);
					}
					else {
						((LiteralString) ((ActivityEdge) element).getGuard()).setValue(activityEdgeGuard);
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
		
		if (element instanceof NamedElement) {
			generateUMLModelMarker(); 	// Generate UML model marker
		}
	}
	
	

	
	
	// Marker support #########################################################
	/**
	 * Generate uml model marker.
	 */
	private void generateUMLModelMarker(){
		if (this.selectedUmlElement != null && this.selectedUmlElement instanceof NamedElement) {
			// create a marker for the uml model element
			String message = "The " + ((NamedElement)selectedUmlElement).eClass().getName() 
								+ " '" + ((NamedElement)selectedUmlElement).getName() + "' has errors in its guard Modelica code.";
			
			if (editor.isDocumentHasErrors()) {
				ModelicaMLMarkerSupport.generateMarker(message, "error", (NamedElement)selectedUmlElement);
			}
			else {
				ModelicaMLMarkerSupport.deleteMarker( message, (NamedElement)selectedUmlElement);
			}
		}
	}
	
	// Utl #########################################################
//	public static int getBodyIndex(OpaqueAction behavior, String language) {
//		int index = 0;
//		boolean isFound = false;
//
//		// test if the language exists
//		Iterator<String> it = behavior.getLanguages().iterator();
//		while (it.hasNext() && !isFound) {
//			String lang = it.next();
//			if (lang.equalsIgnoreCase(language)) {
//				isFound = true;
//			} else {
//				index++;
//			}
//		}
//		// returns -1 if not found
//		if (!isFound) {
//			index = -1;
//		}
//		return index;
//	}
//	
//	private String retrieveBody(OpaqueExpression exp, String languageName) {
//		String body = "";
//		if(exp == null)
//			return body;
//		int index = 0;
//		for(String _languageName : exp.getLanguages()) {
//			if(_languageName.equals(languageName)) {
//				if(index < exp.getBodies().size())
//					return exp.getBodies().get(index);
//				else
//					return "";
//			}
//			index++;
//		}
//		return body;
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
// 	was used in combination with focusLost listener
//	public void aboutToBeHidden() { 
//		// This is necessary in order to enforce saving when user changes Eclipse views 
//		// (e.g. from Properties view to Console View, etc.). 
//		storeText(contextElement, editor.getText());
//	}

}

	
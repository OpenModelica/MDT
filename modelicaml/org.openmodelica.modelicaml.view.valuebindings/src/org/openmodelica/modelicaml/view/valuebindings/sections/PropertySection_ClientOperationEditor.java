package org.openmodelica.modelicaml.view.valuebindings.sections;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.core.utils.EditorUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.validation.services.ModelicaMLMarkerSupport;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.internal.ClientActivator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part.PropertiesSectionXtextEditorHelper;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;

import com.google.inject.Injector;


public class PropertySection_ClientOperationEditor extends AbstractPropertySection {
	
//	private Link textOperation;
	private TreeObject item = null;
	
	
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
	
	/** The owning class. */
	private Element owningClass;
	
	/** The is new selection. */
	private boolean isNewSelection;
	
//	private Element valueClient;

	
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
//        Assert.isTrue(selection instanceof IStructuredSelection);
        Object input = ((IStructuredSelection) selection).getFirstElement();
//        Assert.isTrue(input instanceof TreeObject);
//        this.item = (TreeObject) input;
        
        EObject selectedElement = (EObject) adaptSelectedElement(input);
        if (selectedElement instanceof Element) {
        	selectedUmlElement = (Element)selectedElement;
			if (selectedUmlElement instanceof Property) {
//				valueClient = selectedUmlElement;
				isNewSelection = true;
			}
		}
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
		injector = ClientActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.valuebinding.Client");
		fileExtension = ".valuebindingclientoperation";
		
		// TODO: delete editpart from the constuctor
		editor = new PropertiesSectionXtextEditorHelper(selectedUmlElement, injector, null, textToEdit, fileExtension); 
		// ################################ Adjust end
		
		editor.showEditor(editorComposite, SWT.BORDER);
		editor.getEditorWidget().addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (selectedUmlElement != null) {
					storeText(selectedUmlElement, editor.getText());					
				}
			}
		});
		
		// Get Papyrus editing domain
		editingDomain = EditorUtils.getTransactionalEditingDomain();
	}

	private Boolean isValidElement(){
		// ################################ Adjust start
		if ( this.selectedUmlElement instanceof Property && (
					((Property)this.selectedUmlElement).getAppliedStereotype(Constants.stereotypeQName_ValueClient) != null 
					|| 	((Property)this.selectedUmlElement).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null 
				)
			) {
			return true;
		}
		// ################################ Adjust end
		return false;
	}
	
	
	@Override
	public void refresh() {
		if (isNewSelection && isValidElement() ) { // Only react if a different (new) element was selected
			
//			valueClient = null; // reset the element. important when another element of the same meta-type is selected.
			
			if (isValueClient()) { // no code completion for Value Mediator
				// build the content assistance proposals list.
				ModelicaMLContentAssist.setSelectedSourceElement(selectedUmlElement);
				owningClass = ((Property)selectedUmlElement).getOwner();

				if (owningClass instanceof Class) {
					ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
				}
			}
			else {// no code completion for Value Mediator
				ModelicaMLContentAssist.clearAllLists();
			}
			
			//############## Adjust here: start

			//Marker support
			ModelicaMLContentAssist.setSelectedSourceElement(selectedUmlElement);
			
			// ################################ Adjust start
			if (selectedUmlElement != null ) {
				textToEdit = getOperationString();
			}
			else {
				textToEdit = "";
			}
			// ################################ Adjust end
			
			editor.setTextToEdit(textToEdit);
			editor.setContextElement(selectedUmlElement);
			
			generateUMLModelMarker();
		}
	}
	
	private boolean isValueClient(){
		if (selectedUmlElement.getAppliedStereotype(Constants.stereotypeQName_ValueClient) != null) {
			return true;
		}
		return false;
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
		Stereotype stereotype_temp = selectedUmlElement.getAppliedStereotype(Constants.stereotypeQName_ValueClient);
		if (stereotype_temp == null) { // try anothjer
			stereotype_temp = selectedUmlElement.getAppliedStereotype(Constants.stereotypeQName_ValueMediator);
		}
		final Stereotype stereotype = stereotype_temp;
		if (stereotype != null) {
			// Record command
			// ################################## Adjust start
			if (bodyText.trim().equals("")) {
				Command command = new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						selectedUmlElement.setValue(stereotype, Constants.propertyName_operation, null);
					}
				};
				cc.append(command);
			}
			if (selectedUmlElement != null && bodyText != null) {
//				if (element instanceof OpaqueBehavior) {
					Command command = new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							selectedUmlElement.setValue(stereotype, Constants.propertyName_operation, bodyText);
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
		String message = "The " + ((NamedElement)selectedUmlElement).eClass().getName() 
							+ " '" + ((NamedElement)selectedUmlElement).getName() + "' has errors in its Value client/Mediator operation code.";
		
		if (editor.isDocumentHasErrors()) {
			ModelicaMLMarkerSupport.generateMarker(message, "error", (NamedElement)selectedUmlElement);
		}
		else {
			ModelicaMLMarkerSupport.deleteMarker( message, (NamedElement)selectedUmlElement);
		}
	}
	
	
	private String getOperationString(){
	  Stereotype stereotype = getStereotype();
	  if (stereotype != null) {
		  Element element = selectedUmlElement;
		  if (element != null) {
			  Object object = element.getValue(stereotype, Constants.propertyName_operation);
			  if (object instanceof String) {
				return (String)object;
			}
		  }
		}
	  return "";
	}
	
	private Stereotype getStereotype(){
		Stereotype stereotype = null;
		Element element = selectedUmlElement;
		if (element != null) {
			stereotype = element.getAppliedStereotype(Constants.stereotypeQName_ValueClient);
			if (stereotype != null) { return stereotype;}

			stereotype = element.getAppliedStereotype(Constants.stereotypeQName_ValueProvider);
			if (stereotype != null) { return stereotype;}

			stereotype = element.getAppliedStereotype(Constants.stereotypeQName_ValueMediator);
			if (stereotype != null) { return stereotype;}
		}
		return stereotype;
	}
	
	
	
	
	
//	protected List<Object> getSelectedElements(ExecutionEvent event) throws ExecutionException {
//		ISelection selection = getSelection(event);
//		if (!( selection instanceof IStructuredSelection))
//			return Collections.emptyList();
//		return (List<Object>)((IStructuredSelection) selection).toList();
//	}
//
//	/**
//	 * Gets the current selection.
//	 * 
//	 * @param event
//	 *            the event
//	 * @return The current selection.
//	 * @throws ExecutionException
//	 *             the execution exception
//	 */
//	protected ISelection getSelection(ExecutionEvent event) throws ExecutionException {
//		return HandlerUtil.getCurrentSelectionChecked(event);
//	}

	/**
	 * Gets the current selections.
	 * 
	 * @return the current selections
	 */
//	private List<Object> getCurrentSelections() {
//		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
//		if(selection instanceof IStructuredSelection) {
//			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
//			return structuredSelection.toList();
//		}
//	
//		return null;
//	}

	/**
	 * Adapt the selected element to an EObject, in case there is intermediate
	 * construct (like notation.View)
	 * 
	 * @param selection
	 *            the selection
	 * @return the e object
	 */
	protected EObject adaptSelectedElement( Object selection) {

		EObject eObject = null;
	
		if(selection != null) {
			
//			// treeObject from value bindings plugin
//			if (selection instanceof TreeObject) {
//				Element element = ((TreeObject)selection).getUmlElement();
//				if ( element != null) {
//					eObject = element;
//				}
//			}
			
			// from any adaptable
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
	
}

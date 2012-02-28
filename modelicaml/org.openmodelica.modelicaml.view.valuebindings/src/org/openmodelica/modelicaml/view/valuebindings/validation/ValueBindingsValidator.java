package org.openmodelica.modelicaml.view.valuebindings.validation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.papyrus.resource.uml.ExtendedUmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.internal.ClientActivator;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.internal.MediatorActivator;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.internal.ProviderActivator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part.PropertiesSectionXtextEditorHelper;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeParent;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;

import com.google.inject.Injector;

//public class ValueBindingsValidator implements IRunnableWithProgress {
public class ValueBindingsValidator {
	
	List<TreeObject> clients = new ArrayList<TreeObject>();
	List<TreeObject> mediators = new ArrayList<TreeObject>();
	List<TreeObject> providers = new ArrayList<TreeObject>();
	
	private Injector injector;
	private String fileExtension;
	private PropertiesSectionXtextEditorHelper editor;
	
	// Composite used temporary for instantiating editors that are never shown ...
	private Composite tempComposite = new Composite(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.NONE);
	
	private ExtendedUmlModel umlModel;
	private IProject iProject;
	
	private Element storeLocation = null;
	private String stereotypeQName;
	private String textToEdit;

	public ValueBindingsValidator(TreeParent parent){
		// collect client, mediator and provider tree nodes
		collectItems(parent);
		
		// set up marker data
		umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();
		String projectName = umlModel.getResource().getURI().segment(1);
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		iProject = root.getProject(projectName);
	}
	
	public void validate(){

		// delete all old markers
		deleteValidationMarkers(iProject);

		if (clients.size() > 0) {
			validateClients();
		}
		if (mediators.size() > 0) {
			validateMediators();
		}
		if (providers.size() > 0) {
			validateProviders();
		}
	}
	
	private void validateClients(){
		
		stereotypeQName = Constants.stereotypeQName_ProvidesValueFor;
		
		for (TreeObject treeObject : clients) {
			Element element = treeObject.getUmlElement();
			
			if (treeObject.isValueClient() && element instanceof NamedElement ) {

				TreeObject mediator = TreeUtls.getNearestMediator(treeObject);
				Element mediatorElement = mediator.getUmlElement();

				if (mediatorElement instanceof NamedElement) {
					EList<Dependency> depList = TreeUtls.getMediatorDependency((NamedElement)mediatorElement, (NamedElement)element, stereotypeQName);
					
					if (depList != null && depList.size() == 1) {
						
						storeLocation = depList.get(0);
						
						// if the is action code to validate 
						if (!getOperationString().trim().equals("")) {
							
							injector = ClientActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.valuebinding.Client");
							fileExtension = ".valuebindingclientoperation";
							
							editor = new PropertiesSectionXtextEditorHelper(storeLocation, injector, null, textToEdit, fileExtension);
							// TODO: note, refactor the glue code in order to avoid using composites (UI) for the purpose of simple code validation
							editor.showEditor(tempComposite, SWT.NONE);

							// Code completion support
							if (element instanceof Property) {
								ModelicaMLContentAssist.setSelectedSourceElement(element);
								Element owningClass = ((Property)element).getOwner();

								if (owningClass instanceof Class) {
									ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
									ModelicaMLContentAssist.setPropertyName(StringUtls.replaceSpecChar( ((Property)element).getName() ));
								}
							}

							ModelicaMLContentAssist.setSelectedSourceElement(storeLocation);
							
							if (storeLocation != null ) {
								textToEdit = getOperationString();
							}
							else {
								textToEdit = "";
							}

							editor.setTextToEdit(textToEdit);
							editor.setContextElement(storeLocation);
							
							
							// Generate marker and set hasErrors for the tree items
							if (editor.isDocumentHasErrors()) {
								createMarker(storeLocation, "error", "Value Client '"+treeObject+"' has errors in its operation code.");
							}
						}
					}
				}
			}
		}
	}

	private void validateMediators(){
		stereotypeQName = Constants.stereotypeQName_ValueMediator;
		
		for (TreeObject treeObject : mediators) {
			Element element = treeObject.getUmlElement();
			storeLocation = element;
			
			// if the is action code to validate 
			if (!getOperationString().trim().equals("")) {
				if (treeObject.isValueMediator() && element instanceof NamedElement) {
					injector = MediatorActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.valuebinding.Mediator");
					fileExtension = ".mediatorvaluebindingoperation";
					editor = new PropertiesSectionXtextEditorHelper(element, injector, null, textToEdit, fileExtension); 
					editor.showEditor(tempComposite, SWT.NONE);
					
					//Code completion 
					// no components code completion for Value Mediator
					ModelicaMLContentAssist.clearAllLists();
					ModelicaMLContentAssist.setSelectedSourceElement(element);

					if (storeLocation != null ) {
						textToEdit = getOperationString();
					}
					else {
						textToEdit = "";
					}
					
					editor.setTextToEdit(textToEdit);
					editor.setContextElement(element);
					
					// Generate marker and set hasErrors for the tree items
					if (editor.isDocumentHasErrors()) {
						createMarker(storeLocation, "error", "Value Mediator '"+treeObject+"' has errors in its operation code.");
					}
				}
			}
		}
	}

	private void validateProviders(){
		
		stereotypeQName = Constants.stereotypeQName_ObtainsValueFrom;
		
		for (TreeObject treeObject : providers) {
			
			Element element = treeObject.getUmlElement();
			
			if (treeObject.isValueProvider() && element instanceof NamedElement) {
			
				TreeObject mediator = TreeUtls.getNearestMediator(treeObject);
				Element mediatorElement = mediator.getUmlElement();

				if (mediatorElement instanceof NamedElement) {
					EList<Dependency> depList = TreeUtls.getMediatorDependency((NamedElement)mediatorElement, (NamedElement)element, 
							stereotypeQName);
					if (depList != null && depList.size() == 1) {
						storeLocation = depList.get(0);
						
						
						// if the is action code to validate 
						if (!getOperationString().trim().equals("")) {
							injector = ProviderActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.valuebinding.Provider");
							fileExtension = ".valuebindingprovideroperation";
							
							editor = new PropertiesSectionXtextEditorHelper(storeLocation, injector, null, textToEdit, fileExtension); 
							editor.showEditor(tempComposite, SWT.NONE);
							
							// build the content assistance proposals list.
							if (element instanceof Property) {
								ModelicaMLContentAssist.setSelectedSourceElement(element);
								Element owningClass = ((Property)element).getOwner();

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

							// Generate marker and set hasErrors for the tree items
							if (editor.isDocumentHasErrors()) {
								createMarker(storeLocation, "error", "Value Provider '" + treeObject + "' has errors in its operation code.");
							}
						}
					}
				}
			}
		}
	}

	
	public void collectItems(TreeParent parent){
		if (parent.hasChildren()) {
			TreeObject[] children = parent.getChildren();	
			for (int i = 0; i < children.length; i++) {
				TreeObject treeObject = children[i];
				if (treeObject.isValueClient()) {
					clients.add(treeObject);
				}
				else if (treeObject.isValueMediator()) {
					mediators.add(treeObject);
				}
				else if (treeObject.isValueProvider()){
					providers.add(treeObject);
				}
				
				// recursive call
				if (treeObject instanceof TreeParent) {
					collectItems((TreeParent) treeObject);
				}
			}
		}
	}
	
	
	
	// Marker *****************************************
	
	private String markerType = Constants.MARKERTYPE_VALUEBINDINGS;
	
	public IMarker createMarker(Element elt, String criticality, String msg){
		IResource r = null;
		URI eUri = elt.eResource().getURI();
		
		if (eUri.isPlatformResource()) {
			String platformString = eUri.toPlatformString(true);
			r = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
		}
		try {
			IMarker marker = r.createMarker(markerType);
			marker.setAttribute(IMarker.MESSAGE, msg);
			Integer crit = null;
			if ( criticality.equals("error") ) 	{ crit = IMarker.SEVERITY_ERROR;	}
			else 								{ crit = IMarker.SEVERITY_INFO; }
			marker.setAttribute(IMarker.SEVERITY, crit);
			
			// set the id
			String id = "";
			Resource xmiResource = elt.eResource();
			if( xmiResource != null ) {
				id = ((XMLResource) xmiResource).getID(elt);
			}
			marker.setAttribute(IMarker.SOURCE_ID, id);
			
			if (elt instanceof NamedElement) {
				marker.setAttribute(IMarker.LOCATION, ((NamedElement)elt).getQualifiedName());	
			}
			else{
				marker.setAttribute(IMarker.LOCATION, elt.toString());
			}
			marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(elt).toString());//elt.eResource().getURI().toString());

			return marker;
			
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}


	public void deleteValidationMarkers(IProject iProject) {
		IMarker[] markers = null;
		try {
			if (iProject != null) {
				markers = iProject.findMarkers(markerType, true, IResource.DEPTH_INFINITE);
				for (IMarker marker : markers) {
					marker.delete();
				}
			}

		} catch (CoreException e) {
			//e.printStackTrace();
		}
	}
	
	
	// Utls *******************************************
	
	private String getOperationString(){
		if (storeLocation != null) {
			Stereotype stereotype = storeLocation.getAppliedStereotype(stereotypeQName);
			if (stereotype != null) {
				Object object = storeLocation.getValue(stereotype, Constants.propertyName_operation);
				if (object instanceof String) {
					return (String)object;
				}
			}
		}
		return "";
	}
	
	// Getters ****************************************
	public List<TreeObject> getClients() {
		return clients;
	}

	public List<TreeObject> getProviders() {
		return providers;
	}

	
	public List<TreeObject> getMediators() {
		return mediators;
	}

	
//	// Progress monitor ****************************************
//	
//	// The total sleep time
//	private static final int TOTAL_TIME = 100;
//	// The increment sleep time
//	private static final int INCREMENT = 10;
//	// process time´is unknown
//	private boolean indeterminate = true; 
//	
//	private String progressMonitorTitle = "Value Bindings Operation Validation";
//	private String monitorText1 = "Collecting data ... ";
//	private String monitorText2 = "Validating operations... ";
//	
//	@Override
//	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
//		
//		monitor.beginTask(progressMonitorTitle + " is running." , indeterminate ? IProgressMonitor.UNKNOWN : TOTAL_TIME);
//	    for (int total = 0; total < TOTAL_TIME && !monitor.isCanceled(); total += INCREMENT) {
//	      Thread.sleep(INCREMENT);
//	      monitor.worked(INCREMENT);
//	      if (total == TOTAL_TIME / 10) monitor.subTask(monitorText1);
//	      if (total == TOTAL_TIME / 2) monitor.subTask(monitorText2);
//	    }
//	    monitor.done();
//	    if (monitor.isCanceled()){
//	    	throw new InterruptedException(progressMonitorTitle + " was cancelled.");
//	    }   
//	}
}

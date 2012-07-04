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
package org.openmodelica.modelicaml.gen.modelica.listeners;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.papyrus.core.listenerservice.IPapyrusListener;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.resource.uml.ExtendedUmlModel;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.modelica.ConnectException;
import org.modelica.OMCProxy;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.validation.services.ModelicaMLOMCMarkerSupport;
import org.openmodelica.modelicaml.gen.modelica.postactions.PostGenerationForAutomaticBuild;

import fr.obeo.acceleo.chain.File;
import fr.obeo.acceleo.chain.impl.spec.CChain;
import fr.obeo.acceleo.chain.impl.spec.CFolder;
import fr.obeo.acceleo.chain.impl.spec.CLog;
import fr.obeo.acceleo.chain.impl.spec.CModel;
import fr.obeo.acceleo.gen.IGenFilter;
import fr.obeo.acceleo.gen.template.eval.LaunchManager;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving papyrus events. The class that is
 * interested in processing a papyrus event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addPapyrusListener<code> method. When
 * the papyrus event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see PapyrusEvent
 */
public class PapyrusListener implements IPapyrusListener {
//	private PapyrusMultiDiagramEditor papyrusEditor;
	
	/** The project. */
private String project = null;
	
	/** The code generation chain. */
	private CChain codeGenerationChain = null;
	
	/** The validation chain. */
	private CChain validationChain = null;
	
	/** The model file uri. */
	private String modelFileURI = null;
	
	/** The model name. */
	private String modelName = null;
	
	/** The job. */
	private static Job job;
	
	/** The proxy. */
	private static OMCProxy proxy = null;
	
	/** The uml model. */
	private ExtendedUmlModel umlModel = null;
	
	/** The UML model name. */
	private String UMLModelName = null;
	
	/** The model file path. */
	private String modelFilePath = null;
	
	/** The output folder path. */
	private String outputFolderPath = null;
	
	/** The log path. */
	private String logPath = null;
	
	/** The i project. */
	private IProject iProject = null;
	
	/** The chain code generation uri. */
	private URI chainCodeGenerationURI = null;
	
	/** The rs code generation. */
	private ResourceSet rsCodeGeneration = null;
	
	/** The r code generation. */
	private Resource rCodeGeneration = null;
	
	/** The chain validation uri. */
	private URI chainValidationURI = null;
	
	/** The rs validation. */
	private ResourceSet rsValidation = null;
	
	/** The r validation. */
	private Resource rValidation = null;

	/** The papyrus editor. */
	private PapyrusMultiDiagramEditor papyrusEditor = null;
	
	/** The uml model resource. */
	private static UMLResource umlModelResource = null;
	
	/** The filter. */
	private static IGenFilter filter = new IGenFilter() {
		public boolean filter(java.io.File script, IFile targetFile,
				EObject object) throws CoreException {
			return true;
		}
	};

	/**
	 * Gets the uml resource.
	 * 
	 * @return the uml resource
	 */
	public static UMLResource getUmlResource(){
		return umlModelResource;
	}
	
	
	/**
	 * Instantiates a new papyrus listener.
	 */
	public PapyrusListener() {
		chainValidationURI = URI.createPlatformPluginURI("/org.openmodelica.modelicaml.gen.modelica/bin/validation.chain",true);
		rsValidation = new ResourceSetImpl();
		rValidation = (Resource) rsValidation.createResource(chainValidationURI);

		chainCodeGenerationURI = URI.createPlatformPluginURI("/org.openmodelica.modelicaml.gen.modelica/bin/code_generation.chain",true);
		rsCodeGeneration = new ResourceSetImpl();
		rCodeGeneration = (Resource) rsCodeGeneration.createResource(chainCodeGenerationURI);
		
		job = new Job("Loading ModelicaML model into OMC") {
			protected IStatus run(IProgressMonitor monitor) {
				
				ModelicaMLOMCMarkerSupport.deleteAllOMCMarkers(iProject);

				// get the synch. preference
				String synchronizationWithOMC = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", "synchronizationWithOMC", "files", null);
				
				// validate 
				if (synchronizationWithOMC.equals("corba")) {
					PostGenerationForAutomaticBuild.generationType = "doNotGenerateFiles";
				}
				else if (synchronizationWithOMC.equals("files")) {
					PostGenerationForAutomaticBuild.generationType = "files"; // reset to default
				}

				Boolean ValidateModelicaMLModelBeforeAutomaticCodeGeneration = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "ValidateModelicaMLModelBeforeAutomaticCodeGeneration", false, null);
				if (ValidateModelicaMLModelBeforeAutomaticCodeGeneration) {
					ModelicaMLOMCMarkerSupport.deleteValidationMarkers(iProject);
					runValidationChain(monitor);
				}

				// generate code and reload in OMC
				if (synchronizationWithOMC.equals("corba")) {
					PostGenerationForAutomaticBuild.generationType = "corba";
				}
				else if (synchronizationWithOMC.equals("files")) {
					PostGenerationForAutomaticBuild.generationType = "files"; // reset to default
				}
				
				Boolean GenerateModelicaCodeInBackgroundAfterSave = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "GenerateModelicaCodeInBackgroundAfterSave", false, null);
				if (GenerateModelicaCodeInBackgroundAfterSave) {
					
					Boolean ClearOMCBeforeReloadingCode = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "ClearOMCBeforeReloadingCode", true, null);
					if (ClearOMCBeforeReloadingCode) {
						// clear compiler
						String status = null;
						try {
							status = proxy.sendExpression("clear()");
						} catch (ConnectException e) {
							e.printStackTrace();
						}
						if (status.contains("error") || status.contains("Error") || status.contains("false")) {
							ModelicaMLOMCMarkerSupport.createOMCMarker(umlModel, "error", "Could not clear OMC.");
							System.err.println("Could not clear OMC.");
						}
					}
					
					//launch code generation
					runCodeGenerationChain(monitor);
					
					if (PostGenerationForAutomaticBuild.generationType.equals("files")) {
						Boolean LoadInOMCAfterEachCodeGeneration = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "LoadInOMCAfterEachCodeGeneration", false, null);
						if (LoadInOMCAfterEachCodeGeneration) {
							// load in OMC
							loadInOMC();
						}
					}
				}
				
				// reset generation type to default
				PostGenerationForAutomaticBuild.generationType = "files"; 
				
				return Status.OK_STATUS;
			}
		};
		
		proxy = new OMCProxy();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.papyrus.core.listenerservice.IPapyrusListener#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification notification) {
	
//		System.err.println(notification);
//		System.err.println("eventType: " + notification.getEventType());
//		System.err.println("Notifier: " + notification.getNotifier());

// ############# Examples of notifications:	start

//		CHANGE Stereotype value event
//		eventType: 1		
//		org.eclipse.emf.ecore.impl.ENotificationImpl@1ea320f (eventType: SET, position: -1, notifier: org.eclipse.emf.ecore.impl.DynamicEObjectImpl@c8084d (eClass: org.eclipse.emf.ecore.impl.EClassImpl@11dca9 (name: Component) (instanceClassName: null) (abstract: false, interface: false)), feature: org.eclipse.emf.ecore.impl.EAttributeImpl@41d471 (name: final) (ordered: false, unique: false, lowerBound: 1, upperBound: 1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: false, unsettable: false, derived: false) (iD: false), oldValue: true, newValue: false, isTouch: false, wasSet: true)

//		CHANGE Element value event
//		eventType: 22
//		org.eclipse.papyrus.diagram.common.editparts.PapyrusStereotypeListener$StereotypeCustomNotification@7ca2da (eventType: 22, position: -1, notifier: org.eclipse.uml2.uml.internal.impl.PropertyImpl@939bdb (name: source, visibility: <unset>) (isLeaf: false) (isStatic: false) (isOrdered: false, isUnique: true, isReadOnly: false) (isDerived: false, isDerivedUnion: false, aggregation: composite), feature: org.eclipse.emf.ecore.impl.EReferenceImpl@e0420b (name: eAnnotations) (ordered: true, unique: true, lowerBound: 0, upperBound: -1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: true, resolveProxies: false), oldValue: null, newValue: org.eclipse.emf.ecore.impl.DynamicEObjectImpl@c8084d (eClass: org.eclipse.emf.ecore.impl.EClassImpl@11dca9 (name: Component) (instanceClassName: null) (abstract: false, interface: false)), isTouch: false, wasSet: false)
		
// 		SAVE the UML model event
//		org.eclipse.emf.common.notify.impl.NotifyingListImpl$1@3c56d2 (eventType: REMOVE_MANY, position: -1, notifier: org.eclipse.uml2.uml.internal.resource.UMLResourceImpl@e5f1d uri='platform:/resource/modelicaml.example.twotankssystem/model.uml', feature: null, oldValue: [], newValue: null, isTouch: false, wasSet: false)
//		eventType: 1
//		org.eclipse.emf.ecore.resource.impl.ResourceImpl$11@1bf73a9 (eventType: SET, touch: true, position: -1, notifier: org.eclipse.uml2.uml.internal.resource.UMLResourceImpl@e5f1d uri='platform:/resource/modelicaml.example.twotankssystem/model.uml', feature: null, oldValue: false, newValue: false, isTouch: true, wasSet: false)
//		org.eclipse.emf.ecore.resource.impl.ResourceImpl$3@fd379f (eventType: SET, position: -1, notifier: org.eclipse.uml2.uml.internal.resource.UMLResourceImpl@e5f1d uri='platform:/resource/modelicaml.example.twotankssystem/model.uml', feature: null, oldValue: 1297337319666, newValue: 1297345056911, isTouch: false, wasSet: false)

// ############# Examples of notifications: end

// 		TODO: for any change get the modified UML element and find its Class that is a Modelica "stored definition".
// 		TODO: add the qName of the stored definition to HashSet
		
//		Object o = notification.getNotifier();
//		if (o instanceof NamedElement) {
//			NamedElement classUnit = null;
//			NamedElement storedDefinition = null;
//			
//			if (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
//				IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
//				papyrusEditor=((PapyrusMultiDiagramEditor)editorPart);
//				
//				if (papyrusEditor.isDirty()) { // strange, the isDirty() method gives true after the model is saved and false if the model has changes ...
//					classUnit = findModelicaClassUnit((NamedElement)o);
//					System.err.println("classUnit: " + classUnit.getName());
//					
//					if (classUnit != null) {
//						storedDefinition = findModelicaStoredDefinitionUnit(classUnit);
//					}
//					if (storedDefinition != null ) {
//						System.err.println("storedDefinition: " + storedDefinition.getName() + ".mo");
//						listOfAffectedElements.add(storedDefinition);
//					}
//				}
//			}
//		}
		

		
		if (notification.getNotifier() instanceof UMLResource) {
			umlModelResource = (UMLResource) notification.getNotifier();
			
			umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();

			if (umlModel != null) {
				//String projectName = umlModel.getResourceURI().segment(1);
				String projectName = umlModel.getResource().getURI().segment(1);
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IWorkspaceRoot root = workspace.getRoot();
				iProject = root.getProject(projectName);
				
//				System.err.println("n.isModified(): " + umlModelResource.isModified());
//				System.err.println("Current model: " + UmlUtils.getUmlModel().getResourceURI());
//				System.err.println("Modified model U " + r.getURI());

				if (UmlUtils.getUmlModel().getResourceURI().equals( ((UMLResource) notification.getNotifier()).getURI() ) ) {

					Boolean GenerateModelicaCodeInBackgroundAfterSave = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "GenerateModelicaCodeInBackgroundAfterSave", false, null);
					
					if ( GenerateModelicaCodeInBackgroundAfterSave ) {
						
						// get the Papyrus model in order to make sure that the model is not being saved, hence is not dirty anymore.
						if (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
							IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
							papyrusEditor = ((PapyrusMultiDiagramEditor)editorPart);
						}
						
						prepareValidationAndCodeGenerationChains();
						
						if (job.getState() != Job.NONE ) {
							job.cancel();
						}
						job.setPriority(Job.BUILD);
						//job.setUser(true);
						job.schedule(1000); // wait a second in order to let Papyrus time to save the model.
					}
				}
				
			}
		}
	}
	
	
	/**
	 * Prepare validation and code generation chains.
	 */
	private void prepareValidationAndCodeGenerationChains(){
		//UmlModel umlModel = UmlUtils.getUmlModel();
		
//		umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();
//		//UMLModelName = null;
//		
//		modelFileURI = umlModel.getResourceURI().toString();
//		modelName = umlModel.getResourceURI().lastSegment();
//		project = umlModel.getResourceURI().path().replace(modelName, "").replace("/resource/", "");
//
//		// parameter values for chains
//		modelFilePath = modelFileURI.replace("platform:/resource/", "");
//		outputFolderPath = project;
//		logPath = project + "/errors.log";
		
		UmlModel umlModel = UmlUtils.getUmlModel();
		modelFileURI = umlModel.getResourceURI().toPlatformString(true);

		modelName = umlModel.getResourceURI().lastSegment();
//		project = umlModel.getResourceURI().path().replace(modelName, "").replace("/resource/", "");
		project = umlModel.getResource().getURI().segment(1);
		
		String modelFilePath = modelFileURI.replace("platform:/resource/", "");
		String outputFolderPath = project;
		String logPath = project + "/errors.log";
		
		
// ########### Code Generation
//			URI chainCodeGenerationURI = URI.createPlatformPluginURI("/org.openmodelica.modelicaml.gen.modelica/bin/code_generation.chain",true);
//			ResourceSet rsCodeGeneration = new ResourceSetImpl();
//			Resource rCodeGeneration = (Resource) rsCodeGeneration.createResource(chainCodeGenerationURI);

		// load chains
		try {
			rCodeGeneration.load(null);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		codeGenerationChain = (CChain) rCodeGeneration.getContents().get(0);
		
		// Don't create Parameter Files... simply set the correct path into them
		EList<File> files = codeGenerationChain.getParametersFiles();
		for (File file : files) {
			if (file instanceof CModel) {
				file.setPath(modelFilePath);
			} else if (file instanceof CFolder) {
				file.setPath(outputFolderPath);
			} else if (file instanceof CLog) {
				file.setPath(logPath);
			}
		}

		
		// ########### Validation
		Boolean ValidateModelicaMLModelBeforeAutomaticCodeGeneration = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "ValidateModelicaMLModelBeforeAutomaticCodeGeneration", false, null);
		if (ValidateModelicaMLModelBeforeAutomaticCodeGeneration) {
//				URI chainValidationURI = URI.createPlatformPluginURI("/org.openmodelica.modelicaml.gen.modelica/bin/validation.chain",true);
//				ResourceSet rsValidation = new ResourceSetImpl();
//				Resource rValidation = (Resource) rsValidation.createResource(chainValidationURI);
			
			try {
				rValidation.load(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			validationChain = (CChain) rValidation.getContents().get(0);
			
			// Validation Chain
			// Don't create Parameter Files... simply set the correct path into them
			EList<File> filesValidationChain = validationChain.getParametersFiles();
			for (File file : filesValidationChain) {
				if (file instanceof CModel) {
					file.setPath(modelFilePath);
				} else if (file instanceof CFolder) {
					file.setPath(outputFolderPath);
				} else if (file instanceof CLog) {
					file.setPath(logPath);
				}
			}
		}
		
	}
		
	
	/**
	 * Load in omc.
	 */
	private void loadInOMC(){
		String status, errorString = null;

		try {
//			status = proxy.sendExpression("clear()");
//			//proxy.sendExpression("cd(\"" + dir + "\")");
//			
//			if (status == null) {
//				System.err.println("No connection to OMC! ");
//				//MoldelicaMLSimulationMarkterCreator.modelicaMLSimulationAlert(elt, "error", "No connection to OMC! ");
//			}
			
			
			
			EObject modelElement = null; 
			if (umlModel != null) {
				modelElement = umlModel.getResource().getContents().get(0);
				if (modelElement instanceof Model) {
					UMLModelName = StringUtls.replaceSpecChar(((Model)modelElement).getName());
				}
			}
			
			String packageMoFilePath = null;
			if (UMLModelName != null) {
				//String projectName = umlModel.getResourceURI().path().replace(modelName, "").replace("/resource/", "");
				String projectName = umlModel.getResource().getURI().segment(1);
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IWorkspaceRoot root = workspace.getRoot();
				iProject = root.getProject(projectName);
				String projectPath = iProject.getLocationURI().toString().replaceFirst("file:\\/", "");
				packageMoFilePath = projectPath+"/code-gen/"+ UMLModelName + "/" +"package.mo";

				//ModelicaMLOMCMarkerSupport.deleteAllOMCMarkers(iProject);
			}
			
			
			if (packageMoFilePath != null) {
				
				Boolean ClearOMCBeforeReloadingCode = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "ClearOMCBeforeReloadingCode", true, null);
				if (ClearOMCBeforeReloadingCode) {
					status = proxy.sendExpression("clear()");
					//System.err.println("status for clear: " + status);
					if (status.contains("error") || status.contains("Error") || status.contains("false")) {
						ModelicaMLOMCMarkerSupport.createOMCMarker((Model)modelElement, "error", errorString);
						System.err.println("Cannot clear OMC before loading " + packageMoFilePath + ".");
					}
				}
				
				status = proxy.sendExpression("loadFile(\"" + packageMoFilePath + "\")");
				if (status.contains("error") || status.contains("Error") || status.contains("false")) {
					ModelicaMLOMCMarkerSupport.createOMCMarker((Model)modelElement, "error", errorString);
					System.err.println("Cannot find the package " + packageMoFilePath + "!");
				}
				
				errorString = proxy.sendExpression("getErrorString()");
				if (modelElement != null) {
					if (!errorString.trim().replaceAll("\"\"", "").equals("")) {
						// generate an error marker
						ModelicaMLOMCMarkerSupport.createOMCMarker((Model)modelElement, "error", errorString);
					}else {
						// generate an info marker
						ModelicaMLOMCMarkerSupport.createOMCMarker((Model)modelElement, "", ((Model)modelElement).getName() + " is loaded in OMC.");
					}	
				}
			}
			else {
				System.err.println("Cannot load the model in OMC. Missing the path to the package.mo");
			}
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Run validation chain.
	 * 
	 * @param monitor
	 *            the monitor
	 */
	public void runValidationChain(IProgressMonitor monitor) {
		// delete validation markers
//		if (iProject != null) {
//			IMarker[] markers = null;
//			try {
//				markers = iProject.findMarkers(null, true,IResource.DEPTH_INFINITE);
//
//				for (IMarker marker : markers) {
//					if (marker.getType().equals("org.openmodelica.modelicaml.modelicamlMarker.validation")) {
//						marker.delete();
//					}
//				}
//			} catch (CoreException e) {
//				//e.printStackTrace();
//			}
//		}
		
//			System.err.println("papyrusEditor.isDirty(): " + papyrusEditor.isDirty());
		if (papyrusEditor != null && !papyrusEditor.isDirty()) {
//			if (umlModelResource != null && !umlModelResource.isModified()) { 
			
			// OBSOLETE: validation is replaced by EMF Validation. Remove this code.
			// run validation chain
//				validationChain.launch(filter, monitor, LaunchManager.create("run", true));
		}
		else {
			//System.err.println("Did not launch the (validation) chain. Waiting until the uml model is saved.");
		}
	}
	
	/**
	 * Run code generation chain.
	 * 
	 * @param monitor
	 *            the monitor
	 */
	public void runCodeGenerationChain(IProgressMonitor monitor) {
		try {
			if (papyrusEditor != null && !papyrusEditor.isDirty()) { // strange, the isDirty() method gives true after the model is saved and false if the model has changes ...
//			if (umlModelResource != null && !umlModelResource.isModified()) {	
				
				if (ModelicaMLServices.regenerateCode(umlModel.getResource())) {
					
					// stamp before generating code 
					Long timeStamp = System.currentTimeMillis();
					ModelicaMLServices.codeGenerationStamp.put(umlModel.getResource(), timeStamp);

					// same the model in order to make sure that the code is generated from the latest version
//					ModelicaMLServices.saveModel(umlModel);

					// run code generation  chain
					codeGenerationChain.launch(filter, monitor, LaunchManager.create("run", true));
					
					// TODO: remove this when file encoding for generated code files is enforced to UTF-8
					ModelicaMLServices.generatePackageEncodingFile(project);
				}

			}
			else {
				//System.err.println("Did not launch the (code generation) chain. Waiting until the uml model is saved.");
			}
			
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Find modelica class unit.
	 * 
	 * @param element
	 *            the element
	 * @return the named element
	 */
	private NamedElement findModelicaClassUnit(NamedElement element) {
		NamedElement classUnit = null;
		if (element instanceof FunctionBehavior) {
			return (FunctionBehavior)element; 
		}
		else if (element instanceof Class && !(element instanceof Behavior)) {
			return (Class)element; 
		}
		else {
			Element owner = element.getOwner();
			if (owner != null && owner instanceof NamedElement) {
				classUnit = findModelicaClassUnit((NamedElement)owner);
			}
		}
		return classUnit;
	}
	
	/**
	 * Find modelica stored definition unit.
	 * 
	 * @param element
	 *            the element
	 * @return the named element
	 */
	private NamedElement findModelicaStoredDefinitionUnit(NamedElement element) {
		NamedElement storedDefinition = null;
		Element owner = element.getOwner();
		if (owner != null) {
			if (owner instanceof NamedElement) {
				if ( isModelicaStoredDefinition( (NamedElement)owner ) ) {
					return (NamedElement)owner; 
				}
				else {
					storedDefinition = findModelicaStoredDefinitionUnit( (NamedElement)owner );
				}
			}
		}
		return storedDefinition;
	}
	
	/**
	 * Checks if is modelica stored definition.
	 * 
	 * @param element
	 *            the element
	 * @return the boolean
	 */
	private Boolean isModelicaStoredDefinition(NamedElement element){
//		<%if (	ownerIsAValidPackageFolder == "true"
//			&& isModelicaRestrictedClass == "true" 
//			
//			&& isProtected == "false"
//			&& isRedeclare == "false"
//			&& isReplaceable == "false"
//			&& isPartialDerivativeFunction == "false"
//			){%>true<%}else{%>false<%}%>
		
		
//		<%script type="uml.Classifier" name="isProtected" post="trim()"%>
//		<%if (filter("Class").visibility == "protected"){%>true<%}else{%>false<%}%><%-- NOTE: 'public' is default. --%>
//
//		<%script type="uml.NamedElement" name="isRedeclare" post="trim()"%>
//		<%if ( clientDependency[hasStereotype(getProperty("s_redeclare"))] != null ){%>true<%}else{%>false<%}%>
//
//		<%script type="uml.NamedElement" name="isReplaceable" post="trim()"%>
//		<%if ( isSetModelicaClassStereotypeValueBoolean("s_p_replaceable") == "true" ){%>true<%}else{%>false<%}%>
//
//		<%script type="uml.NamedElement" name="isPartialDerivativeFunction" post="trim()"%>
//		<%if (clientDependency.nFirst().hasStereotype(getProperty("s_partialDerivativeFunction"))){%>true<%}else{%>false<%}%>

		
		if ( ownerIsAValidPackageFolder(element) 
				&& isModelicaRestrictedClass(element)
				&& !isProtected(element)
			) { // TODO: implement isRedeclare, isReplaceable, isPartialDerivativeFunction
			return true;
		}
		
//		//TODO: implement! -> use the common plugin!
//		if (element instanceof FunctionBehavior ) {
//			return true;
//		}
//		else if (element instanceof Class && !(element instanceof Behavior)) {
//			return true;
//		}
//		else if (element instanceof Model || element instanceof Package) {
//			
//		}
		return false;
	}
	
	
	// TODO: implement isRedeclare, isReplaceable, isPartialDerivativeFunction
	
	
	/**
	 * Checks if is protected.
	 * 
	 * @param element
	 *            the element
	 * @return the boolean
	 */
	private Boolean isProtected(NamedElement element) {
		if (element.getVisibility().getLiteral().equals("protected")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if is modelica restricted class.
	 * 
	 * @param element
	 *            the element
	 * @return the boolean
	 */
	private Boolean isModelicaRestrictedClass(NamedElement element){
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::ModelicaClass") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::ModelicaPackage") != null){ return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::Operator") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::Model") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::Block") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::Connector") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::Record") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::Type") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::ModelicaEnumeration") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::Function") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::ModelicaEnumeration") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::CalculationModel") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::Requirement") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::RequirementRepresentative") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::MeasureOfEffectiveness") != null) { return true; }
		if (element.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::ModelicaModelReference") != null) { return true; }		
		return false;
	}
	
	/**
	 * Owner is a valid package folder.
	 * 
	 * @param element
	 *            the element
	 * @return the boolean
	 */
	private Boolean ownerIsAValidPackageFolder(NamedElement element){
//		<%if ( owner.eClass().name == "Model" || owner.eClass().name == "Package" ){%>true
//			<%}else{%>
//				<%if ( owner.hasStereotype(getProperty("s_package")) || owner.hasStereotype(getProperty("s_operator")) ){%>
//					<%if (owner.owner.eClass().name == "Model" || owner.owner.eClass().name == "Package"){%>true
//					<%}else{%>
//						<%if ( owner.owner.hasStereotype(getProperty("s_package")) || owner.owner.hasStereotype(getProperty("s_operator")) ){%>true
//						<%}else{%>false<%}%>
//					<%}%>
//				<%}else{%>false<%}%>
//			<%}%>
		
		Element owner = element.getOwner();
		if (owner != null) {
			
			if (owner instanceof Model || owner instanceof Package) {
				return true;
			}
			else if (owner.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::ModelicaPackage") != null
					|| owner.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::Operator") != null) {
				Element owner2LevelUp = owner.getOwner();
				if (owner2LevelUp != null) {
					if (owner2LevelUp instanceof Model || owner2LevelUp instanceof Package) {
						return true;
					}
					else if (owner2LevelUp.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::ModelicaPackage") != null
							|| owner2LevelUp.getAppliedStereotype("ModelicaML::ModelicaClassConstructs::Operator") != null) {
						return true;
					}
				}
			}
		}
		return false;
	}
}

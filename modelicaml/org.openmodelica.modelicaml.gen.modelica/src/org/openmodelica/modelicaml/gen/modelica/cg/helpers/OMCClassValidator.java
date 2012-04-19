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
package org.openmodelica.modelicaml.gen.modelica.cg.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.resource.uml.ExtendedUmlModel;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.modelica.ConnectException;
import org.modelica.OMCProxy;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
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

public class OMCClassValidator {
	/** The project. */
	private String project = null;
	
	/** The code generation chain. */
	private CChain codeGenerationChain = null;
	
	/** The model file uri. */
	private String modelFileURI = null;
	
//	/** The model name. */
//	private String modelName = null;
	
	/** The proxy. */
	private OMCProxy proxy = null;
	
	/** The uml model. */
	private ExtendedUmlModel umlModel = null;
	
	/** The UML model name. */
	private String UMLModelName = null;
	
	/** The model file path. */
//	private String modelFilePath = null;
	
	/** The output folder path. */
//	private String outputFolderPath = null;
	
	/** The log path. */
//	private String logPath = null;
	
	/** The i project. */
	private IProject iProject = null;
	
	/** The chain code generation uri. */
	private URI chainCodeGenerationURI = null;
	
	/** The rs code generation. */
	private ResourceSet rsCodeGeneration = null;
	
	/** The r code generation. */
	private Resource rCodeGeneration = null;

//	private Element classToBeValidated = null;
	
	private String dotPath_classToBeValidated = "";
	
	private Job job = new Job("Loading ModelicaML model into OMC") {
		
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

			// generate code and reload in OMC
			if (synchronizationWithOMC.equals("corba")) {
				PostGenerationForAutomaticBuild.generationType = "corba";
			}
			else if (synchronizationWithOMC.equals("files")) {
				PostGenerationForAutomaticBuild.generationType = "files"; // reset to default
			}
			
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
			try {
				codeGenerationChain.launch(filter, monitor, LaunchManager.create("run", true));

				// TODO: remove this when file encoding for generated code files is enforced to UTF-8
				ModelicaMLServices.generatePackageEncodingFile(project);

			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			loadAndCheckInOMC();
			
			// reset generation type to default
			PostGenerationForAutomaticBuild.generationType = "files"; 
			return Status.OK_STATUS;
		}
		
		
	};
	
	JobChangeAdapter jobChangeAdapter = new JobChangeAdapter() {
		public void done(IJobChangeEvent event) {
            if (event.getResult().isOK())
            	openDialog();
            else
            	openDialog();
            }
         };
         
	
	/** The filter. */
	private IGenFilter filter = new IGenFilter() {
		public boolean filter(java.io.File script, IFile targetFile,
				EObject object) throws CoreException {
			return true;
		}
	};

	
	
	
	
	public OMCClassValidator(Element classToBeValidated) {
		if (classToBeValidated instanceof Class) {
			
//			this.classToBeValidated = classToBeValidated;
			this.dotPath_classToBeValidated = StringUtls.replaceSpecCharExceptThis(((Classifier)classToBeValidated).getQualifiedName().replaceAll("::", "."), "\\.");

		}
		else {
			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Model Valdation with OMC", "The select element must be a UML Class! ");
		}
	}
	
	
	public void validate(){
		chainCodeGenerationURI = URI.createPlatformPluginURI("/org.openmodelica.modelicaml.gen.modelica/bin/code_generation.chain",true);
		rsCodeGeneration = new ResourceSetImpl();
		rCodeGeneration = (Resource) rsCodeGeneration.createResource(chainCodeGenerationURI);
		
		proxy = new OMCProxy();
		
		umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();

		//String projectName = umlModel.getResourceURI().segment(1);
		String projectName = umlModel.getResource().getURI().segment(1);
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		iProject = root.getProject(projectName);

		prepareValidationAndCodeGenerationChains();
		
		job.addJobChangeListener(jobChangeAdapter);
		
		job.setUser(true);
		job.schedule();
	}
	
	
 
	
	private void openDialog(){
		// Use this to open a Shell in the UI thread
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				boolean isError = getLog().contains("rror") | getLog().contains("ailed");   
				DialogMessage dialog = new DialogMessage(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
						"Check Model in OMC", "Validation result for '" + dotPath_classToBeValidated + "': ", 
						getLog(), 
						isError);
		        dialog.open();
			}
		});
	}
	
	private void prepareValidationAndCodeGenerationChains(){
//		umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();
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

//		modelName = umlModel.getResourceURI().lastSegment();
//		project = umlModel.getResourceURI().path().replace(modelName, "").replace("/resource/", "");
		project = umlModel.getResource().getURI().segment(1);
		
		String modelFilePath = modelFileURI.replace("platform:/resource/", "");
		String outputFolderPath = project;
		String logPath = project + "/errors.log";
		
		// load chains
		try {
			rCodeGeneration.load(null);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		codeGenerationChain = (CChain) rCodeGeneration.getContents().get(0);
		
		// Don't create Parameter Files... simply set the correct path into them
		@SuppressWarnings("unchecked")
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
	}

	private String log = "";
	
	private void loadAndCheckInOMC(){
		String status, errorString = null;
		try {

			EObject modelElement = null; 
			if (umlModel != null) {
				modelElement = umlModel.getResource().getContents().get(0);
				if (modelElement instanceof Model) {
					UMLModelName = StringUtls.replaceSpecChar(((Model)modelElement).getName());
				}
			}
			
			String packageMoFilePath = null;
			String codeSyncFolderPath = null;
			
			if (UMLModelName != null) {
				//String projectName = umlModel.getResourceURI().path().replace(modelName, "").replace("/resource/", "");
				String projectName = umlModel.getResource().getURI().segment(1);
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IWorkspaceRoot root = workspace.getRoot();
				iProject = root.getProject(projectName);
				String projectPath = iProject.getLocationURI().toString().replaceFirst("file:\\/", "");
				packageMoFilePath = projectPath+"/"+Constants.folderName_code_gen+"/"+ UMLModelName + "/" +"package.mo";
				
				codeSyncFolderPath = projectPath+"/"+Constants.folderName_code_sync+"/"; 
				
				//ModelicaMLOMCMarkerSupport.deleteAllOMCMarkers(iProject);
			}
			
			if (packageMoFilePath != null) {
				Boolean ClearOMCBeforeReloadingCode = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "ClearOMCBeforeReloadingCode", true, null);
				if (ClearOMCBeforeReloadingCode) {
					status = proxy.sendExpression("clear()");
					setLog(status);
					//System.err.println("status for clear: " + status);
					if (status.contains("error") || status.contains("Error") || status.contains("false")) {
						ModelicaMLOMCMarkerSupport.createOMCMarker((Model)modelElement, "error", errorString);
						System.err.println("Cannot clear OMC before loading " + packageMoFilePath + ".");
					}
				}
				
				
				/*
				 * Load first the code from code-sync folder
				 */
				List<String> filesToLoad = new ArrayList<String>(); 
				filesToLoad.addAll(ModelicaMLServices.getFilesToLoad(codeSyncFolderPath));
				for (String path : filesToLoad) {
					status = proxy.sendExpression("loadFile(\"" + path + "\")");
//					status = proxy.sendExpression("loadFile(\"" + path + "\", encoding=\""+Constants.fileEncoding+"\")");
				}
				
				/*
				 * Load code from the code-gen folder
				 */
				status = proxy.sendExpression("loadFile(\"" + packageMoFilePath + "\")");
//				status = proxy.sendExpression("loadFile(\"" + packageMoFilePath + "\", encoding=\""+Constants.fileEncoding+"\")");
				setLog(status);
				
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
				
				status = proxy.sendExpression("checkModel(" + dotPath_classToBeValidated + ")");
				// get the answer from OMC 
				setLog(status);
				
				errorString = proxy.sendExpression("getErrorString()");

			}
			else {
				System.err.println("Cannot load the model in OMC. Missing the path to the package.mo");
			}
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getLog() {
		return log;
	}
}

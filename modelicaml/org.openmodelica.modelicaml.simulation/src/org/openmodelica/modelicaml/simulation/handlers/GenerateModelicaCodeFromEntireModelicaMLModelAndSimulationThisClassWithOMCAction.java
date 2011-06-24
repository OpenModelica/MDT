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
package org.openmodelica.modelicaml.simulation.handlers;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
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
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmt.modisco.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.papyrus.core.utils.EditorUtils;
import org.eclipse.papyrus.diagram.common.editparts.IUMLEditPart;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.simulation.omc.OMCCommandExecuter;
import org.openmodelica.modelicaml.simulation.omc.OMCSimulationDataStorage;
import org.openmodelica.modelicaml.simulation.omc.SimulationConfigurationWizard;

import fr.obeo.acceleo.chain.File;
import fr.obeo.acceleo.chain.impl.spec.CChain;
import fr.obeo.acceleo.chain.impl.spec.CFolder;
import fr.obeo.acceleo.chain.impl.spec.CLog;
import fr.obeo.acceleo.chain.impl.spec.CModel;
import fr.obeo.acceleo.gen.IGenFilter;
import fr.obeo.acceleo.gen.template.eval.LaunchManager;

// TODO: Auto-generated Javadoc
/**
 * The Class
 * GenerateModelicaCodeFromEntireModelicaMLModelAndSimulationThisClassWithOMCAction
 * .
 * 
 * @author rmwscham
 */
public class GenerateModelicaCodeFromEntireModelicaMLModelAndSimulationThisClassWithOMCAction extends AbstractHandler {

	// private ModelManager modelManager = null;
	/** The project. */
	private String project = null;
	
	/** The my chain. */
	private CChain myChain = null;
	
	/** The model file uri. */
	private String modelFileURI = null;
	
	/** The model name. */
	private String modelName = null;

	// NullProgressMonitor monitor = null;

	/** The plot command. */
	private String plotCommand = null;
	
	/** The sim par. */
	private String simPar = null;
	
	/** The go. */
	private Boolean go = true;
	
	/** The shell. */
	Shell shell = new Shell();
	
	
	/** The filter. */
	IGenFilter filter = new IGenFilter() {
		public boolean filter(java.io.File script, IFile targetFile,
				EObject object) throws CoreException {
			return true;
		}
	};

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		TransactionalEditingDomain editingDomain = EditorUtils
				.getTransactionalEditingDomain();
		editingDomain.getCommandStack().execute(getCommand(editingDomain));
		return null;
	}

	/**
	 * Gets the command.
	 * 
	 * @param editingDomain
	 *            the editing domain
	 * @return the command
	 */
	protected Command getCommand(TransactionalEditingDomain editingDomain) {

		Element umlElement = null;
		IStructuredSelection selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();		
		Object input = ((IStructuredSelection) selection).getFirstElement();
		
		if (input instanceof ModelElementItem) {
			EObject eObject = ((ModelElementItem)input).getEObject();
			if ( eObject instanceof Element ) {
				umlElement = (Element)eObject;
			}
		}
		else if (input instanceof IUMLEditPart) {
			umlElement = ((IUMLEditPart)input).getUMLElement();
		}
		
		UmlModel umlModel = UmlUtils.getUmlModel();
		modelFileURI = umlModel.getResourceURI().toString();

		modelName = umlModel.getResourceURI().lastSegment();
		project = umlModel.getResourceURI().path().replace(modelName, "")
				.replace("/resource/", "");

		
//		String projectName = umlModel.getResourceURI().path().replace(modelName, "").replace("/resource/", "");
		String projectName = umlModel.getResource().getURI().segment(1);
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject iProject = root.getProject(projectName);
		
		//String projectPath = umlModel.getResourceURI().toString().replaceFirst("file:/", "");
		//String projectPath = iProject.getRawLocationURI().toString().replaceFirst("file:\\/", "");
		
		String projectPath = iProject.getLocationURI().toString().replaceFirst("file:\\/", "");
		
		String modelElementQualifiedName = ((NamedElement)umlElement).getQualifiedName();
		String temp_modelElementWoSpecChar = StringUtls.replaceSpecCharExceptThis(modelElementQualifiedName, "::");
		final String modelElementDotPath = temp_modelElementWoSpecChar.replaceAll("::", ".");
		final String packageMoFilePath = projectPath+"/code-gen/"+ StringUtls.replaceSpecChar(umlElement.getModel().getName()) + "/" +"package.mo";
		final String folderPath = projectPath + "/code-gen";
		final Element modelElement = umlElement;
		
		URI chainURI = URI
				.createPlatformPluginURI(
						"/org.openmodelica.modelicaml.gen.modelica/bin/code_generation.chain",
						true);
		ResourceSet rs = new ResourceSetImpl();
		Resource r = (Resource) rs.createResource(chainURI);
		try {
			r.load(null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		myChain = (CChain) r.getContents().get(0);

		String modelFilePath = modelFileURI.replace("platform:/resource/", "");
		String outputFolderPath = project;
		String logPath = project + "/errors.log";

		// Don't create Parameter Files... simply set the correct path into them
		EList<File> files = myChain.getParametersFiles();
		for (File file : files) {
			if (file instanceof CModel) {
				file.setPath(modelFilePath);
			} else if (file instanceof CFolder) {
				file.setPath(outputFolderPath);
			} else if (file instanceof CLog) {
				file.setPath(logPath);
			}
		}
		
		Shell shell = new Shell();
	    WizardDialog wizard = new WizardDialog(shell, new SimulationConfigurationWizard(umlElement));
	    wizard.open();

	    setGo(wizard.getReturnCode());
		
	    if (go) {
			simPar = " startTime=" + OMCSimulationDataStorage.startTime
			+ ", stopTime=" + OMCSimulationDataStorage.stopTime
			+ ", tolerance=" + OMCSimulationDataStorage.tolerance
			+ ", method=" + OMCSimulationDataStorage.Solver;

			String vars = "";
			plotCommand = null;
			
			if (OMCSimulationDataStorage.selectedVarPathList.size() > 0) {
				vars = OMCSimulationDataStorage.selectedVarPathList.toString().substring(1, OMCSimulationDataStorage.selectedVarPathList.toString().length() - 1);
				plotCommand = "plot({" + vars + "})";
			}
			Job job = new Job("Modelica Simulation with OMC") {
				protected IStatus run(IProgressMonitor monitor) {
					Boolean RegenerateCodeBeforeEachSimulation = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "RegenerateCodeBeforeEachSimulation", false, null);
					if (RegenerateCodeBeforeEachSimulation) {
						runchain(monitor);
					}
					new OMCCommandExecuter(modelElement, folderPath, packageMoFilePath, modelElementDotPath, plotCommand, simPar);
					return Status.OK_STATUS;
				}
			};
			//job.setUser(true);
			job.schedule();
		}
	    

//		Job job = new Job("Modelica Code Generation") {
//			protected IStatus run(IProgressMonitor monitor) {
//				runchain(monitor);
//				return Status.OK_STATUS;
//			}
//		};
//		job.setUser(true);
//		job.schedule();
//		// run the chain

		CompoundCommand cc = new CompoundCommand("Modelica Code Generation");
		return (cc.unwrap());
	}

	/**
	 * Sets the go.
	 * 
	 * @param returnCode
	 *            the new go
	 */
	private void setGo(Integer returnCode){
		if (returnCode == 1) {
			this.go = false;
		}
		if (returnCode == 0) {
			this.go = true;
		}
	}
	
	/**
	 * Runchain.
	 * 
	 * @param monitor
	 *            the monitor
	 */
	public void runchain(IProgressMonitor monitor) {
		try {
			myChain.launch(filter, monitor, LaunchManager.create("run", true));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Checks if is enabled.
	 * 
	 * @return true, if is enabled
	 * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
	 */
	public boolean isEnabled() {
		return true;
	}

	/**
	 * Checks if is visible.
	 * 
	 * @return true, if is visible
	 */
	public boolean isVisible() {
		return true;
	}

}

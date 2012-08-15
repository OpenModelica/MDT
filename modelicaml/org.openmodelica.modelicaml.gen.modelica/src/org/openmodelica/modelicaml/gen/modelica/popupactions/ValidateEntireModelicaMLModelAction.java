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
package org.openmodelica.modelicaml.gen.modelica.popupactions;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
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
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.EditorServices;

import fr.obeo.acceleo.chain.File;
import fr.obeo.acceleo.chain.impl.spec.CChain;
import fr.obeo.acceleo.chain.impl.spec.CFolder;
import fr.obeo.acceleo.chain.impl.spec.CLog;
import fr.obeo.acceleo.chain.impl.spec.CModel;
import fr.obeo.acceleo.gen.IGenFilter;
import fr.obeo.acceleo.gen.template.eval.LaunchManager;

/**
 * The Class ValidateEntireModelicaMLModelAction.
 * 
 * @author rmwscham
 */
public class ValidateEntireModelicaMLModelAction extends AbstractHandler {

	// private ModelManager modelManager = null;
	/** The project. */
	private String project = null;
	
	/** The my chain. */
	private CChain myChain = null;
	
	/** The model file uri. */
	private String modelFileURI = null;
	
	/** The model name. */
//	private String modelName = null;

	// NullProgressMonitor monitor = null;

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
		TransactionalEditingDomain editingDomain = EditorServices.getPapyrusEditingDomain();
		editingDomain.getCommandStack().execute(getCommand(editingDomain));
		return null;
	}

	
	/**
	 * Delete marker.
	 * 
	 * @param project
	 *            the project
	 */
	public void deleteMarker(IProject project) {
		
		IMarker[] markers = null;
		try {
			markers = project.findMarkers(null, true,
					IResource.DEPTH_INFINITE);

			for (IMarker marker : markers) {
				if (marker.getType().equals(Constants.MARKERTYPE_VALIDATION_PROBLEM)) {
					marker.delete();
				}
			}

		} catch (CoreException e) {
			
		}
	}
	
	
	/**
	 * Gets the command.
	 * 
	 * @param editingDomain
	 *            the editing domain
	 * @return the command
	 */
	@SuppressWarnings("unchecked")
	protected Command getCommand(TransactionalEditingDomain editingDomain) {

		UmlModel umlModel = UmlUtils.getUmlModel();
		modelFileURI = umlModel.getResourceURI().toPlatformString(true);
		
//		modelName = umlModel.getResourceURI().lastSegment();
		//project = umlModel.getResourceURI().path().replace(modelName, "").replace("/resource/", "");
		project = umlModel.getResource().getURI().segment(1);
		
		// delete markers: start
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject iProject = root.getProject(project);
		
		deleteMarker(iProject);// delete markers: end
		
		URI chainURI = URI.createPlatformPluginURI( "/org.openmodelica.modelicaml.gen.modelica/bin/validation.chain", true);
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

		Job job = new Job("ModelicaML Model Validation") {
			protected IStatus run(IProgressMonitor monitor) {
				runchain(monitor);
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.schedule();
		// run the chain

		CompoundCommand cc = new CompoundCommand("ModelicaML Model Validation");
		return (cc.unwrap());
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

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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.gen.modelica.popupactions;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
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
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.papyrus.uml.tools.model.UmlUtils;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.EditorServices;

import fr.obeo.acceleo.chain.File;
import fr.obeo.acceleo.chain.impl.spec.CChain;
import fr.obeo.acceleo.chain.impl.spec.CFolder;
import fr.obeo.acceleo.chain.impl.spec.CLog;
import fr.obeo.acceleo.chain.impl.spec.CModel;
import fr.obeo.acceleo.gen.IGenFilter;
import fr.obeo.acceleo.gen.template.eval.LaunchManager;

// TODO: Auto-generated Javadoc
/**
 * The Class GenerateModelicaCodeFromEntireModelicaMLModelAction.
 * 
 * @author rmwscham
 */
public class GenerateModelicaCodeFromEntireModelicaMLModelAction extends AbstractHandler {

	// private ModelManager modelManager = null;
	/** The project. */
	private String project = null;
	
	/** The my chain. */
	private CChain myChain = null;
	
	/** The model file uri. */
	private String modelFileURI = null;
	
	/** The model name. */
	private String modelName = null;
	
	private UmlModel umlModel;
	
	
	private Job job;
	
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
	 * Gets the command.
	 * 
	 * @param editingDomain
	 *            the editing domain
	 * @return the command
	 */
	protected Command getCommand(TransactionalEditingDomain editingDomain) {

		umlModel = UmlUtils.getUmlModel();
		modelFileURI = umlModel.getResourceURI().toPlatformString(true);

		modelName = umlModel.getResourceURI().lastSegment();
//		project = umlModel.getResourceURI().path().replace(modelName, "").replace("/resource/", "");
		project = umlModel.getResource().getURI().segment(1);
		
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
		final String outputFolderPath = project;
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
		
		// check if the model is dirty and, if it is, save it in order to make sure that code is generated from an up to date version
		IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		final PapyrusMultiDiagramEditor papyrusEditor = ((PapyrusMultiDiagramEditor)editorPart);
				
		job = new Job("Modelica Code Generation") {
			protected IStatus run(IProgressMonitor monitor) {

				if (papyrusEditor.isDirty()) {
					papyrusEditor.doSave(monitor);
				}
				
				runchain(monitor);
				
				return Status.OK_STATUS;
			}
		};
		
		
		job.setUser(true);
		job.schedule();

		CompoundCommand cc = new CompoundCommand("Modelica Code Generation");
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
			
			if (ModelicaMLServices.regenerateCode(umlModel.getResource())) {
				
				// stamp before generating code 
				Long timeStamp = System.currentTimeMillis();
				ModelicaMLServices.codeGenerationStamp.put(umlModel.getResource(), timeStamp);
				
//				// same the model in order to make sure that the code is generated from the latest version
//				ModelicaMLServices.saveModel(umlModel);

				myChain.launch(filter, monitor, LaunchManager.create("run", true));
				
				// TODO: remove this when file encoding for generated code files is enforced to UTF-8
				ModelicaMLServices.generatePackageEncodingFile(project);
			}

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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}

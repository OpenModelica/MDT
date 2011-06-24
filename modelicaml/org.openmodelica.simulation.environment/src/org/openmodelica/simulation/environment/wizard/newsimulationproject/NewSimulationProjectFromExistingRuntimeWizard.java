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
package org.openmodelica.simulation.environment.wizard.newsimulationproject;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.openmodelica.simulation.core.init_txt_handling.InitTXT;
import org.openmodelica.simulation.core.init_txt_handling.SimulationInit_TXT_reader;
import org.openmodelica.simulation.core.models.simulation.SimulationProject;
import org.openmodelica.simulation.core.xml.generator.SimulationInitial_XML_generator;
import org.openmodelica.simulation.core.xml.generator.SimulationProject_XML_generator;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * This is a new file wizard. Its role is to create a new simulation project file 
 * resource in the provided container. The wizard creates a project folder with a user specified name. 
 * 
 * This wizard will be used if a simulation runtime (model.exe) and its corresponding initial file (model_init.txt) is available yet.
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */
public class NewSimulationProjectFromExistingRuntimeWizard extends Wizard
		implements INewWizard {

	/** The page1. */
	private NewSimulationProjectExistingModelExecutableWizardPage page1;
	
	/** The page2. */
	private NewSimulationProject_ModelicaModelWizardPage page2;
	
	/** The selection. */
	private ISelection selection;
	
	/** The project folder. */
	private File projectFolder;
	
	/** The project id. */
	private String projectID;
	
	/** The omc error string. */
	private String omcErrorString;

	/**
	 * Constructor for NewSimulationProjectFromExistingCodeWizard.
	 */
	public NewSimulationProjectFromExistingRuntimeWizard() {
		super();
		this.setWindowTitle("New Modelica Model Wizard");
		omcErrorString = "";
		/**
		 * Create org.openmodelica.simulation.projects folder into workspace
		 */
		File workspaceFolder = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
		File projectParentFolder = new File(workspaceFolder.getPath() + "/org.openmodelica.simulation.projects");
		if(projectParentFolder.mkdir()){
			//TODO [20100603] Check if folder has been created successfully
		}
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Adding the page1 to the wizard.
	 */

	public void addPages() {
		page1 = new NewSimulationProjectExistingModelExecutableWizardPage(selection);
		addPage(page1);
		page2 = new NewSimulationProject_ModelicaModelWizardPage();
		addPage(page2);
//		getContainer().getShell().setSize(400, 520);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 *
	 * @return true, if successful
	 */
	public boolean performFinish() {
		projectID = "123"; //TODO [20100506] Dynamisch machen

		try {
			new ProgressMonitorDialog(getContainer().getShell()).run(true, true,
					new IRunnableWithProgress() {
						@Override
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException,
								InterruptedException {
							monitor.beginTask("Create a new Simulation Project...",
									3);
//									IProgressMonitor.UNKNOWN);
							for (int total = 1; total < 4; total++) {
								if (monitor.isCanceled())
									throw new OperationCanceledException();
								
								switch (total) {
								case 1:
									monitor.setTaskName("Step: " + total + ", Creating Simulation Project...");
									getShell().getDisplay().asyncExec(new Runnable() {
										public void run() {
											getProjectPageData();
										}
									});
									monitor.worked(1);
									break;
								case 2:
									monitor.setTaskName("Step: " + total + ", Checking Executable and Initial File...");
									getShell().getDisplay().asyncExec(new Runnable() {
										public void run() {
											checkModelExecutableAndInitFile();
										}
									});
									monitor.worked(2);
									break;
								case 3:
									monitor.setTaskName("Step: " + total + ", Creating Modelica Model...");
									getShell().getDisplay().asyncExec(new Runnable() {
										public void run() {
											try {
												getModelPageData();
													} catch (Exception e) {
														String updateOMC_OMI = "\n\nPlease try again or check for an update of this plug-in";

														if (e instanceof Exception)
															omcErrorString = e
																	.getMessage()
																	+ updateOMC_OMI;
														else if (e instanceof java.util.NoSuchElementException)
															omcErrorString = "Empty line detected"
																	+ updateOMC_OMI;
														else if (e instanceof NumberFormatException)
															omcErrorString = "Element is not a number. "
																	+ e.getMessage()
																	+ updateOMC_OMI;
													}
										}
									});
									monitor.worked(3);
									break;
								default:
									break;
								}
								
							}
							monitor.done();
						}
					});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Abbruch1");
		} catch (OperationCanceledException e){
			System.out.println("Abbruch2");
		}
		 IWorkbench workbench = PlatformUI.getWorkbench();
		   try {
			   Activator.getSimulationProjectCenter().changeInWorkspace();
			workbench.showPerspective("org.openmodelica.simulation.environment.perspective.project.ProjectDataPerspective", 
			      workbench.getActiveWorkbenchWindow());
			
			if (!omcErrorString.equals("")) {
				MessageDialog
						.openError(getShell(), "Error using OMC",
								"Could not create model.\nOMC Error: "
										+ omcErrorString);

				// DELETE CREATED PROJECT FOLDER
				deleteCreatedProject();
			}
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Reads the user specified data from the first new file wizard page to create a simulation project.
	 *
	 * @return the project page data
	 */
	private void getProjectPageData(){
		final String projectName = page1.getProjectName();
		final String projectVersion = page1.getProjectVersion();
		final String projectAuthor = page1.getProjectAuthor();
		final String projectCompany = page1.getProjectCompany();
		final String projectComment = page1.getProjectComment();
		final File projectParentFolder = page1.getProjectParentFolder();
		
//		File workspaceFolder = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
//		File projectParentFolder = handleBrowse( new File( workspaceFolder.getPath() + "\\org.openmodelica.simulation.projects"));
		
		projectFolder = new File(projectParentFolder.getPath() + "/" + projectName);
		projectFolder.mkdir();
		(new File (projectFolder.getPath() + "/SimulationSessionConfigurations")).mkdir();

		//Create a SimulationProject Object
		SimulationProject simproj = new SimulationProject(projectName, projectID, projectVersion, projectAuthor, projectCompany, projectComment, projectFolder.getPath());
		//Generate the Simulation Project XML and store it into the project folder
		try {
			SimulationProject_XML_generator.createXML(simproj, projectFolder.getAbsolutePath());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Checks the specified model executable file and the corresponding initial file. 
	 * If matched it will copy both to the specified project folder
	 */
	private void checkModelExecutableAndInitFile() {

		/*
		 * Check Model.exe and init.txt
		 * Is readable,...
		 */
		
		//Copy model.exe and init.txt to the project folder
		String srcFile; 
		String destFile;
		
		srcFile= page1.getModelExeFile();
		destFile= projectFolder.getPath() + "/"
				+ (new File(page1.getModelExeFile()).getName());
		org.openmodelica.simulation.environment.filehandling.cp
				.copyFile(srcFile, destFile);
		
		srcFile = page1.getModelInitTxtFile();
		destFile= projectFolder.getPath() + "/"
		+ (new File(page1.getModelInitTxtFile()).getName());
		org.openmodelica.simulation.environment.filehandling.cp
		.copyFile(srcFile, destFile);
	}
	
	/**
	 * After compiling the model, OMC will generate an init file. This File can
	 * be used to create the a needed initial.xml file with all available
	 * variables and parameters. Also some additional information from the
	 * second page of the create model wizard will be used.
	 *
	 * @return the model page data
	 * @throws Exception the exception
	 */
	private void getModelPageData() throws Exception{
	final String modelVersion = page2.getModelVersion();
		final String modelAuthor = page2.getModelAuthor();
		final String modelCompany = page2.getModelCompany();
		final String modelComment = page2.getModelComment();
		
		InitTXT initTXTFile = SimulationInit_TXT_reader.readInit(page1.getModelInitTxtFile());
		
		Scanner sc = new Scanner((new File(page1.getModelExeFile())).getName());
		sc.useDelimiter(".exe");
		String fullqualifiedname = sc.next();
		String mainclassName = fullqualifiedname.substring(fullqualifiedname.lastIndexOf('.') +1);
		
		try {
			SimulationInitial_XML_generator.createXML(mainclassName, projectID, initTXTFile, projectFolder.getPath(), modelVersion, modelAuthor, modelCompany, modelComment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes a project if the compilation failed.
	 */
	private void deleteCreatedProject() {

			deleteAll(projectFolder);

			projectFolder.delete();
			Activator.getSimulationProjectCenter().setSelectedSimulationProject(null);
			Activator.getSimulationProjectCenter().changeInWorkspace();
			
		}
	
	/**
	 * Recursive method to delete contend files from a Directory.
	 *
	 * @param f the f
	 */
	private void deleteAll(File f) {
		if (f.isFile())
			f.delete();
		else if (f.isDirectory() && f.listFiles().length == 0)
			f.delete();
		else {
			for (File childF : f.listFiles())
				deleteAll(childF);
			f.delete();
		}
	}	
	
	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 *
	 * @param workbench the workbench
	 * @param selection the selection
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
	
	/**
	 * The Class OnlyExtension.
	 *
	 * @see #FilenameFilter
	 * @author Parham Vasaiely
	 */
	public class OnlyExtension implements FilenameFilter {
		
		/** The ext. */
		String ext;
		
		/**
		 * Instantiates a new only extension.
		 *
		 * @param ext the ext
		 */
		public OnlyExtension(String ext) {
		this.ext = ext;
		}
		
		/* (non-Javadoc)
		 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
		 */
		public boolean accept(File dir, String name) {
		return name.endsWith(ext);
		}
	}
}
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
package org.openmodelica.simulation.environment.wizard.sessionconfiguration.noninteractive;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.openmodelica.simulation.core.init_handling.InitData;
import org.openmodelica.simulation.core.init_handling.SimulationInit_XML_reader;
import org.openmodelica.simulation.core.init_handling.SimulationInit_XML_writer;
import org.openmodelica.simulation.core.models.modelica.ModelicaModel;
import org.openmodelica.simulation.core.models.simulation.SimulationProject;
import org.openmodelica.simulation.core.models.simulation.SimulationSessionConfiguration;
import org.openmodelica.simulation.core.models.simulation.SimulationSetting;
import org.openmodelica.simulation.core.models.simulation.SimulationSettingNonInteractive;
import org.openmodelica.simulation.core.xml.generator.SimulationConfiguration_XML_generator;
import org.openmodelica.simulation.core.xml.generator.SimulationSetting_XML_generator;
import org.openmodelica.simulation.environment.Activator;
import org.openmodelica.simulation.environment.core.SimulationProjectCenter;
import org.openmodelica.simulation.environment.preferences.page.WorkbenchPreferencePageDefaultConfiguration;

/**
 * Wizard to create or modify Simulation Session Configuration files for non interactive simulations.
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class SessionConfiguration_NonInteractiveWizard extends Wizard{

	private SessionConfiguration_ChangeSetupNonInteractiveWizardPage one;
	private SessionConfiguration_ChangeModelNonInteractiveWizardPage two;
	
	public SimulationSetting ss;
	public ModelicaModel mm;
	
	/**
	 * Use this variable to setup the wizard page
	 * default = Create a new setup using the default settings from properties
	 * change = Change an existing configuration using a sample configuration
	 * use = Create a new setup using an existing configuration as sample
	 */
	String option = "";

	/**
	 * Use this constructor to create a new session configuration using the initial model and default setup from preferences
	 * @param simulationType The type will be needed to display different pages. "i"=interactive, "ni"=not interactive
	 */
	public SessionConfiguration_NonInteractiveWizard() {
		super();
		setWindowTitle("Simulation Session Configuration");
		// ss =
		// SimulationSetting_XML_reader.readFromXML("F:/Eclipse_Workspaces/runtime-EclipseApplication/org.openmodelica.simulation.projects/Project001/SimulationSessionConfigurations/SessionConfig001/SimSetting.xml");
		ss = createDefaultNonInteractiveSettingFromPreferences();
		mm = Activator.getSimulationProjectCenter()
				.getSelectedSimulationProject().getModelicaModel();
		option = "default";
		// addPages();
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Use this constructor to create a new session configuration or to modify one using an existing setting and configured model.
	 * @param sampleSimulationSetting existing setting (interactive or non interactive)
	 * @param sampleModelicaModel configured model
	 * @param option use the string 'change' if the given configuration file already exists and have to be modified, 'use' if a new file should be created using a sample file
	 */
	public SessionConfiguration_NonInteractiveWizard(SimulationSetting sampleSimulationSetting, ModelicaModel sampleModelicaModel, String option) {
		super();
		setWindowTitle("Simulation Session Configuration");
		ss = sampleSimulationSetting;
		mm = sampleModelicaModel;
		this.option = option;
//		addPages();
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Creates a default simulation setting object for a non interactive simulation using the data from eclipse preferences
	 * @return a new SimulationSetting object filled with default setting and model data
	 */
	private SimulationSetting createDefaultNonInteractiveSettingFromPreferences() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		
		String author = preferenceStore.getString(WorkbenchPreferencePageDefaultConfiguration.AUTHOR);
		String company = preferenceStore.getString(WorkbenchPreferencePageDefaultConfiguration.COMPANY);
		String solver = preferenceStore.getString(WorkbenchPreferencePageDefaultConfiguration.SOLVER);
		String start = preferenceStore.getString(WorkbenchPreferencePageDefaultConfiguration.START);
		String stop = preferenceStore.getString(WorkbenchPreferencePageDefaultConfiguration.STOP);
		String output = preferenceStore.getString(WorkbenchPreferencePageDefaultConfiguration.OUTPUT);
		String tolerance = preferenceStore.getString(WorkbenchPreferencePageDefaultConfiguration.TOLERANCE);
		//TODO 20101212 start, stop und tolerance auf datentzp pruefen
		
		if (start.isEmpty() || start == null) {
			start = "0.0";
		}
		
		if (stop.isEmpty() || stop == null) {
			stop = "1.0";
		}
		
		Double outputRelativeToStartAndStop = 0.1;
		
		try{
			Double startDouble = new Double(start);
			Double stopDouble = new Double(stop);
			
			outputRelativeToStartAndStop = ((stopDouble - startDouble) * 0.01);
		}catch (Exception e) {
		}
		
		if (output.isEmpty() || output == null) {
			output = outputRelativeToStartAndStop + "";
		}
		
		if (tolerance.isEmpty() || tolerance == null) {
			tolerance = "0.0001";
		}
		
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat( "yyyyMMddHHmm" );
		String settingName = ("Config_" + df.format(dt));
		
		SimulationSetting defaultSetting = new SimulationSettingNonInteractive(settingName, "ni", "1.0", author, company, "no comment yet", 0, output, tolerance, solver, start, stop);
		return defaultSetting;
	}


	@Override
	public void addPages() {
		one = new SessionConfiguration_ChangeSetupNonInteractiveWizardPage("Simulation Session Configuration: Setting", ss, option);
		two = new SessionConfiguration_ChangeModelNonInteractiveWizardPage("Simulation Session Configuration: Model Configuration", mm);
		addPage(one);
		addPage(two);
		getContainer().getShell().setSize(550, 650);
	}

	@Override
	public boolean performFinish() {

		try {
			new ProgressMonitorDialog(getContainer().getShell()).run(true, true,
					new IRunnableWithProgress() {
						@Override
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException,
								InterruptedException {
							monitor.beginTask("Create a new Simulation Session Configuration...",
									3);
//									IProgressMonitor.UNKNOWN);
							for (int total = 1; total < 2; total++) {
								if (monitor.isCanceled())
									throw new OperationCanceledException();
//								Thread.sleep(1000l); //TODO [20100506] CAN BE DELETED
								
								switch (total) {
								case 1:
									monitor.setTaskName("Step: " + total + ", Preparing Filesystem...");
									getShell().getDisplay().asyncExec(new Runnable() {
										public void run() {
											String pathToSessionConfigFolder = createConfigFolder();
											try {
												createSettingFile(pathToSessionConfigFolder);
												createConfiguredModel(pathToSessionConfigFolder);
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									monitor.worked(1);
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
		String selectedSimulationProject = Activator.getSimulationProjectCenter().getSelectedSimulationProject().getProjectName();
		Activator.getSimulationProjectCenter().changeInWorkspace();
		Activator.getSimulationProjectCenter().setSelectedSimulationProject(selectedSimulationProject);
		
		if(one.getStartSimulation())prepareNonInteractiveSimulation();
		
		return true;
	}
	
	/**
	 * This method is called when "Start Simulation" button is pressed
	 * @param selected TreeItem
	 */
	private boolean prepareNonInteractiveSimulation() {

		final SimulationProject selectedSimulationProject = Activator
				.getSimulationProjectCenter().getSelectedSimulationProject();

		final SimulationProjectCenter spc = Activator
				.getSimulationProjectCenter();

		String selectedSessionConfigName = one.getSessionConfigurationName();
		final SimulationSessionConfiguration selectedSimulationSessionConfiguration = selectedSimulationProject
				.getSimSessConfig(selectedSessionConfigName);

		try {
			new ProgressMonitorDialog(getShell()).run(true, true,
					new IRunnableWithProgress() {
						@Override
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException,
								InterruptedException {
							monitor.beginTask("Start a Simulation Session...", 100);
							// IProgressMonitor.UNKNOWN);
							for (int total = 1; total < 5; total++) {
								if (monitor.isCanceled())
									throw new OperationCanceledException();
								switch (total) {
								case 1:
									monitor.setTaskName("Step: "
											+ total
											+ ", Preparing Simulation Settings and Model Data...");
									Display.getDefault().asyncExec(
											new Runnable() {
												public void run() {
													// Modifying init.txt
													/**
													 * Path to model_init.xml
													 */
													String initFilePath = spc
															.getSimulationProjectPath(selectedSimulationProject
																	.getProjectName())
															+ "/"
															+ selectedSimulationProject
																	.getFullQualifiedModelicaModelName()
															+ "_init.xml";
													SimulationInit_XML_writer.writeInit(
																	initFilePath,
																	createInitTXTObject(initFilePath));
												}
											});
									monitor.worked(10);
									break;
								case 2:
									monitor.setTaskName("Step: "
											+ total
											+ ", Preparing the Simulation Center...");
									Display.getDefault().asyncExec(
											new Runnable() {
												public void run() {
													Activator
															.initSimulationCenter_NonInteractive(selectedSimulationSessionConfiguration);
												}
											});
									monitor.worked(25);
									break;
								case 3:
									monitor.setTaskName("Step: "
											+ total
											+ ", Starting the Runtime Environment...");
									Display.getDefault().asyncExec(
											new Runnable() {
												public void run() {
													Activator
															.getSimulationCenter_NonInteractive()
															.startRuntimeEnvironment();
												}
											});
									monitor.worked(80);
									break;
								case 4:									
									monitor.setTaskName("Step: "
											+ total
											+ ", Evaluating Simulation Results...");
									Display.getDefault().asyncExec(
											new Runnable() {
												public void run() {
													try {
//														Thread.sleep(1000); // TODO [20101120] Warten bis die result datei von der runtime erzeugt wurde 
														
//														String resultFilePath = Activator.getSimulationProjectCenter().getSimulationProjectPath(selectedSimulationProject.getProjectName());
//														String resultFileName = selectedSimulationProject.getFullQualifiedModelicaModelName() + "_res.plt";
//														
//														File resultFile = new File(resultFilePath + "/" + resultFileName);
//														
//														while(!resultFile.exists()){
//															System.out.println("warten");
//															Thread.sleep(500); // TODO [20101120] Warten bis die result datei von der runtime erzeugt wurde 
//														}
															
														Activator
																.getSimulationCenter_NonInteractive()
																.evaluatingResults();
													} catch (Exception e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												}
											});
									monitor.worked(100);
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
		} catch (OperationCanceledException e) {
			System.out.println("Abbruch2");
		}
		IWorkbench workbench = PlatformUI.getWorkbench();
		try {
			workbench
					.showPerspective(
							"org.openmodelica.simulation.environment.perspective.simulation.noninteractiveSimulationSessionPerspective",
							workbench.getActiveWorkbenchWindow());
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Create an InitData object using the setting and configured model file
	 * 
	 * @return a full initialized InitData object with data from setting and
	 *         configured model
	 */
	private InitData createInitTXTObject(String initFilePath) {

		SimulationProject selectedSimulationProject = Activator
				.getSimulationProjectCenter().getSelectedSimulationProject();
		String selectedSessionConfigName = one.getSessionConfigurationName();
		SimulationSessionConfiguration selectedSimulationSessionConfiguration = selectedSimulationProject
				.getSimSessConfig(selectedSessionConfigName);

		/*
		 * The original init.txt file have to be read again to specified the
		 * order of variables into it TODO [201009] Erzeugen, Property
		 * Reihenfolge muss eingehalten werden daher original einlesen und werte
		 * ersetzen (teuer) aber einzige Möglichkeit variables wieder in state
		 * und alg zu teilen. PROBLEM nicht nur die Reihenfolge der typen muss
		 * eingehalten werden sondern auch die exacte folge der variablen…
		 */
		InitData originalInitTXT = null;
		
		//TODO [20110309] After the first parsing of the init file the exception won't occure anymore
		try {
			originalInitTXT = SimulationInit_XML_reader.readFromXML(initFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		originalInitTXT.fileName = selectedSimulationProject
				.getFullQualifiedModelicaModelName() + "_init.xml";

		originalInitTXT.start = new Double(
				selectedSimulationSessionConfiguration.getSimSetting()
						.getStart());
		originalInitTXT.stop = new Double(
				selectedSimulationSessionConfiguration.getSimSetting()
						.getStop());
		originalInitTXT.step = new Double(
				selectedSimulationSessionConfiguration.getSimSetting()
						.getSimStepTime());
		originalInitTXT.tolerance = new Double(
				selectedSimulationSessionConfiguration.getSimSetting()
						.getTolerance());
		originalInitTXT.method = selectedSimulationSessionConfiguration
				.getSimSetting().getSolver();
		
		originalInitTXT.states = selectedSimulationSessionConfiguration
				.getModelicaModel().getN_states();
		originalInitTXT.alg_vars = selectedSimulationSessionConfiguration
				.getModelicaModel().getN_algebraics();
		originalInitTXT.parameters = selectedSimulationSessionConfiguration
				.getModelicaModel().getN_parameters();

		originalInitTXT.n_int_parameters = selectedSimulationSessionConfiguration
				.getModelicaModel().getN_int_parameters();
		originalInitTXT.n_int_variables = selectedSimulationSessionConfiguration
				.getModelicaModel().getN_int_variables();
		originalInitTXT.n_bool_parameters = selectedSimulationSessionConfiguration
				.getModelicaModel().getN_bool_parameters();
		originalInitTXT.n_bool_variables = selectedSimulationSessionConfiguration
				.getModelicaModel().getN_bool_variables();

		originalInitTXT.string_parameters = selectedSimulationSessionConfiguration
				.getModelicaModel().getN_string_parameters();
		originalInitTXT.string_variables = selectedSimulationSessionConfiguration
				.getModelicaModel().getN_string_variables();

		// Modify state and algorithm values NOTE: Problem a ModelicaModel does
		// not separate states and algorithm variables, so we need to iterate
		// over both to find their correspondence
		for (String varName : selectedSimulationSessionConfiguration
				.getModelicaModel().getMainclass()
				.getAllFullQualifiedVariableNames()) {
			
			if (originalInitTXT.stateProperties.containsKey(varName))
				originalInitTXT.stateProperties
						.put(varName,
								(Double) selectedSimulationSessionConfiguration
										.getModelicaModel().getMainclass()
										.getVariableByName(varName)
										.getPrimitiveValue());
			
			else if(originalInitTXT.algProperties.containsKey(varName))
				originalInitTXT.algProperties
						.put(varName,
								(Double) selectedSimulationSessionConfiguration
										.getModelicaModel().getMainclass()
										.getVariableByName(varName)
										.getPrimitiveValue());
			
			else if(originalInitTXT.n_int_variablesProperties.containsKey(varName)){
				
				System.out.println("n_int_variablesProperties: " + originalInitTXT.n_int_variablesProperties.get(varName));
				
				originalInitTXT.n_int_variablesProperties
				.put(varName,
						new Double((Integer)selectedSimulationSessionConfiguration
								.getModelicaModel().getMainclass()
								.getVariableByName(varName)
								.getPrimitiveValue()));
				System.out.println("n_int_variablesProperties: " + originalInitTXT.n_int_variablesProperties.get(varName));
			}
			
			else if(originalInitTXT.n_bool_variablesProperties.containsKey(varName)){
				
				if ((Boolean)selectedSimulationSessionConfiguration.getModelicaModel()
						.getMainclass().getVariableByName(varName)
						.getPrimitiveValue()){
					originalInitTXT.n_bool_variablesProperties
					.put(varName, 1.0);
				}else{
					originalInitTXT.n_bool_variablesProperties
					.put(varName, 0.0);
				}
			}
		}

		// Modify Parameter values
		for (String parName : selectedSimulationSessionConfiguration
				.getModelicaModel().getMainclass()
				.getAllFullQualifiedParameterNames()) {

			if (originalInitTXT.parameterProperties.containsKey(parName))
				originalInitTXT.parameterProperties.put(parName,
						(Double) selectedSimulationSessionConfiguration
								.getModelicaModel().getMainclass()
								.getParameterByName(parName)
								.getPrimitiveValue());

			else if (originalInitTXT.n_int_parametersProperties.containsKey(parName))
				originalInitTXT.n_int_parametersProperties
						.put(parName, new Double((Integer)selectedSimulationSessionConfiguration
										.getModelicaModel().getMainclass()
										.getVariableByName(parName)
										.getPrimitiveValue()));
			
			else if (originalInitTXT.n_bool_parametersProperties.containsKey(parName))
			
			if ((Boolean)selectedSimulationSessionConfiguration.getModelicaModel()
					.getMainclass().getVariableByName(parName)
					.getPrimitiveValue()){
				originalInitTXT.n_bool_parametersProperties
				.put(parName, 1.0);
			}else{
				originalInitTXT.n_bool_parametersProperties
				.put(parName, 0.0);
			}
		}
		return originalInitTXT;
	}

	/**
	 * 
	 * @return path to session config folder
	 */
	private String createConfigFolder(){
		String selectedSimulationProject = Activator.getSimulationProjectCenter().getSelectedSimulationProject().getProjectName();
		String pathToSelectedSimulationProject = Activator.getSimulationProjectCenter().getSimulationProjectPath(selectedSimulationProject);
		
		String sessionConfigName = one.getSessionConfigurationName();
		String sessionConfigFolderPath = pathToSelectedSimulationProject + "/SimulationSessionConfigurations/" + sessionConfigName;
		File simSessConfigFolder = new File(sessionConfigFolderPath);
		simSessConfigFolder.mkdir();
		
		return simSessConfigFolder.getAbsolutePath();
	}
	
	private void createSettingFile(String pathToSessionConfigFolder) throws Exception{
		String selectedSimulationProject = Activator.getSimulationProjectCenter().getSelectedSimulationProject().getProjectName();
		
		
		SimulationSetting_XML_generator.createXML(selectedSimulationProject, mm.getId(), one.createSimulationSetting(), pathToSessionConfigFolder);
	}
	
	private void createConfiguredModel(String pathToSessionConfigFolder) throws Exception {
		
		two.adoptAllPropertySettings();
		SimulationConfiguration_XML_generator.createXML(two.getConfiguredModelicaModel(), pathToSessionConfigFolder);
	}
}

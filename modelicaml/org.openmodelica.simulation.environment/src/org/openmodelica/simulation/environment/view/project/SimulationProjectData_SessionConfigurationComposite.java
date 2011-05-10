/*
 * 
 */
package org.openmodelica.simulation.environment.view.project;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.openmodelica.simulation.core.init_txt_handling.InitTXT;
import org.openmodelica.simulation.core.init_txt_handling.SimulationInit_TXT_reader;
import org.openmodelica.simulation.core.init_txt_handling.SimulationInit_TXT_writer;
import org.openmodelica.simulation.core.models.modelica.ModelicaModel;
import org.openmodelica.simulation.core.models.simulation.SimulationProject;
import org.openmodelica.simulation.core.models.simulation.SimulationSessionConfiguration;
import org.openmodelica.simulation.core.models.simulation.SimulationSetting;
import org.openmodelica.simulation.core.models.simulation.SimulationSettingInteractive;
import org.openmodelica.simulation.core.models.simulation.SimulationSettingNonInteractive;
import org.openmodelica.simulation.environment.Activator;
import org.openmodelica.simulation.environment.core.SimulationProjectCenter;
import org.openmodelica.simulation.environment.wizard.sessionconfiguration.interactive.SessionConfiguration_InteractiveWizard;
import org.openmodelica.simulation.environment.wizard.sessionconfiguration.noninteractive.SessionConfiguration_NonInteractiveWizard;

// TODO: Auto-generated Javadoc
/**
 * The Class SimulationProjectData_SessionConfigurationComposite.
 */
public class SimulationProjectData_SessionConfigurationComposite extends
		Composite implements Observer {

	/** The sash form1. */
	private SashForm sashForm1;
	
	/** The composite tree configs. */
	private Composite compositeTreeConfigs;
	
	/** The composite selected config data. */
	private Composite compositeSelectedConfigData;
	
	/** The button start simulation. */
	private Button buttonStartSimulation;
	
	/** The button delete. */
	private Button buttonDelete;
	
	/** The button use to create. */
	private Button buttonUseToCreate;
	
	/** The button modify. */
	private Button buttonModify;
	
	/** The composite buttons for selected config. */
	private Composite compositeButtonsForSelectedConfig;
	
	/** The tab folder setting and config. */
	private TabFolder tabFolderSettingANDConfig;
	
	/** The tab item1. */
	private TabItem tabItem1;
	
	/** The tab item2. */
	private TabItem tabItem2;
	
	/** The button add config. */
	private Button buttonAddConfig;
	
	/** The composite config tree buttons. */
	private Composite compositeConfigTreeButtons;
	
	/** The tree config model. */
	private Tree treeConfigModel;
	
	/** The composite configured model data. */
	private Composite compositeConfiguredModelData;
	
	/** The composite setting data. */
	private Composite compositeSettingData;
	
	/** The styled text selected session config data. */
	private StyledText styledTextSelectedSessionConfigData;
	
	/** The tree configs. */
	private Tree treeConfigs;

	/**
	 * Auto-generated main method to display this
	 * org.eclipse.swt.widgets.Composite inside a new Shell.
	 */

	/**
	 * Overriding checkSubclass allows this class to extend
	 * org.eclipse.swt.widgets.Composite
	 */
	protected void checkSubclass() {
	}

	/**
	 * Auto-generated method to display this org.eclipse.swt.widgets.Composite
	 * inside a new Shell.
	 *
	 * @param parent the parent
	 * @param style the style
	 */

	public SimulationProjectData_SessionConfigurationComposite(
			Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			FillLayout thisLayout = new FillLayout(
					org.eclipse.swt.SWT.HORIZONTAL);
			this.setLayout(thisLayout);
			this.setSize(644, 390);
			{
				sashForm1 = new SashForm(this, SWT.BORDER);
				GridLayout sashForm1Layout = new GridLayout();
				sashForm1Layout.makeColumnsEqualWidth = true;
				sashForm1.setLayout(sashForm1Layout);
				{
					compositeTreeConfigs = new Composite(sashForm1, SWT.NONE);
					GridLayout compositeTreeConfigsLayout = new GridLayout();
					compositeTreeConfigsLayout.makeColumnsEqualWidth = true;
					compositeTreeConfigs.setLayout(compositeTreeConfigsLayout);
					GridData compositeTreeConfigsLData = new GridData();
					compositeTreeConfigsLData.verticalAlignment = GridData.FILL;
					compositeTreeConfigs
							.setLayoutData(compositeTreeConfigsLData);
					compositeTreeConfigs.setBounds(50, 63, 288, 294);
					{
						compositeConfigTreeButtons = new Composite(
								compositeTreeConfigs, SWT.NONE);
						GridLayout compositeConfigTreeButtonsLayout = new GridLayout();
						compositeConfigTreeButtonsLayout.makeColumnsEqualWidth = true;
						GridData compositeConfigTreeButtonsLData = new GridData();
						compositeConfigTreeButtonsLData.horizontalAlignment = GridData.FILL;
						compositeConfigTreeButtons
								.setLayoutData(compositeConfigTreeButtonsLData);
						compositeConfigTreeButtons
								.setLayout(compositeConfigTreeButtonsLayout);
						{
							buttonAddConfig = new Button(
									compositeConfigTreeButtons, SWT.PUSH
											| SWT.CENTER);
							GridData buttonAddConfigLData = new GridData();
							buttonAddConfig.setLayoutData(buttonAddConfigLData);
							
							LocalResourceManager resources 
						    = new LocalResourceManager(JFaceResources.getResources(), buttonAddConfig);
							ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/add.gif");
							Image i = imageDescriptor.createImage();
							resources.dispose();

							buttonAddConfig.setImage(i);
							
							
							buttonAddConfig
									.addSelectionListener(new SelectionAdapter() {
										@Override
										public void widgetSelected(
												SelectionEvent e) {
											super.widgetSelected(e);

											// TODO [20101107]

											MessageDialog messageDialog = new MessageDialog(
													getShell(),
													"Add a new simulation session configuration",
													null,
													"Please select the type of simulation.",
													MessageDialog.INFORMATION,
													new String[] {
															"Interactive",
															"Non Interactive",
															"Cancel" }, 1);
											int selection = messageDialog.open();
											
											if (selection == 0) {
												WizardDialog wizard = new WizardDialog(
														getShell(),
														new SessionConfiguration_InteractiveWizard());
												wizard.open();
											} else if (selection == 1) {
												WizardDialog wizard = new WizardDialog(
														getShell(),
														new SessionConfiguration_NonInteractiveWizard());
												wizard.open();
											}

										}
									});
						}
					}
					{
						GridData treeConfigsLData = new GridData();
						treeConfigsLData.horizontalAlignment = GridData.FILL;
						treeConfigsLData.verticalAlignment = GridData.FILL;
						treeConfigsLData.grabExcessHorizontalSpace = true;
						treeConfigsLData.grabExcessVerticalSpace = true;
						treeConfigs = new Tree(compositeTreeConfigs, SWT.SINGLE
								| SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
						treeConfigs.setLayoutData(treeConfigsLData);
						treeConfigs
								.addSelectionListener(new SelectionAdapter() {
									public void widgetSelected(
											SelectionEvent evt) {
										TreeItem ti = treeConfigs
												.getSelection()[0];
										fillStyledTextSelectedSessionConfigData((String) ti
												.getData());

										SimulationProject selectedSimulationProject = Activator
												.getSimulationProjectCenter()
												.getSelectedSimulationProject();
										SimulationSetting selectedSimulationSetting = selectedSimulationProject
												.getSimSessConfig(
														(String) ti.getData())
												.getSimSetting();

										if (selectedSimulationSetting instanceof SimulationSettingInteractive) {
											compositeSettingData = new SimulationProjectData_SessionConfiguration_SettingInteractiveComposite(
													tabFolderSettingANDConfig,
													SWT.NONE);
											tabItem1.setControl(compositeSettingData);
											fillTabItem1ForInteractive((String) ti
													.getData());
										} else if (selectedSimulationSetting instanceof SimulationSettingNonInteractive) {
											compositeSettingData = new SimulationProjectData_SessionConfiguration_SettingNonInteractiveComposite(
													tabFolderSettingANDConfig,
													SWT.NONE);
											tabItem1.setControl(compositeSettingData);
											fillTabItem1ForNonInteractive((String) ti
													.getData());
										}
										fillTabItem2((String) ti.getData());
										enableButtons();
										// Activator.getSimulationProjectCenter().setSelectedSimulationProject((String)ti.getData());
									}
								});
					}
				}
				{
					compositeSelectedConfigData = new Composite(sashForm1,
							SWT.NONE);
					GridLayout compositeConfigDataLayout = new GridLayout();
					compositeConfigDataLayout.makeColumnsEqualWidth = true;
					GridData compositeConfigDataLData = new GridData();
					compositeSelectedConfigData
							.setLayoutData(compositeConfigDataLData);
					compositeSelectedConfigData
							.setLayout(compositeConfigDataLayout);
					{
						styledTextSelectedSessionConfigData = new StyledText(
								compositeSelectedConfigData, SWT.READ_ONLY);
						GridData styledTextSelectedSessionConfigDataLData = new GridData();
						styledTextSelectedSessionConfigDataLData.horizontalAlignment = GridData.FILL;
						styledTextSelectedSessionConfigDataLData.grabExcessHorizontalSpace = true;
						styledTextSelectedSessionConfigData
								.setLayoutData(styledTextSelectedSessionConfigDataLData);
						styledTextSelectedSessionConfigData.setText("\n\n\n\n"); // TODO [201009] grö0e von textfeld angeben!
					}
					{
						tabFolderSettingANDConfig = new TabFolder(
								compositeSelectedConfigData, SWT.NONE);
						{
							tabItem1 = new TabItem(tabFolderSettingANDConfig,
									SWT.NONE);
							tabItem1.setText("Settings");
							tabItem1.setToolTipText("Displays the specified simulation session settings e.g. networks configuration...");
							{
								compositeSettingData = new SimulationProjectData_SessionConfiguration_SettingInteractiveComposite( //TODO This initiazies the composite. Das gibt die größe von anfang an richtig an, da sonst fehler bei der darstellung enstehen
										tabFolderSettingANDConfig, SWT.NONE);
								tabItem1.setControl(compositeSettingData);
							}
						}
						{
							tabItem2 = new TabItem(tabFolderSettingANDConfig,
									SWT.NONE);
							tabItem2.setText("Properties");
							tabItem2.setToolTipText("Displays the specified simulation session property configuration e.g. a variable and its value");
							{
								compositeConfiguredModelData = new Composite(
										tabFolderSettingANDConfig, SWT.NONE);
								GridLayout compositeConfiguredModelDataLayout = new GridLayout();
								compositeConfiguredModelDataLayout.makeColumnsEqualWidth = true;
								compositeConfiguredModelData
										.setLayout(compositeConfiguredModelDataLayout);
								tabItem2.setControl(compositeConfiguredModelData);
								{
									GridData treeModelLData = new GridData();
									treeModelLData.horizontalAlignment = GridData.FILL;
									treeModelLData.verticalAlignment = GridData.FILL;
									treeModelLData.grabExcessHorizontalSpace = true;
									treeModelLData.grabExcessVerticalSpace = true;
									treeConfigModel = new Tree(
											compositeConfiguredModelData,
											SWT.NONE);
									treeConfigModel
											.setLayoutData(treeModelLData);
									Font font = new Font(Display.getCurrent(), "Segoe UI", 11, SWT.NONE); 
									treeConfigModel.setFont(font);
									buildTreeConfigModel(null);
								}
							}
						}
						GridData tabFolderSettingANDConfigLData = new GridData();
						tabFolderSettingANDConfigLData.verticalAlignment = GridData.FILL;
						tabFolderSettingANDConfigLData.horizontalAlignment = GridData.FILL;
						tabFolderSettingANDConfigLData.grabExcessVerticalSpace = true;
						tabFolderSettingANDConfig
								.setLayoutData(tabFolderSettingANDConfigLData);
						tabFolderSettingANDConfig.setSelection(0);
					}
					{
						compositeButtonsForSelectedConfig = new Composite(
								compositeSelectedConfigData, SWT.NONE);
						GridLayout compositeButtonsForSelectedConfigLayout = new GridLayout();
						compositeButtonsForSelectedConfigLayout.makeColumnsEqualWidth = true;
						compositeButtonsForSelectedConfigLayout.numColumns = 2;
						GridData compositeButtonsForSelectedConfigLData = new GridData();
						compositeButtonsForSelectedConfigLData.horizontalAlignment = GridData.FILL;
						compositeButtonsForSelectedConfig
								.setLayoutData(compositeButtonsForSelectedConfigLData);
						compositeButtonsForSelectedConfig
								.setLayout(compositeButtonsForSelectedConfigLayout);
						{
							buttonModify = new Button(
									compositeButtonsForSelectedConfig, SWT.PUSH
											| SWT.CENTER);
							GridData buttonModifyLData = new GridData();
							buttonModifyLData.horizontalAlignment = GridData.FILL;
							buttonModify.setLayoutData(buttonModifyLData);
							buttonModify
									.setToolTipText("Click to modify this selected simulation session configuration");
							
							
							LocalResourceManager resources 
						    = new LocalResourceManager(JFaceResources.getResources(), buttonAddConfig);
							ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/settings.gif");
							Image i = imageDescriptor.createImage();
							resources.dispose();
							
							buttonModify.setImage(i);
							buttonModify.setText("Change");
							buttonModify.setEnabled(false);
							buttonModify
									.addSelectionListener(new SelectionAdapter() {
										@Override
										public void widgetSelected(
												SelectionEvent e) {
											super.widgetSelected(e);
											TreeItem ti = treeConfigs
													.getSelection()[0];
											String selectedSessionConfigName = (String) ti
													.getData();

											SimulationProject selectedSimulationProject = Activator
													.getSimulationProjectCenter()
													.getSelectedSimulationProject();

											SimulationSetting selectedSimulationSetting = selectedSimulationProject
													.getSimSessConfig(
															selectedSessionConfigName)
													.getSimSetting();
											ModelicaModel selectedConfiguredModelicaModel = selectedSimulationProject
													.getSimSessConfig(
															selectedSessionConfigName)
													.getModelicaModel();

											// TODO [20101107]

											if (selectedSimulationSetting instanceof SimulationSettingInteractive) {
												WizardDialog wizard = new WizardDialog(
														getShell(),
														new SessionConfiguration_InteractiveWizard(
																selectedSimulationSetting,
																selectedConfiguredModelicaModel,
																"change"));
												wizard.open();
											} else if (selectedSimulationSetting instanceof SimulationSettingNonInteractive) {
												WizardDialog wizard = new WizardDialog(
														getShell(),
														new SessionConfiguration_NonInteractiveWizard(
																selectedSimulationSetting,
																selectedConfiguredModelicaModel,
																"change"));
												wizard.open();
											}
										}
									});
						}
						{
							buttonUseToCreate = new Button(
									compositeButtonsForSelectedConfig, SWT.PUSH
											| SWT.CENTER);
							GridData buttonUseToCreateLData = new GridData();
							buttonUseToCreateLData.horizontalAlignment = GridData.FILL;
							buttonUseToCreate
									.setLayoutData(buttonUseToCreateLData);
							buttonUseToCreate.setText("Use to create...");
							buttonUseToCreate
									.setToolTipText("Click to create a new simulation session configuration using this selected one as template");
							
							LocalResourceManager resources 
						    = new LocalResourceManager(JFaceResources.getResources(), buttonAddConfig);
							ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/export.gif");
							Image i = imageDescriptor.createImage();
							resources.dispose();
							
							buttonUseToCreate.setImage(i);
							buttonUseToCreate.setEnabled(false);
							buttonUseToCreate
									.addSelectionListener(new SelectionAdapter() {
										public void widgetSelected(
												SelectionEvent e) {
											// TODO Auto-generated method stub
											super.widgetSelected(e);
											TreeItem ti = treeConfigs
													.getSelection()[0];
											String selectedSessionConfigName = (String) ti
													.getData();

											SimulationProject selectedSimulationProject = Activator
													.getSimulationProjectCenter()
													.getSelectedSimulationProject();

											SimulationSetting selectedSimulationSetting = selectedSimulationProject
													.getSimSessConfig(
															selectedSessionConfigName)
													.getSimSetting();
											ModelicaModel selectedConfiguredModelicaModel = selectedSimulationProject
													.getSimSessConfig(
															selectedSessionConfigName)
													.getModelicaModel();

											// TODO [20101107]

											if (selectedSimulationSetting instanceof SimulationSettingInteractive) {
												WizardDialog wizard = new WizardDialog(
														getShell(),
														new SessionConfiguration_InteractiveWizard(
																selectedSimulationSetting,
																selectedConfiguredModelicaModel,
																"use"));
												wizard.open();
											} else if (selectedSimulationSetting instanceof SimulationSettingNonInteractive) {
												WizardDialog wizard = new WizardDialog(
														getShell(),
														new SessionConfiguration_NonInteractiveWizard(
																selectedSimulationSetting,
																selectedConfiguredModelicaModel,
																"use"));
												wizard.open();
											}
										}
									});
						}
						{
							buttonDelete = new Button(
									compositeButtonsForSelectedConfig, SWT.PUSH
											| SWT.CENTER);
							GridData buttonDeleteLData = new GridData();
							buttonDeleteLData.horizontalAlignment = GridData.FILL;
							buttonDelete.setLayoutData(buttonDeleteLData);
							buttonDelete.setText("Delete");
							buttonDelete
									.setToolTipText("Click to delete this selected session configuration file");
							
							LocalResourceManager resources 
						    = new LocalResourceManager(JFaceResources.getResources(), buttonDelete);
							ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/delete.gif");
							Image i = imageDescriptor.createImage();
							resources.dispose();
//							Image i = new Image(Display.getCurrent(), "src/org/openmodelica/simulation/environment/icons/delete.gif");
							buttonDelete.setImage(i);
							buttonDelete.setEnabled(false);
							buttonDelete
									.addSelectionListener(new SelectionAdapter() {
										@Override
										public void widgetSelected(
												SelectionEvent e) {
											// TODO Auto-generated method stub
											super.widgetSelected(e);

											TreeItem ti = treeConfigs
													.getSelection()[0];

											String selectedSimulationProject = Activator
													.getSimulationProjectCenter()
													.getSelectedSimulationProject()
													.getProjectName();
											String pathToSelectedSimulationProject = Activator
													.getSimulationProjectCenter()
													.getSimulationProjectPath(
															selectedSimulationProject);
											String sessionConfigName = (String) ti
													.getData();
											String sessionConfigFolderPath = pathToSelectedSimulationProject
													+ "/SimulationSessionConfigurations/"
													+ sessionConfigName;
											MessageDialog messageDialog = new MessageDialog(
													getShell(),
													"Delete selected Session Configuration",
													null,
													"Are you sure you want to delete "
															+ sessionConfigName
															+ " from "
															+ selectedSimulationProject
															+ "?",
													MessageDialog.QUESTION,
													new String[] { "Delete",
															"Cancel" }, 1);
											if (messageDialog.open() == 0) {
												File simSessConfigFolder = new File(
														sessionConfigFolderPath);

												deleteAll(simSessConfigFolder);

												simSessConfigFolder.delete();
												Activator
														.getSimulationProjectCenter()
														.changeInWorkspace();
												Activator
														.getSimulationProjectCenter()
														.setSelectedSimulationProject(
																selectedSimulationProject);
											}

										}

										/**
										 * Recursive method to delete contend
										 * files from a Directory
										 * 
										 * @param f
										 */
										private void deleteAll(File f) {
											if (f.isFile())
												f.delete();
											else if (f.isDirectory()
													&& f.listFiles().length == 0)
												f.delete();
											else {
												for (File childF : f
														.listFiles())
													deleteAll(childF);
												f.delete();
											}
										}

									});
						}
						{
							buttonStartSimulation = new Button(
									compositeButtonsForSelectedConfig, SWT.PUSH
											| SWT.CENTER);
							GridData buttonStartSimulationLData = new GridData();
							buttonStartSimulationLData.horizontalAlignment = GridData.FILL;
							buttonStartSimulation
									.setLayoutData(buttonStartSimulationLData);
							buttonStartSimulation.setText("Start Simulation");
							LocalResourceManager resources 
						    = new LocalResourceManager(JFaceResources.getResources(), buttonStartSimulation);
							ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/run.gif");
							Image i = imageDescriptor.createImage();
							resources.dispose();
							buttonStartSimulation.setImage(i);
							buttonStartSimulation
									.addSelectionListener(new SelectionAdapter() {
										@Override
										public void widgetSelected(
												SelectionEvent e) {
											// TODO Auto-generated method stub
											super.widgetSelected(e);
											
											//If an interactive simulation has already been started the sockets should been closed before
											if (compositeSettingData instanceof SimulationProjectData_SessionConfiguration_SettingInteractiveComposite
													&& !Activator.getSimulationCenter_Interactive().getSimulationStatus().equals("")) {
												MessageDialog messageDialog = new MessageDialog(
														getShell(),
														"Start a new interactive simulation",
														null,
														"Another interactive simulation is already running. Close the running simulation to start a new session?",
														MessageDialog.INFORMATION,
														new String[] {
																"Close Simulation",
																"Cancel" }, 1);
												if (messageDialog.open() == 0) {
													Activator.getSimulationCenter_Interactive().stopAll();
													initSimulation();
												}
											}
											else{
												initSimulation(); //All other cases: e.g. non-interactive simulation or not already running simulation
											}

										}
										
										private void initSimulation(){
											TreeItem ti = treeConfigs
											.getSelection()[0];
											
											String selectedSessionConfigName = (String) ti
											.getData();
											
											SimulationProject selectedSimulationProject = Activator
											.getSimulationProjectCenter()
											.getSelectedSimulationProject();
											
											SimulationSetting selectedSimulationSetting = selectedSimulationProject
											.getSimSessConfig(
													selectedSessionConfigName)
													.getSimSetting();
											
											if (selectedSimulationSetting instanceof SimulationSettingInteractive) {
												prepareInteractiveSimulation(ti);
											} else if (selectedSimulationSetting instanceof SimulationSettingNonInteractive) {
												prepareNonInteractiveSimulation(ti);
											}
											
											buttonStartSimulation
											.setEnabled(false);
										}

									});
							buttonStartSimulation.setEnabled(false);
						}
					}
				}
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We
	 * will create an operation and run it using wizard as execution context.
	 *
	 * @param ti the ti
	 * @return true, if successful
	 */
	private boolean prepareInteractiveSimulation(final TreeItem ti) {

		final SimulationProject selectedSimulationProject = Activator
				.getSimulationProjectCenter().getSelectedSimulationProject();

		final SimulationProjectCenter spc = Activator
				.getSimulationProjectCenter();

		String selectedSessionConfigName = (String) ti.getData();
		final SimulationSessionConfiguration selectedSimulationSessionConfiguration = selectedSimulationProject
				.getSimSessConfig(selectedSessionConfigName);
		
//		MessageDialog
//		.openError(
//			getShell(),
//			"Start Simulation Runtime",
//			"Please start the simulation runtime (*.exe or *.bat)\nusing the parameter -interactive -port 10501 and press OK");

		try {
			new ProgressMonitorDialog(getShell()).run(true, true,
					new IRunnableWithProgress() {
						@Override
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException,
								InterruptedException {
							monitor.beginTask("Start a Simulation Session...", 100);
							// IProgressMonitor.UNKNOWN);
							for (int total = 1; total < 7; total++) {
								if (monitor.isCanceled())
									throw new OperationCanceledException();
								Thread.sleep(500); // TODO [20100506] CAN BE
													// DELETED

								switch (total) {
								case 1:
									monitor.setTaskName("Step: "
											+ total
											+ ", Preparing Simulation Settings and Model Data...");
									Display.getDefault().asyncExec(
											new Runnable() {
												public void run() {
													/**
													 * Path to model_init.txt
													 */
													String initFilePath = spc
															.getSimulationProjectPath(selectedSimulationProject
																	.getProjectName())
															+ "/"
															+ selectedSimulationProject
																	.getFullQualifiedModelicaModelName()
															+ "_init.txt";
													SimulationInit_TXT_writer
															.writeInit(
																	initFilePath,
																	createInitTXTObject(initFilePath, ti));
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
															.initSimulationCenter_Interactive(selectedSimulationSessionConfiguration);
												}
											});
									monitor.worked(25);
									break;
								case 3:
									monitor.setTaskName("Step: "
											+ total
											+ ", Preparing Runtime Environment...");
									Display.getDefault().asyncExec(
											new Runnable() {
												public void run() {
													int rcsPort = 0;
													try {// TODO [20101107]
													 rcsPort =
													 Integer.valueOf(((SimulationSettingInteractive)selectedSimulationSessionConfiguration.getSimSetting()).getRuntimeControlServerPort().trim());
													} catch (NumberFormatException e) {
														// TODO Auto-generated
														// catch block
														e.printStackTrace();
													}

													// Create thread which called the runtime executable file TODO [20100912] only for the interactive simulation
													Activator
															.getSimulationCenter_Interactive()
															.prepareExecutableRuntimeForInteractiveSimulation(
																	rcsPort);
												}
											});
									monitor.worked(35);
									break;
								case 4:
									monitor.setTaskName("Step: "
											+ total
											+ ", Preparing Network Communication Units...");
									Display.getDefault().asyncExec(
											new Runnable() {
												public void run() {
													int scsPort = 0;
													int rcsPort = 0;
													int stsPort = 0;
													try { // TODO [20101107]
													 rcsPort =
													 Integer.valueOf(((SimulationSettingInteractive)selectedSimulationSessionConfiguration.getSimSetting()).getRuntimeControlServerPort().trim());
													 scsPort =
													 Integer.valueOf(((SimulationSettingInteractive)selectedSimulationSessionConfiguration.getSimSetting()).getSimulationControlServerPort().trim());
													 stsPort =
													 Integer.valueOf(((SimulationSettingInteractive)selectedSimulationSessionConfiguration.getSimSetting()).getSimulationTransferServerPort().trim());
													} catch (NumberFormatException e) {
														e.printStackTrace();
													}

													Activator
															.getSimulationCenter_Interactive()
															.prepareNetworkCommunicationUnits(
																	scsPort,
																	rcsPort,
																	stsPort);
												}
											});
									monitor.worked(55);
									break;
								case 5:
									monitor.setTaskName("Step: "
											+ total
											+ ", Starting the Runtime Environment and Network Units...");
									Display.getDefault().asyncExec(
											new Runnable() {
												public void run() {
													Activator
															.getSimulationCenter_Interactive()
															.startAll();
												}
											});
									monitor.worked(80);
									break;
								case 6:
									monitor.setTaskName("Step: " + total
											+ ", Starting Simulation Center...");
									Display.getDefault().asyncExec(
											new Runnable() {
												public void run() {

													int scsPort = 0;
													int stsPort = 0;
													try {// TODO [20101107]
													 scsPort =
													 Integer.valueOf(((SimulationSettingInteractive)selectedSimulationSessionConfiguration.getSimSetting()).getSimulationControlServerPort().trim());
													 stsPort =
													 Integer.valueOf(((SimulationSettingInteractive)selectedSimulationSessionConfiguration.getSimSetting()).getSimulationTransferServerPort().trim());
													} catch (NumberFormatException e) {
														e.printStackTrace();
													}

													ModelicaModel configuredMoProj = selectedSimulationSessionConfiguration
															.getModelicaModel();
													
													Activator
															.getSimulationCenter_Interactive()
															.createFilterProperties(
																	configuredMoProj);
													
													Activator
													.getSimulationCenter_Interactive().getSimulationResultManager().startNewSimulation();

													Activator
															.getSimulationCenter_Interactive()
															.prepairRuntimeEnvironment(
																	scsPort,
																	stsPort);
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
							"org.openmodelica.simulation.environment.perspective.simulation.SimulationSessionPerspective",
							workbench.getActiveWorkbenchWindow());
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * This method is called when "Start Simulation" button is pressed.
	 *
	 * @param ti the ti
	 * @return true, if successful
	 */
	private boolean prepareNonInteractiveSimulation(final TreeItem ti) {

		final SimulationProject selectedSimulationProject = Activator
				.getSimulationProjectCenter().getSelectedSimulationProject();

		final SimulationProjectCenter spc = Activator
				.getSimulationProjectCenter();

		String selectedSessionConfigName = (String) ti.getData();
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
								Thread.sleep(500); // TODO [20100506] CAN BE
													// DELETED

								switch (total) {
								case 1:
									monitor.setTaskName("Step: "
											+ total
											+ ", Preparing Simulation Settings and Model Data...");
									Display.getDefault().asyncExec(
											new Runnable() {
												public void run() {
													// Modifying init.txt
//													System.out.println(" 2 Modifying init.txt");
													/**
													 * Path to model_init.txt
													 */
													String initFilePath = spc
															.getSimulationProjectPath(selectedSimulationProject
																	.getProjectName())
															+ "/"
															+ selectedSimulationProject
																	.getFullQualifiedModelicaModelName()
															+ "_init.txt";
													
													SimulationInit_TXT_writer
															.writeInit(
																	initFilePath,
																	createInitTXTObject(initFilePath, ti));
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
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		resetAllData();
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				Display.getDefault().readAndDispatch();
				SimulationProject selectedSimulationProject = Activator
						.getSimulationProjectCenter()
						.getSelectedSimulationProject();

				if (selectedSimulationProject != null) {
					buildTreeConfigs(selectedSimulationProject);
				} else {
					buildTreeConfigs(null);
				}
			}
		});

	}

	/**
	 * Resets the data of the config tree, texts and all tabs.
	 */
	private void resetAllData() {
		treeConfigs.removeAll();
		styledTextSelectedSessionConfigData.setText("");

		// reset tab1
		if (compositeSettingData instanceof SimulationProjectData_SessionConfiguration_SettingInteractiveComposite) {
			SimulationProjectData_SessionConfiguration_SettingInteractiveComposite tempComp = (SimulationProjectData_SessionConfiguration_SettingInteractiveComposite) compositeSettingData;
			tempComp.textCCIP.setText("");
			tempComp.textCCPort.setText("");
			tempComp.textCSIP.setText("");
			tempComp.textCSPort.setText("");
			tempComp.textIntervalls.setText("");
			tempComp.textIntervalUnit.setText("");
			tempComp.textTolerance.setText("");
			tempComp.labelUsedSolver.setText("");
			tempComp.textTSIP.setText("");
			tempComp.textTSPort.setText("");
			tempComp.labelCCPortCheck.setText("");
			
			Color c = new Color(Display.getCurrent(), new RGB(240, 240, 240));
			
			tempComp.labelCCPortCheck.setBackground(c);
			tempComp.labelCSPortCheck.setText("");
			tempComp.labelCSPortCheck.setBackground(c);
			tempComp.labelTSPortCheck.setText("");
			tempComp.labelTSPortCheck.setBackground(c);
		} else if (compositeSettingData instanceof SimulationProjectData_SessionConfiguration_SettingNonInteractiveComposite) {
			SimulationProjectData_SessionConfiguration_SettingNonInteractiveComposite tempComp = (SimulationProjectData_SessionConfiguration_SettingNonInteractiveComposite) compositeSettingData;
			tempComp.textStart.setText("");
			tempComp.textStop.setText("");
			tempComp.textIntervalls.setText("");
			tempComp.textIntervalUnit.setText("");
			tempComp.textTolerance.setText("");
			tempComp.labelUsedSolver.setText("");
		}
		// Disable Buttons
		{
			buttonDelete.setEnabled(false);
			buttonModify.setEnabled(false);
			buttonUseToCreate.setEnabled(false);
			buttonStartSimulation.setEnabled(false);
		}
		// reset tab2
		{
			treeConfigModel.removeAll();
		}
	}

	/**
	 * Fills the StyledTextSelectedSessionConfigData with the data from a
	 * selected Session configuration.
	 *
	 * @param selectedSessionConfigName name of a selected session configuration
	 */
	private void fillStyledTextSelectedSessionConfigData(
			String selectedSessionConfigName) {

		SimulationProject selectedSimulationProject = Activator
				.getSimulationProjectCenter().getSelectedSimulationProject();
		// TODO [201009] StyledText sollte eine maximale höhe haben, da zu lange
		// commentare sonst die gesamt sicht nach unten hin stören!!
		styledTextSelectedSessionConfigData.setText(selectedSessionConfigName
				+ ":");
		styledTextSelectedSessionConfigData.append("\nVersion: "
				+ selectedSimulationProject
						.getSimSessConfig(selectedSessionConfigName)
						.getSimSetting().getVersion());
		styledTextSelectedSessionConfigData.append("\nType: "
				+ selectedSimulationProject
						.getSimSessConfig(selectedSessionConfigName)
						.getSimSetting().getType());
		styledTextSelectedSessionConfigData.append("\nAuthor: "
				+ selectedSimulationProject
						.getSimSessConfig(selectedSessionConfigName)
						.getSimSetting().getAuthor());
		styledTextSelectedSessionConfigData.append("\nCompany: "
				+ selectedSimulationProject
						.getSimSessConfig(selectedSessionConfigName)
						.getSimSetting().getCompany());
		styledTextSelectedSessionConfigData.append("\nComment: "
				+ selectedSimulationProject
						.getSimSessConfig(selectedSessionConfigName)
						.getSimSetting().getComment());
		styledTextSelectedSessionConfigData.append("\nNumber of Runs: "
				+ selectedSimulationProject
						.getSimSessConfig(selectedSessionConfigName)
						.getSimSetting().getNumberOfRuns());
	}

	/**
	 * Fills the first tab with simulation and network settings of a selected
	 * simulation session configuration.
	 *
	 * @param selectedSessionConfigName the selected session config name
	 */
	private void fillTabItem1ForInteractive(String selectedSessionConfigName) {
		SimulationProject selectedSimulationProject = Activator
				.getSimulationProjectCenter().getSelectedSimulationProject();
		SimulationSettingInteractive selectedSimulationSetting = (SimulationSettingInteractive) selectedSimulationProject
				.getSimSessConfig(selectedSessionConfigName).getSimSetting();

		SimulationProjectData_SessionConfiguration_SettingInteractiveComposite tempComp = (SimulationProjectData_SessionConfiguration_SettingInteractiveComposite) compositeSettingData;

		// Network settings
		// Fill and Check Control Server
		{
			String ip = selectedSimulationSetting
					.getSimulationControlServerIp();
			String port = selectedSimulationSetting
					.getSimulationControlServerPort();

			tempComp.textCSIP.setText(ip);
			tempComp.textCSPort.setText(port);
			if (checkIfConnectionAvailable(port)) {
				tempComp.labelCSPortCheck.setText("OK");
				tempComp.labelCSPortCheck.setBackground(new Color(Display.getCurrent(), new RGB(0, 255, 0)));
			} else {
				tempComp.labelCSPortCheck.setText("IN USE");
				tempComp.labelCSPortCheck.setBackground(new Color(Display.getCurrent(), new RGB(255, 128, 128)));
			}
		}
		// Fill and Check Control Client
		{
			String ip = selectedSimulationSetting.getRuntimeControlServerIp();
			String port = selectedSimulationSetting
					.getRuntimeControlServerPort();
			tempComp.textCCIP.setText(ip);
			tempComp.textCCPort.setText(port);

			if (checkIfConnectionAvailable(port)) {
				tempComp.labelCCPortCheck.setText("OK");
				tempComp.labelCCPortCheck.setBackground(new Color(Display.getCurrent(), new RGB(0, 255, 0)));
			} else {
				tempComp.labelCCPortCheck.setText("IN USE");
				tempComp.labelCCPortCheck.setBackground(new Color(Display.getCurrent(), new RGB(255, 128, 128)));
			}
		}
		// Fill and Check Transfer Server
		{
			String ip = selectedSimulationSetting
					.getSimulationTransferServerIp();
			String port = selectedSimulationSetting
					.getSimulationTransferServerPort();
			tempComp.textTSIP.setText(ip);
			tempComp.textTSPort.setText(port);

			if (checkIfConnectionAvailable(port)) {
				tempComp.labelTSPortCheck.setText("OK");
				tempComp.labelTSPortCheck.setBackground(new Color(Display.getCurrent(), new RGB(0, 255, 0)));
			} else {
				tempComp.labelTSPortCheck.setText("IN USE");
				tempComp.labelTSPortCheck.setBackground(new Color(Display.getCurrent(), new RGB(255, 128, 128)));
			}
		}

		// Simulation settings
		// To display a value with its correct unit it is recommended to check
		// the value

		Double tempSimStepTimeAsDouble = 0.0;
		if (!selectedSimulationSetting.getSimStepTime().isEmpty()) { // If the
																		// string
																		// is
																		// empty
																		// ("")
																		// it
																		// can't
																		// converts
																		// into
																		// an
																		// double
			try {
				tempSimStepTimeAsDouble = Double
						.valueOf(selectedSimulationSetting.getSimStepTime()); // exception it the value is not a valid double value
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		// TODO [20100909] Die Einheit sollte eine ComboBox sein, der Benutzer
		// wählt die gewünschte Einheit und der wert wird in dieser angezeigt!

		if (tempSimStepTimeAsDouble < 1) {
			tempComp.textIntervalls
					.setText(roundScale(tempSimStepTimeAsDouble * 1000) + "");
			tempComp.textIntervalUnit.setText("ms");
		} else if (tempSimStepTimeAsDouble > 3600) {
			tempComp.textIntervalls
					.setText(roundScale(tempSimStepTimeAsDouble / 3600) + "");
			tempComp.textIntervalUnit.setText("h");
		} else if (tempSimStepTimeAsDouble > 60) {
			tempComp.textIntervalls
					.setText(roundScale(tempSimStepTimeAsDouble / 60) + "");
			tempComp.textIntervalUnit.setText("min");
		} else {
			tempComp.textIntervalls.setText(roundScale(tempSimStepTimeAsDouble)
					+ "");
			tempComp.textIntervalUnit.setText("s");
		}

		tempComp.textTolerance
				.setText(selectedSimulationSetting.getTolerance());

		tempComp.labelUsedSolver.setText(selectedSimulationSetting.getSolver());
	}

	/**
	 * Fills the first tab with simulation and network settings of a selected
	 * simulation session configuration.
	 *
	 * @param selectedSessionConfigName the selected session config name
	 */
	private void fillTabItem1ForNonInteractive(
			String selectedSessionConfigName) {
		SimulationProject selectedSimulationProject = Activator
				.getSimulationProjectCenter().getSelectedSimulationProject();
		SimulationSettingNonInteractive selectedSimulationSetting = (SimulationSettingNonInteractive) selectedSimulationProject
				.getSimSessConfig(selectedSessionConfigName).getSimSetting();

		SimulationProjectData_SessionConfiguration_SettingNonInteractiveComposite tempComp = (SimulationProjectData_SessionConfiguration_SettingNonInteractiveComposite) compositeSettingData;

		// Simulation settings
		// To display a value with its correct unit it is recommended to check
		// the value

		Double tempSimStepTimeAsDouble = 0.0;
		if (!selectedSimulationSetting.getSimStepTime().isEmpty()) { // If the
																		// string
																		// is
																		// empty
																		// ("")
																		// it
																		// can't
																		// converts
																		// into
																		// an
																		// double
			try {
				tempSimStepTimeAsDouble = Double
						.valueOf(selectedSimulationSetting.getSimStepTime()); // exception it the value is not a valid double value
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		// TODO [20100909] Die Einheit sollte eine ComboBox sein, der Benutzer
		// wählt die gewünschte Einheit und der wert wird in dieser angezeigt!

		if (tempSimStepTimeAsDouble < 1) {
			tempComp.textIntervalls
					.setText(roundScale(tempSimStepTimeAsDouble * 1000) + "");
			tempComp.textIntervalUnit.setText("ms");
		} else if (tempSimStepTimeAsDouble > 3600) {
			tempComp.textIntervalls
					.setText(roundScale(tempSimStepTimeAsDouble / 3600) + "");
			tempComp.textIntervalUnit.setText("h");
		} else if (tempSimStepTimeAsDouble > 60) {
			tempComp.textIntervalls
					.setText(roundScale(tempSimStepTimeAsDouble / 60) + "");
			tempComp.textIntervalUnit.setText("min");
		} else {
			tempComp.textIntervalls.setText(roundScale(tempSimStepTimeAsDouble)
					+ "");
			tempComp.textIntervalUnit.setText("s");
		}

		tempComp.textStart.setText(selectedSimulationSetting.getStart());
		tempComp.textStop.setText(selectedSimulationSetting.getStop());
		tempComp.textTolerance
				.setText(selectedSimulationSetting.getTolerance());

		tempComp.labelUsedSolver.setText(selectedSimulationSetting.getSolver());
	}

	/**
	 * Fills the second tab with model configuration data of a selected
	 * simulation session configuration.
	 *
	 * @param selectedSessionConfigName the selected session config name
	 */
	private void fillTabItem2(String selectedSessionConfigName) {
		SimulationProject selectedSimulationProject = Activator
				.getSimulationProjectCenter().getSelectedSimulationProject();

		ModelicaModel selectedModelicaModelConfiguration = selectedSimulationProject
				.getSimSessConfig(selectedSessionConfigName).getModelicaModel();
		buildTreeConfigModel(selectedModelicaModelConfiguration);
	}

	/**
	 * Controls the availability of buttons (delete, modify, usetocreate, startsimulation).
	 */
	private void enableButtons() {
		buttonDelete.setEnabled(true);
		buttonModify.setEnabled(true);
		buttonUseToCreate.setEnabled(true);
		
		
		if (compositeSettingData instanceof SimulationProjectData_SessionConfiguration_SettingInteractiveComposite){ //Check if the selected config is for an interactive simulation or non
				if(!Activator.getSimulationCenter_Interactive()
						.getSimulationStatus().equals("")) { //Check if an interactive simulation has already been started
					buttonStartSimulation.setEnabled(true);
				}
				else {//Check if an nother applikation are using the ports
					if (compositeSettingData instanceof SimulationProjectData_SessionConfiguration_SettingInteractiveComposite
						&& ((SimulationProjectData_SessionConfiguration_SettingInteractiveComposite) compositeSettingData).labelCCPortCheck 
								.getText().equals("IN USE")
						|| ((SimulationProjectData_SessionConfiguration_SettingInteractiveComposite) compositeSettingData).labelCSPortCheck
								.getText().equals("IN USE")
						|| ((SimulationProjectData_SessionConfiguration_SettingInteractiveComposite) compositeSettingData).labelTSPortCheck
								.getText().equals("IN USE")) {

						buttonStartSimulation.setEnabled(false);
						MessageDialog
							.openError(
								getShell(),
								"Network connection check failed",
								"One or more ports you would like to use for the simulation are in use. Please change all ports which are marked red.");
					}
					else
						buttonStartSimulation.setEnabled(true);
				}
		} 
		else {
			buttonStartSimulation.setEnabled(true);
		}

//		if (compositeSettingData instanceof SimulationProjectData_SessionConfiguration_SettingInteractiveComposite
//				&& (((SimulationProjectData_SessionConfiguration_SettingInteractiveComposite) compositeSettingData).labelCCPortCheck
//						.getText().equals("IN USE")
//							|| ((SimulationProjectData_SessionConfiguration_SettingInteractiveComposite) compositeSettingData).labelCSPortCheck
//								.getText().equals("IN USE") 
//							|| ((SimulationProjectData_SessionConfiguration_SettingInteractiveComposite) compositeSettingData).labelTSPortCheck
//								.getText().equals("IN USE"))) {
//
//			buttonStartSimulation.setEnabled(false);
//			MessageDialog
//					.openError(
//							getShell(),
//							"Network connection check failed",
//							"One or more ports you would like to use for the simulation are in use. Please change all ports which are marked red.");
//		} else {
//			buttonStartSimulation.setEnabled(true);
//		}
	}

	/**
	 * Builds the tree config model.
	 *
	 * @param modelicaModel the modelica model
	 */
	private void buildTreeConfigModel(ModelicaModel modelicaModel) {
		if (modelicaModel != null) {
			treeConfigModel.removeAll();
			TreeItem rootItem = new TreeItem(treeConfigModel, SWT.NULL);
			rootItem.setText(modelicaModel.getName());

			Set<String> fullQualifiedVariableNames = modelicaModel
					.getMainclass().getAllFullQualifiedVariableNames();

			Set<String> fullQualifiedParameterNames = modelicaModel
					.getMainclass().getAllFullQualifiedParameterNames();

			for (String fullQualifiedName : fullQualifiedParameterNames) {
				createModelElement(modelicaModel, rootItem, fullQualifiedName,
						fullQualifiedName, 'p');
			}

			for (String fullQualifiedName : fullQualifiedVariableNames) {
				createModelElement(modelicaModel, rootItem, fullQualifiedName,
						fullQualifiedName, 'v');
			}
		} else {
			treeConfigModel.removeAll();
		}
	}

	/**
	 * Recursive method to build the treeModel with all nodes and child elements.
	 *
	 * @param modelicaModel the modelica model
	 * @param item parantItem
	 * @param restOfFullQualifiedName the rest of full qualified name
	 * @param fullQualifiedName the full qualified name
	 * @param propertyType Type of the Property, only for documentation or symbols!!
	 */
	private void createModelElement(ModelicaModel modelicaModel, TreeItem item,
			String restOfFullQualifiedName, String fullQualifiedName,
			char propertyType) {

		Scanner sc = new Scanner(restOfFullQualifiedName);
		sc.useDelimiter("\\.");
		if (sc.hasNext()) {
			String name = sc.next();

			if (item.getItems().length != 0) {
				boolean hasChild = false;
				for (TreeItem childItem : item.getItems()) {
					if (childItem.getText().equals(name)) {
						if (sc.hasNextLine())
							createModelElement(modelicaModel, childItem,
									sc.nextLine(), fullQualifiedName,
									propertyType);
						hasChild = true;
						break;
					}
				}
				if (!hasChild) {
					TreeItem newChildItem = new TreeItem(item, SWT.NULL);
					newChildItem.setText(name);
					if (sc.hasNextLine()) {
						createModelElement(modelicaModel, newChildItem,
								sc.nextLine(), fullQualifiedName, propertyType);
					} else {
						newChildItem.setData(fullQualifiedName);
						if (propertyType == 'v') {
							newChildItem.setText(newChildItem.getText()
									+ "  [Value: "
									+ modelicaModel
											.getMainclass()
											.getVariableByName(
													fullQualifiedName)
											.getPrimitiveValue()
									+ " | Typ: Variable]");
						} else if (propertyType == 'p') {
							newChildItem.setText(newChildItem.getText()
									+ "  [Value: "
									+ modelicaModel
											.getMainclass()
											.getParameterByName(
													fullQualifiedName)
											.getPrimitiveValue()
									+ " | Typ: Parameter]");
						}
					}
				}
			} else {
				TreeItem newChildItem = new TreeItem(item, SWT.NULL);
				newChildItem.setText(name);
				if (sc.hasNextLine()) {
					createModelElement(modelicaModel, newChildItem,
							sc.nextLine(), fullQualifiedName, propertyType);
				} else {
					newChildItem.setData(fullQualifiedName);
					if (propertyType == 'v') {
						newChildItem.setText(newChildItem.getText()
								+ "  [Value: "
								+ modelicaModel.getMainclass()
										.getVariableByName(fullQualifiedName)
										.getPrimitiveValue()
								+ " | Typ: Variable]");
					} else if (propertyType == 'p') {
						newChildItem.setText(newChildItem.getText()
								+ "  [Value: "
								+ modelicaModel.getMainclass()
										.getParameterByName(fullQualifiedName)
										.getPrimitiveValue()
								+ " | Typ: Parameter]");
					}
				}
			}
		}
	}

	// TODO [20100909] ERZEUGT WAHRSCHEINLICH KEINE SESS CONFIG...! Checken...!
	/**
	 * Builds the tree configs.
	 *
	 * @param selectedSimulationProject the selected simulation project
	 */
	private void buildTreeConfigs(SimulationProject selectedSimulationProject) {
		if (selectedSimulationProject != null) {
			for (String simsessconfigName : selectedSimulationProject
					.getSimSessionConfigs()) {
				// Calendar cal = Calendar.getInstance();
				// cal.setTimeInMillis(Activator.getSimulationProjectCenter()
				// .getFileLastModifiedDate(simsessconfigName));
				// DateFormat formatter = new SimpleDateFormat();

				// String date = (formatter.format(cal.getTime()));
				TreeItem sessionConfigItem = new TreeItem(treeConfigs, 0);
				// sessionConfigItem.setText(simsessconfigName);
				int numberOfRuns = selectedSimulationProject
						.getSimSessConfig(simsessconfigName).getSimSetting()
						.getNumberOfRuns();
				String type =  selectedSimulationProject
				.getSimSessConfig(simsessconfigName).getSimSetting()
				.getType();
				sessionConfigItem.setText(simsessconfigName + " ["
						+ numberOfRuns + " times used]");
				sessionConfigItem.setData(simsessconfigName);
				
				if(type.equals("i")){
					LocalResourceManager resources 
				    = new LocalResourceManager(JFaceResources.getResources(), treeConfigs);
					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/settingsInteractive.gif");
					Image i = imageDescriptor.createImage();
					resources.dispose();
					sessionConfigItem.setImage(i);
				}
				else if(type.equals("ni")){
					LocalResourceManager resources 
				    = new LocalResourceManager(JFaceResources.getResources(), treeConfigs);
					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/settingsNonInteractive.gif");
					Image i = imageDescriptor.createImage();
					resources.dispose();
					sessionConfigItem.setImage(i);
				}
			}

		} else {
			treeConfigs.removeAll();
			styledTextSelectedSessionConfigData.setText("");
			// TODO remove other config composites...
		}
	}

	/**
	 * Rounding of a double value to a specified scale using Math.rint
	 * 
	 * @param d
	 *            double value to be rounded
	 * @return a scaled double value
	 * @see Math#rint(double)
	 */
	private double roundScale(double d) {
		return Math.rint(d * 1000) / 1000.;
	}

	/**
	 * Try to create a Socket using a specified IP and a Port. TODO [20100909]
	 * This version does not support a Server on another ip as the localhost
	 * (127.0.0.1)
	 *
	 * @param portString the port string
	 * @return true if the port is available and false if the port is in use...!
	 */
	private boolean checkIfConnectionAvailable(String portString) {

		int port = 0;
		if (!portString.isEmpty()) { // If the string is empty ("") it can't
										// converts into an integer
			try {
				port = Integer.valueOf(portString); // exception it the value is
													// not a valid integer value
			} catch (Exception e) {
				System.err.println(e.getMessage()); // TODO [20100909] NaN muss
													// schon vorher abgefangen
													// werden... bei benutzer
													// eingabe!
				return false;
			}
		}

		try {
			ServerSocket server = new ServerSocket(port);
			server.close();
		} catch (UnknownHostException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * Create an InitTXT object using the setting and configured model file.
	 *
	 * @param initFilePath the init file path
	 * @param ti the ti
	 * @return a full initialized InitTXT object with data from setting and
	 * configured model
	 */
	private InitTXT createInitTXTObject(String initFilePath, TreeItem ti) {

		SimulationProject selectedSimulationProject = Activator
				.getSimulationProjectCenter().getSelectedSimulationProject();
		String selectedSessionConfigName = (String) ti.getData();
		SimulationSessionConfiguration selectedSimulationSessionConfiguration = selectedSimulationProject
				.getSimSessConfig(selectedSessionConfigName);

//		/**
//		 * Path to model_init.txt
//		 */
//		String initFilePath = Activator.getSimulationProjectCenter()
//				.getSimulationProjectPath(
//						selectedSimulationProject.getProjectName())
//				+ "/"
//				+ selectedSimulationProject.getFullQualifiedModelicaModelName()
//				+ "_init.txt";
		/*
		 * The original init.txt file have to be read again to specified the
		 * order of variables into it TODO [201009] Erzeugen, Property
		 * Reihenfolge muss eingehalten werden daher original einlesen und werte
		 * ersetzen (teuer) aber einzige Möglichkeit variables wieder in state
		 * und alg zu teilen. PROBLEM nicht nur die Reihenfolge der typen muss
		 * eingehalten werden sondern auch die exacte folge der variablen…
		 */
		InitTXT originalInitTXT = null;
		
		//TODO [20110309] After the first parsing of the init file the exception won't occure anymore
		try {
			originalInitTXT = SimulationInit_TXT_reader
			.readInit(initFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		originalInitTXT.fileName = selectedSimulationProject
				.getFullQualifiedModelicaModelName() + "_init.txt";

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
				
				originalInitTXT.n_int_variablesProperties
				.put(varName,
						new Double((Integer)selectedSimulationSessionConfiguration
								.getModelicaModel().getMainclass()
								.getVariableByName(varName)
								.getPrimitiveValue()));
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
}

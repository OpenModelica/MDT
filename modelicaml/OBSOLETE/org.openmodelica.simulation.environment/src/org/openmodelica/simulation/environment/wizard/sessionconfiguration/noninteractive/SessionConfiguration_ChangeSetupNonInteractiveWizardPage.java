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
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.openmodelica.simulation.core.models.simulation.SimulationSetting;
import org.openmodelica.simulation.core.models.simulation.SimulationSettingNonInteractive;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * WizardPage to modify the session configuration setup
 * This WizardPage can be used to create a new page from initial or using an sample.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 */
public class SessionConfiguration_ChangeSetupNonInteractiveWizardPage extends WizardPage {

	/** The composite1. */
	private Composite composite1;
	
	/** The group session config data. */
	private Group groupSessionConfigData;
	
	/** The text comment. */
	private Text textComment;
	
	/** The label comment. */
	private Label labelComment;
	
	/** The label company. */
	private Label labelCompany;
	
	/** The text company. */
	private Text textCompany;
	
	/** The text author. */
	private Text textAuthor;
	
	/** The label author. */
	private Label labelAuthor;
	
	/** The text version. */
	private Text textVersion;
	
	/** The button start simulation. */
	private Button buttonStartSimulation;
	
	/** The text stop. */
	private Text textStop;
	
	/** The label stop. */
	private Label labelStop;
	
	/** The text start. */
	private Text textStart;
	
	/** The label start. */
	private Label labelStart;
	
	/** The label version. */
	private Label labelVersion;
	
	/** The text session configuration name. */
	private Text textSessionConfigurationName;
	
	/** The label session configuration name. */
	private Label labelSessionConfigurationName;
	
	/** The composite buttons. */
	private Composite compositeButtons;
	
	/** The group simulation setup. */
	private Group groupSimulationSetup;	
	
	/** The button restore. */
	private Button buttonRestore;
	
	/** 0: DASSL2 1: DASSL 2: RUNGEKUTTA 3: EULER. */
	private Combo comboSolver;
	
	/** The label solver. */
	private Label labelSolver;
	
	/** The text tolerance. */
	private Text textTolerance;
	
	/** The label tolerance. */
	private Label labelTolerance;
	
	/** 0: ms, 1: s, 2: min, 3: h. */
	private Combo comboIntervalUnit;
	
	/** The text interval. */
	private Text textInterval;
	
	/** The label interval. */
	private Label labelInterval;
	
	/** The composite simulation setup interval. */
	private Composite compositeSimulationSetupInterval;

	/** Use this variable to setup the wizard page default = Create a new setup using the default settings from properties change = Change an existing configuration using a sample configuration use = Create a new setup using an existing configuration as sample. */
	String option = "";
	
	/** The sample setting. */
	SimulationSetting sampleSetting;
	
	/**
	 * Default Constructor. Default Setting from preferences will be used
	 *
	 * @param pageName the page name
	 */
	protected SessionConfiguration_ChangeSetupNonInteractiveWizardPage(String pageName) {
		super(pageName);
		setTitle("Prepare a non-interactive simulation session: Simulation Setup");
        setMessage("Please setup the simulation. Please fill all information marked with (*).");
	}
	
	/**
	 * A sample SimulationSetting will be used.
	 *
	 * @param pageName the page name
	 * @param setting a sample SimulationSetting
	 * @param option use the string 'change' if the given configuration file already exists and have to be modified, 'use' if a new file should be created using a sample file
	 */
	protected SessionConfiguration_ChangeSetupNonInteractiveWizardPage(String pageName, SimulationSetting setting, String option) {
		super(pageName);
		setTitle("Prepare a non-interactive simulation session: Simulation Setup");
        setMessage("Please setup the simulation. Please fill all information marked with (*)");
        sampleSetting = setting;
        this.option = option;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		composite1 = new Composite(parent, SWT.NONE);
		GridLayout composite1Layout = new GridLayout();
		composite1Layout.makeColumnsEqualWidth = true;
		GridData composite1LData = new GridData();
		composite1LData.verticalAlignment = GridData.FILL;
		composite1LData.horizontalAlignment = GridData.FILL;
		composite1LData.grabExcessHorizontalSpace = true;
		composite1LData.grabExcessVerticalSpace = true;
		composite1.setLayoutData(composite1LData);
		composite1.setLayout(composite1Layout);
		{
			groupSessionConfigData = new Group(composite1, SWT.NONE);
			GridLayout groupSessionConfigDataLayout = new GridLayout();
			groupSessionConfigDataLayout.makeColumnsEqualWidth = true;
			groupSessionConfigDataLayout.numColumns = 3;
			groupSessionConfigData.setLayout(groupSessionConfigDataLayout);
			GridData groupSessionConfigDataLData = new GridData();
			groupSessionConfigDataLData.horizontalAlignment = GridData.FILL;
			groupSessionConfigDataLData.grabExcessHorizontalSpace = true;
			groupSessionConfigDataLData.verticalAlignment = GridData.FILL;
			groupSessionConfigDataLData.grabExcessVerticalSpace = true;
			groupSessionConfigData.setLayoutData(groupSessionConfigDataLData);
			groupSessionConfigData.setText("Configuration Data");
			{
				labelSessionConfigurationName = new Label(
						groupSessionConfigData, SWT.NONE);
				GridData labelSessionConfigurationNameLData = new GridData();
				labelSessionConfigurationName
						.setLayoutData(labelSessionConfigurationNameLData);
				labelSessionConfigurationName.setText("*Configuration Name:");
			}
			{
				textSessionConfigurationName = new Text(groupSessionConfigData,
						SWT.BORDER);
				GridData textSessionConfigurationNameLData = new GridData();
				textSessionConfigurationNameLData.grabExcessHorizontalSpace = true;
				textSessionConfigurationNameLData.horizontalAlignment = GridData.FILL;
				textSessionConfigurationNameLData.horizontalSpan = 2;
				textSessionConfigurationName
						.setLayoutData(textSessionConfigurationNameLData);
				
				Date dt = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
				
				textSessionConfigurationName.setText("Config_" + df.format(dt));
				
				textSessionConfigurationName
						.addModifyListener(new ModifyListener() {
							public void modifyText(ModifyEvent evt) {
								
								if(option.equals("use") && !sampleSetting.getName().isEmpty() && sampleSetting.getName().equalsIgnoreCase(textSessionConfigurationName.getText().trim()) )
									textSessionConfigurationName.setText("copyOf" + sampleSetting.getName()); //If a new config file should be created using a sample the sample config name have to be modified other wise the file will be overwritten
								//TODO [201009] Gleicher dateiname kann übergangen werden indem NACHTRÄGLICH name über "Change" modifiziert wird, also vorher system ordner auf gleiche namen prüfen aber nur bei change!
								dialogChanged();
							}
						});
				if(option.equals("change"))
					textSessionConfigurationName.setEditable(false); //The configuration file should be overwritten
			}
			{
				labelVersion = new Label(groupSessionConfigData, SWT.NONE);
				GridData labelVersionLData = new GridData();
				labelVersion.setLayoutData(labelVersionLData);
				labelVersion.setText("*Version:");
			}
			{
				textVersion = new Text(groupSessionConfigData, SWT.BORDER);
				GridData textVersionLData = new GridData();
				textVersionLData.horizontalSpan = 2;
				textVersionLData.horizontalAlignment = GridData.FILL;
				textVersionLData.grabExcessHorizontalSpace = true;
				textVersion.setLayoutData(textVersionLData);
				
				if(sampleSetting.getVersion().isEmpty() || sampleSetting.getVersion() == null)
					textVersion.setText("1.0");
				else
					textVersion.setText(sampleSetting.getVersion());
				
				textVersion.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent evt) {
						dialogChanged();
					}
				});
			}
			{
				labelAuthor = new Label(groupSessionConfigData, SWT.NONE);
				GridData labelAuthorLData = new GridData();
				labelAuthor.setLayoutData(labelAuthorLData);
				labelAuthor.setText("Author:");
			}
			{
				GridData textAuthorLData = new GridData();
				textAuthorLData.horizontalSpan = 2;
				textAuthorLData.horizontalAlignment = GridData.FILL;
				textAuthorLData.grabExcessHorizontalSpace = true;
				textAuthor = new Text(groupSessionConfigData, SWT.BORDER);
				textAuthor.setLayoutData(textAuthorLData);
				
				textAuthor.setText(sampleSetting.getAuthor());
			}
			{
				labelCompany = new Label(groupSessionConfigData, SWT.NONE);
				GridData labelCompanyLData = new GridData();
				labelCompany.setLayoutData(labelCompanyLData);
				labelCompany.setText("Company:");
			}
			{
				GridData textCompanyLData = new GridData();
				textCompanyLData.horizontalSpan = 2;
				textCompanyLData.horizontalAlignment = GridData.FILL;
				textCompanyLData.grabExcessHorizontalSpace = true;
				textCompany = new Text(groupSessionConfigData, SWT.BORDER);
				textCompany.setLayoutData(textCompanyLData);
//				if(option.equals("change"))
//					textCompany.setText(sampleSetting.getCompany());
//				else
//					textCompany.setText("");
				
				textCompany.setText(sampleSetting.getCompany());
			}
			{
				labelComment = new Label(groupSessionConfigData, SWT.NONE);
				GridData labelCommentLData = new GridData();
				labelComment.setLayoutData(labelCommentLData);
				labelComment.setText("Comment:");
			}
			{
				textComment = new Text(groupSessionConfigData, SWT.MULTI
						| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
				GridData textCommentLData = new GridData();
				textCommentLData.horizontalSpan = 2;
				textCommentLData.horizontalAlignment = GridData.FILL;
				textCommentLData.grabExcessHorizontalSpace = true;
				textCommentLData.verticalAlignment = GridData.FILL;
				textCommentLData.verticalSpan = 10;
				textCommentLData.grabExcessVerticalSpace = true;
				textComment.setLayoutData(textCommentLData);
//				if(option.equals("change"))
//					textComment.setText(sampleSetting.getComment());
//				else
//					textComment.setText("");
				
				textComment.setText(sampleSetting.getComment());
				
			}
		}
		{
			groupSimulationSetup = new Group(composite1, SWT.NONE);
			GridLayout groupSimulationSetupLayout = new GridLayout();
			groupSimulationSetupLayout.makeColumnsEqualWidth = true;
			groupSimulationSetup.setLayout(groupSimulationSetupLayout);
			GridData groupSimulationSetupLData = new GridData();
			groupSimulationSetupLData.horizontalAlignment = GridData.FILL;
			groupSimulationSetupLData.grabExcessHorizontalSpace = true;
			groupSimulationSetup.setLayoutData(groupSimulationSetupLData);
			groupSimulationSetup.setText("Simulation Setup");
			{
				compositeSimulationSetupInterval = new Composite(
						groupSimulationSetup, SWT.NONE);
				GridLayout compositeSimulationSetupIntervalLayout = new GridLayout();
				compositeSimulationSetupIntervalLayout.makeColumnsEqualWidth = true;
				compositeSimulationSetupIntervalLayout.numColumns = 3;
				GridData compositeSimulationSetupIntervalLData = new GridData();
				compositeSimulationSetupIntervalLData.horizontalAlignment = GridData.FILL;
				compositeSimulationSetupIntervalLData.grabExcessHorizontalSpace = true;
				compositeSimulationSetupInterval
						.setLayoutData(compositeSimulationSetupIntervalLData);
				compositeSimulationSetupInterval
						.setLayout(compositeSimulationSetupIntervalLayout);
				{
					labelStart = new Label(compositeSimulationSetupInterval, SWT.NONE);
					GridData labelStartLData = new GridData();
					labelStart.setLayoutData(labelStartLData);
					labelStart.setText("Start:");
				}
				{
					textStart = new Text(compositeSimulationSetupInterval, SWT.BORDER);
					GridData textStartLData = new GridData();
					textStartLData.horizontalSpan = 2;
					textStartLData.horizontalAlignment = GridData.FILL;
					textStart.setLayoutData(textStartLData);
					textStart.setText(sampleSetting.getStart());
					textStart.setToolTipText("The act. version of OM does only support the start value 0.0s");
					textStart.addModifyListener(new ModifyListener() {
						public void modifyText(ModifyEvent evt) {
							dialogChanged();
						}
					});
				}
				{
					labelStop = new Label(compositeSimulationSetupInterval, SWT.NONE);
					GridData labelStopLData = new GridData();
					labelStop.setLayoutData(labelStopLData);
					labelStop.setText("Stop:");
				}
				{
					textStop = new Text(compositeSimulationSetupInterval, SWT.BORDER);
					GridData textStopLData = new GridData();
					textStopLData.horizontalSpan = 2;
					textStopLData.horizontalAlignment = GridData.FILL;
					textStop.setLayoutData(textStopLData);
					textStop.setText(sampleSetting.getStop());
					textStop.addModifyListener(new ModifyListener() {
						public void modifyText(ModifyEvent evt) {
							dialogChanged();
							if(textStop.getText() != null && !textStop.getText().isEmpty() && textStart.getText() != null && !textStart.getText().isEmpty())
								try{
									Double startDouble = new Double(textStart.getText());
									Double stopDouble = new Double(textStop.getText());
									
									if(stopDouble > startDouble){
										textInterval.setText(((stopDouble - startDouble) * 0.01) + "");
									}
								}catch (Exception e) {
									// TODO: handle exception
								}
						}
					});
				}
				{
					labelInterval = new Label(compositeSimulationSetupInterval,
							SWT.NONE);
					GridData labelIntervalLData = new GridData();
					labelInterval.setLayoutData(labelIntervalLData);
					labelInterval.setText("*Output Interval:");
				}
				{
					textInterval = new Text(compositeSimulationSetupInterval,
							SWT.BORDER);
					GridData textIntervalLData = new GridData();
					textIntervalLData.horizontalAlignment = GridData.FILL;
					textInterval.setLayoutData(textIntervalLData);
					textInterval.setText(sampleSetting.getSimStepTime());
					textInterval.addModifyListener(new ModifyListener() {
						public void modifyText(ModifyEvent evt) {
							dialogChanged();
						}
					});
				}
				{
					comboIntervalUnit = new Combo(
							compositeSimulationSetupInterval, SWT.BORDER | SWT.READ_ONLY);
					GridData comboIntervalUnitLData = new GridData();
					comboIntervalUnitLData.horizontalAlignment = GridData.FILL;
					comboIntervalUnit.setLayoutData(comboIntervalUnitLData);
					comboIntervalUnit.setItems(new java.lang.String[] { "ms",
							"s", "min", "h" });
					comboIntervalUnit.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							dialogChanged();
						}
					});
					comboIntervalUnit.select(1);
					//TODO 20101120 passende einheit wählen und wert welcher in sec. ist anpassen
				}
				{
					labelTolerance = new Label(
							compositeSimulationSetupInterval, SWT.NONE);
					GridData labelToleranceLData = new GridData();
					labelTolerance.setLayoutData(labelToleranceLData);
					labelTolerance.setText("*Tolerance:");
				}
				{
					textTolerance = new Text(compositeSimulationSetupInterval,
							SWT.BORDER);
					GridData textToleranceLData = new GridData();
					textToleranceLData.horizontalSpan = 2;
					textToleranceLData.horizontalAlignment = GridData.FILL;
					textTolerance.setLayoutData(textToleranceLData);
					
					textTolerance.setText(sampleSetting.getTolerance());
					
					textTolerance.addModifyListener(new ModifyListener() {
						public void modifyText(ModifyEvent evt) {
							dialogChanged();
						}
					});
				}
				{
					labelSolver = new Label(compositeSimulationSetupInterval,
							SWT.NONE);
					GridData labelSolverLData = new GridData();
					labelSolver.setLayoutData(labelSolverLData);
					labelSolver.setText("*Solver:");
				}
				{
					GridData comboSolverLData = new GridData();
					comboSolverLData.horizontalSpan = 2;
					comboSolverLData.horizontalAlignment = GridData.FILL;
					comboSolver = new Combo(compositeSimulationSetupInterval,
							SWT.BORDER | SWT.READ_ONLY);
					comboSolver.setLayoutData(comboSolverLData);
					comboSolver.setItems(new java.lang.String[] { "dassl2", "dassl", "rungekutta", "euler" });
					comboSolver.select(0);
				}
			}
		}
		{
			compositeButtons = new Composite(composite1, SWT.NONE);
			GridLayout compositeButtonsLayout = new GridLayout();
			compositeButtonsLayout.makeColumnsEqualWidth = true;
			GridData compositeButtonsLData = new GridData();
			compositeButtonsLData.horizontalAlignment = GridData.FILL;
			compositeButtonsLData.grabExcessHorizontalSpace = true;
			compositeButtons.setLayoutData(compositeButtonsLData);
			compositeButtons.setLayout(compositeButtonsLayout);
			{
				buttonRestore = new Button(compositeButtons, SWT.PUSH
						| SWT.CENTER);
				GridData buttonRestoreLData = new GridData();
				buttonRestoreLData.verticalAlignment = GridData.FILL;
				buttonRestore.setLayoutData(buttonRestoreLData);
				buttonRestore.setText("Restore Default");
				LocalResourceManager resources 
			    = new LocalResourceManager(JFaceResources.getResources(), buttonRestore);
				ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/clear.gif");
				Image i = imageDescriptor.createImage();
				resources.dispose();
				buttonRestore.setImage(i);
				buttonRestore.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						super.widgetSelected(e);
						MessageDialog messageDialog = new MessageDialog(
								getShell(),
								"Restore Default",
								null,
								"Are you sure you want to reset the fields to default?",
								MessageDialog.QUESTION, new String[] {
										"Restore", "Cancel" }, 1);
						if (messageDialog.open() == 0) {
							useSettingAsSample();
						}

					}
				});
			}
			{
				buttonStartSimulation = new Button(compositeButtons, SWT.CHECK | SWT.LEFT);
				GridData buttonStartSimulationLData = new GridData();
				buttonStartSimulation.setLayoutData(buttonStartSimulationLData);
				buttonStartSimulation.setText("Start Simulation");
				buttonStartSimulation.setToolTipText("Please check this button to start a simulation afterwards. Default: Checked");
				buttonStartSimulation.setSelection(true);
			}
		}
		useSettingAsSample();
		setControl(composite1);
	}
	
	/**
	 * Use a SimulationSetting as sample to fill fields with given setting data.
	 *
	 */
	private void useSettingAsSample(){
		
		try{
			if(option.equals("use") && !sampleSetting.getName().isEmpty()) //If getName is empty the setting is created from a default
				textSessionConfigurationName.setText("copyOf" + sampleSetting.getName()); //If a new config file should be created using a sample the sample config name have to be modified other wise the file will be overwritten
			else
				textSessionConfigurationName.setText(sampleSetting.getName());
			
			textAuthor.setText(sampleSetting.getAuthor());
			textCompany.setText(sampleSetting.getCompany());
			textComment.setText(sampleSetting.getCompany());
			textVersion.setText(sampleSetting.getVersion());
			
			Double sampleInterval = new Double(sampleSetting.getSimStepTime());
			
			if(sampleInterval < 0){ //sampleInterval in ms
				textInterval.setText(sampleInterval * 1000 + "");
				comboIntervalUnit.select(0);
			}else if(sampleInterval < 60){ //sampleInterval in s
				textInterval.setText(sampleInterval + "");
				comboIntervalUnit.select(1);
			}else if(sampleInterval < 3600){ //sampleInterval in min
				textInterval.setText(sampleInterval / 60 + "");
				comboIntervalUnit.select(2);
			}else {//sampleInterval in h
				textInterval.setText(sampleInterval / 3600 + "");
				comboIntervalUnit.select(3);
			}
			
			
			textTolerance.setText(sampleSetting.getTolerance());
			comboSolver.select(comboSolver.indexOf(sampleSetting.getSolver()));
		}catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * Dialog changed.
	 */
	private void dialogChanged() {
		//VALIDATE project name
		if (textSessionConfigurationName.getText() == null || textSessionConfigurationName.getText().isEmpty()) {
			updateStatus("Please enter a configuration name.");
			return;
		}
		
		if (!textSessionConfigurationName.getText().matches("\\w*")) {
			updateStatus("Please enter a valid configuration name. Only word characters are allowed [a-z A-Z _ 0-9].");
			return;
		}
		
		//VALIDATE project version
		if (textVersion.getText() == null || textVersion.getText().isEmpty()) {
			updateStatus("Please enter a configuration version.");
			return;
		}
		
		//TODO einzelnes zeichen geht nicht... [201009]
		if (!textVersion.getText().matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*[a-zA-Z0-9]")) {
			updateStatus("Please enter a valid configuration version. Only word characters [a-z A-Z 0-9] and dots [.] are allowed.");
			return;
		}
		
		//VALIDATE Start
		if (textStart.getText() == null || textStart.getText().isEmpty()) {
			updateStatus("Please enter a start value for the calculation.");
			return;
		}
		
		//Check if its a double value no need to catch exponent e.g. 2e10
		try {
			new Double(textStart.getText());
		} catch (Exception e) {
			updateStatus("Please enter a valid start value.");
			return;
		}
		
		//VALIDATE Stop
		if (textStop.getText() == null || textStop.getText().isEmpty()) {
			updateStatus("Please enter a stop value for the calculation.");
			return;
		}
		
		//Check if its a double value no need to catch exponent e.g. 2e10
		try {
			new Double(textStop.getText());
		} catch (Exception e) {
			updateStatus("Please enter a valid stop value.");
			return;
		}
		
		//Check if its a double value no need to catch exponent e.g. 2e10
		try {
			Double start = new Double(textStart.getText());
			Double stop = new Double(textStop.getText());
			
			if(stop <= start)
				updateStatus("The stop value is smaller than the start value");
		} catch (Exception e) {
			updateStatus("Please enter a valid stop value.");
			return;
		}
		
		
		//VALIDATE StepTime
		if (textInterval.getText() == null || textInterval.getText().isEmpty()) {
			updateStatus("Please enter an output interval value.");
			return;
		}
		
		//Check if its a double value no need to catch exponent e.g. 2e10
		try {
			new Double(textInterval.getText());
		} catch (Exception e) {
			updateStatus("Please enter a valid output interval value.");
			return;
		}
		
		Double simStepTimeInSeconds = 0.0;
		
		if(comboIntervalUnit.getText().equals("s")){
			simStepTimeInSeconds = new Double(textInterval.getText());
		}else if(comboIntervalUnit.getText().equals("ms")){
			simStepTimeInSeconds = ((new Double(textInterval.getText())) / 1000);
		}else if(comboIntervalUnit.getText().equals("min")){
			simStepTimeInSeconds = ((new Double(textInterval.getText())) * 60);
		}else if(comboIntervalUnit.getText().equals("h")){
			simStepTimeInSeconds = ((new Double(textInterval.getText())) * 3600);
		}
		
		if(simStepTimeInSeconds < 0.001){
			updateStatus("Please enter a valid output interval. Its value must not under-run 0.001 s. Your value in sec: " + simStepTimeInSeconds);
			return;
		}
		
		//VALIDATE Tolerance
		if (textTolerance.getText() == null || textTolerance.getText().isEmpty()) {
			updateStatus("Please enter a tolerance value for the calculation.");
			return;
		}
		
		//Check if its a double value no need to catch exponent e.g. 2e10
		try {
			new Double(textTolerance.getText());
		} catch (Exception e) {
			updateStatus("Please enter a valid tolerance value.");
			return;
		}
		
		updateStatus(null); //all entries are ok
	}
	
	/**
	 * Update page status be signal if page is completed or not.
	 *
	 * @param message page is completed if message is null, otherwise the user will get a message
	 */
	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
	/**
	 * Creates a {@link SimulationSetting} using the entered data from this wizard page
	 * NOTE: The data entry check should be done first.
	 *
	 * @return a SimulationSetting filled with data from this wizard page
	 */
	public SimulationSetting createSimulationSetting(){		
		SimulationSetting newSetting;
		
		String name = textSessionConfigurationName.getText();
		String version = textVersion.getText();
		String author = textAuthor.getText();
		String company = textCompany.getText();
		String comment = textComment.getText();
		int numberOfRuns = 0; //0 because it is a new setting
		
		
		String simStepTimeInSeconds = "";
		
		if(comboIntervalUnit.getText().equals("s")){
			simStepTimeInSeconds = textInterval.getText();
		}else if(comboIntervalUnit.getText().equals("ms")){
			simStepTimeInSeconds = ((new Double(textInterval.getText())) / 1000) + "";
		}else if(comboIntervalUnit.getText().equals("min")){
			simStepTimeInSeconds = ((new Double(textInterval.getText())) * 60) + "";
		}else if(comboIntervalUnit.getText().equals("h")){
			simStepTimeInSeconds = ((new Double(textInterval.getText())) * 3600) + "";
		}
		
		
		String start = textStart.getText();		
		String stop = textStop.getText();
		
		String tolerance = textTolerance.getText();
		String solver = comboSolver.getText();
		
		newSetting = new SimulationSettingNonInteractive(name, "ni", version, author, company, comment, numberOfRuns, simStepTimeInSeconds, tolerance, solver, start, stop);
		
		return newSetting;
	}
	
	/**
	 * Gets the session configuration name.
	 *
	 * @return the session configuration name
	 */
	public String getSessionConfigurationName(){
		return textSessionConfigurationName.getText();
	}
	
	/**
	 * Gets the start simulation.
	 *
	 * @return true if check button is selected and a simulation should be started after creating the config file
	 */
	public boolean getStartSimulation(){
		return buttonStartSimulation.getSelection();
	}
}

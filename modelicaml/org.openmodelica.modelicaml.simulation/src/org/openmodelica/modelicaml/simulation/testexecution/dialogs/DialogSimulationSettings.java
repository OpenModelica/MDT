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
package org.openmodelica.modelicaml.simulation.testexecution.dialogs;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.simulation.Activator;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DialogSimulationSettings extends Dialog {

	private String title = "";
	private Text textTolerance;
	private Combo comboMethod;
	private Text textStartTime;
	private Text textStopTime;
	private Text textOutputInterval;
	private Button btnCopySimulationFiles;
	
	private NamedElement umlElement;
	
	private String startTime, stopTime, outputInterval, method, tolerance, format;
	private Button btnLoadModelicaLib;
	private Button btnGenerateModelicaCode;
	private Combo comboFormat;
	
	private boolean isGenerateModelicaCodeChecked, isCopyFilesChecked, isLoadModelicaLibChecked;
	
	public DialogSimulationSettings(Shell 
			parentShell, 
			String title,
			NamedElement umlElement,
			String startTime, 
			String stopTime,
			String outputInteraval,
			String method,
			String tolerance,
			String format
			) {
		
		super(parentShell);
		setBlockOnOpen(true);
//      setShellStyle( SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP | SWT.SHELL_TRIM );
//		setShellStyle( SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.SHELL_TRIM );
//		setShellStyle( SWT.DIALOG_TRIM | SWT.ON_TOP | SWT.SHELL_TRIM );

        this.title = title;
        
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.outputInterval = outputInteraval;
		this.method = method;
		this.tolerance = tolerance;
		this.format = format;
		
		this.umlElement = umlElement;
		
		// default settings from the preferences page
		Boolean RegenerateCodeBeforeEachSimulation = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "RegenerateCodeBeforeEachSimulation", false, null);
		isGenerateModelicaCodeChecked = RegenerateCodeBeforeEachSimulation;
	}
	
	protected void configureShell(Shell shell) {
        super.configureShell(shell);
   		shell.setText(this.title);
   		shell.setSize(525, 485);
   		shell.setImage(SWTResourceManager.getImage(Activator.class, "/icons/plot.png"));
    }

	
	@Override
	protected Control createDialogArea(Composite parent) {
        final Composite composite = (Composite) super.createDialogArea(parent);
        composite.setLayout(null);
        
        Label lblModel = new Label(composite, SWT.NONE);
        lblModel.setBounds(10, 10, 490, 19);
        lblModel.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
        lblModel.setText(umlElement.getName());
        
        Label lblQname = new Label(composite, SWT.NONE);
        lblQname.setBounds(10, 30, 490, 14);
        lblQname.setFont(SWTResourceManager.getFont("Arial", 8, SWT.NORMAL));
        lblQname.setText(umlElement.getQualifiedName());
        
        Composite compositeSimParams = new Composite(composite, SWT.NONE);
        compositeSimParams.setBounds(10, 68, 497, 200);
        compositeSimParams.setLayout(null);
        
        Group grpSimulationParameters = new Group(compositeSimParams, SWT.NONE);
        grpSimulationParameters.setBounds(0, 0, 497, 200);
        grpSimulationParameters.setText("Simulation Settings");
        grpSimulationParameters.setLayout(null);
        
        Label lblStarttime = new Label(grpSimulationParameters, SWT.NONE);
        lblStarttime.setBounds(10, 33, 60, 21);
        lblStarttime.setText("startTime:");
        
        textStartTime = new Text(grpSimulationParameters, SWT.BORDER);
        textStartTime.addModifyListener(new ModifyListener() {
        	public void modifyText(ModifyEvent e) {
        		startTime = textStartTime.getText();
        	}
        });
        textStartTime.setBounds(102, 33, 385, 21);
        textStartTime.setText(startTime);
        
        Label lblStoptime = new Label(grpSimulationParameters, SWT.NONE);
        lblStoptime.setText("stopTime:");
        lblStoptime.setBounds(10, 60, 60, 21);
        
        textStopTime = new Text(grpSimulationParameters, SWT.BORDER);
        textStopTime.addModifyListener(new ModifyListener() {
        	public void modifyText(ModifyEvent e) {
        		stopTime = textStopTime.getText();
        	}
        });
        textStopTime.setText(stopTime);
        textStopTime.setBounds(102, 60, 385, 21);
        
        Label lblOutputInterval = new Label(grpSimulationParameters, SWT.NONE);
        lblOutputInterval.setText("output interval:");
        lblOutputInterval.setBounds(10, 87, 86, 21);
        
        textOutputInterval = new Text(grpSimulationParameters, SWT.BORDER);
        textOutputInterval.addModifyListener(new ModifyListener() {
        	public void modifyText(ModifyEvent e) {
        		outputInterval = textOutputInterval.getText();
        	}
        });
        textOutputInterval.setText(outputInterval);
        textOutputInterval.setBounds(102, 87, 385, 21);
        
        Label lblMethod = new Label(grpSimulationParameters, SWT.NONE);
        lblMethod.setText("method:");
        lblMethod.setBounds(10, 114, 60, 21);
        
        String[] itemsMethod = new String[] {"dassl", "dassl2", "euler", "rungekutta"};
        comboMethod = new Combo(grpSimulationParameters, SWT.NONE);
        comboMethod.addModifyListener(new ModifyListener() {
        	public void modifyText(ModifyEvent e) {
        		method = comboMethod.getText();
        	}
        });
        comboMethod.setItems(itemsMethod);
        comboMethod.setBounds(102, 114, 125, 23);
        comboMethod.select(getItemIndex(method, itemsMethod));

        Label label_1 = new Label(grpSimulationParameters, SWT.NONE);
        label_1.setText("tolerance:");
        label_1.setBounds(10, 141, 60, 21);
        
        textTolerance = new Text(grpSimulationParameters, SWT.BORDER);
        textTolerance.addModifyListener(new ModifyListener() {
        	public void modifyText(ModifyEvent e) {
        		tolerance = textTolerance.getText();
        	}
        });
        textTolerance.setText(tolerance);
        textTolerance.setBounds(102, 141, 385, 21);
        
        Label lblFormat = new Label(grpSimulationParameters, SWT.NONE);
        lblFormat.setText("format:");
        lblFormat.setBounds(10, 168, 60, 21);
        
        String[] itemsFormat = new String[] {"plt"};
        comboFormat = new Combo(grpSimulationParameters, SWT.NONE);
        comboFormat.addModifyListener(new ModifyListener() {
        	public void modifyText(ModifyEvent e) {
        		format = comboFormat.getText();
        	}
        });
        comboFormat.setItems(itemsFormat);
        comboFormat.setBounds(102, 166, 125, 23);
        comboFormat.select(getItemIndex(format, itemsFormat));

        Composite compositeOptions = new Composite(composite, SWT.NONE);
        compositeOptions.setBounds(10, 274, 497, 99);
        
        Group grpOptions = new Group(compositeOptions, SWT.NONE);
        grpOptions.setLocation(0, 0);
        grpOptions.setSize(497, 99);
        grpOptions.setText("Options");
        
        btnCopySimulationFiles = new Button(grpOptions, SWT.CHECK);
        btnCopySimulationFiles.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		isCopyFilesChecked = btnCopySimulationFiles.getSelection();
        	}
        });
        btnCopySimulationFiles.setToolTipText("If this option is activated then the simulation files such as _init.xml, .exe and the simulation result file will be copies into the 'sim-gen' folder inside the active project.");
        btnCopySimulationFiles.setBounds(10, 73, 477, 16);
        btnCopySimulationFiles.setText("Copy result files in my project 'sim-gen' folder after simulation");
        
        btnLoadModelicaLib = new Button(grpOptions, SWT.CHECK);
        btnLoadModelicaLib.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		isLoadModelicaLibChecked = btnLoadModelicaLib.getSelection();
        	}
        });
        btnLoadModelicaLib.setToolTipText("If this option is activated then the Modelica library will be loaded in advance.");
        btnLoadModelicaLib.setText("Load Modelica library");
        btnLoadModelicaLib.setBounds(10, 51, 477, 16);
        
        btnGenerateModelicaCode = new Button(grpOptions, SWT.CHECK);
        btnGenerateModelicaCode.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		isGenerateModelicaCodeChecked = btnGenerateModelicaCode.getSelection();
        	}
        });
        btnGenerateModelicaCode.setSelection(isGenerateModelicaCodeChecked);
        btnGenerateModelicaCode.setToolTipText("If this option is activated then Modelica code will be generated before simulation.");
        btnGenerateModelicaCode.setText("Generate Modelica code before simulating");
        btnGenerateModelicaCode.setBounds(10, 29, 477, 16);
        
        Label lblMessage = new Label(composite, SWT.NONE);
        lblMessage.setBounds(15, 388, 474, 15);
        lblMessage.setText("After simulation a new results window will open.");
        
        return parent;
	}
	
	
	
	// params
	public String getStartTime(){
		return startTime;
	}
	
	public String getStopTime(){
		return stopTime;
	}
	
	public String getOutputInterval(){
		return outputInterval;
	}
	
	public String getMethod(){
		return method;
	}
	
	public String getTolerance(){
		return tolerance;
	}
	
	
	public String getFormat(){
		return format;
	}
	
	
	
	// options
	public boolean isCopyFilesChecked(){
		return isCopyFilesChecked;
	}
	
	public boolean isLoadModelicaLibChecked(){
		return isLoadModelicaLibChecked;
	}
	
	public boolean isGenerateModelicaCodeChecked(){
		return isGenerateModelicaCodeChecked;
	}
	
	private int getItemIndex(String string, String[] items) {
		for (int i = 0; i < items.length; i++) {
			String item = items[i];
			if (item.trim().equals(string)) {
				return i;
			}
			
		}
		return 0;
	}
}

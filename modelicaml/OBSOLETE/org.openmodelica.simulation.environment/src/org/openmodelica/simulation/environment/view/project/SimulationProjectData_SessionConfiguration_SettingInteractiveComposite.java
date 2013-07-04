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
package org.openmodelica.simulation.environment.view.project;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

// TODO: Auto-generated Javadoc
/**
 * The Class SimulationProjectData_SessionConfiguration_SettingInteractiveComposite.
 *
 * @author
 */
public class SimulationProjectData_SessionConfiguration_SettingInteractiveComposite extends org.eclipse.swt.widgets.Composite {
	
	/** The label cc. */
	private Label labelCC;
	
	/** The label cc port. */
	private Label labelCCPort;
	
	/** The text ccip. */
	public Text textCCIP;
	
	/** The label ccip. */
	private Label labelCCIP;
	
	/** The label cs port check. */
	public Label labelCSPortCheck;
	
	/** The text cs port. */
	public Text textCSPort;
	
	/** The label cs port. */
	private Label labelCSPort;
	
	/** The text csip. */
	public Text textCSIP;
	
	/** The label csip. */
	private Label labelCSIP;
	
	/** The label control server. */
	private Label labelControlServer;
	
	/** The label network setting. */
	private Label labelNetworkSetting;
	
	/** The label cc port check. */
	public Label labelCCPortCheck;
	
	/** The composite network settings. */
	private Composite compositeNetworkSettings;
	
	/** The label used solver. */
	public Label labelUsedSolver;
	
	/** The label solver. */
	private Label labelSolver;
	
	/** The label fill empty001. */
	private Label labelFillEmpty001;
	
	/** The text tolerance. */
	public Text textTolerance;
	
	/** The label calc tolerance. */
	private Label labelCalcTolerance;
	
	/** The text interval unit. */
	public Text textIntervalUnit;
	
	/** The composite simulation settings. */
	private Composite compositeSimulationSettings;
	
	/** The composite3. */
	private Composite composite3;
	
	/** The composite2. */
	private Composite composite2;
	
	/** The composite1. */
	private Composite composite1;
	
	/** The text intervalls. */
	public Text textIntervalls;
	
	/** The label interval. */
	private Label labelInterval;
	
	/** The label ts port check. */
	public Label labelTSPortCheck;
	
	/** The text ts port. */
	public Text textTSPort;
	
	/** The label ts port. */
	private Label labelTSPort;
	
	/** The text tsip. */
	public Text textTSIP;
	
	/** The label tsip. */
	private Label labelTSIP;
	
	/** The label transfer server. */
	private Label labelTransferServer;
	
	/** The text cc port. */
	public Text textCCPort;
	
	/** The composite setting data. */
	private Composite compositeSettingData;

	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	
	/**
	* Overriding checkSubclass allows this class to extend org.eclipse.swt.widgets.Composite
	*/	
	protected void checkSubclass() {
	}
	
	/**
	 * Auto-generated method to display this
	 * org.eclipse.swt.widgets.Composite inside a new Shell.
	 *
	 * @param parent the parent
	 * @param style the style
	 */

	public SimulationProjectData_SessionConfiguration_SettingInteractiveComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			FormLayout thisLayout = new FormLayout();
			this.setLayout(thisLayout);
			{
				compositeSettingData = new Composite(this, SWT.NONE);
				GridLayout compositeSettingDataLayout = new GridLayout();
				compositeSettingDataLayout.makeColumnsEqualWidth = true;
				compositeSettingData.setLayout(compositeSettingDataLayout);
				{
					GridData composite5LData = new GridData();
					composite5LData.horizontalAlignment = GridData.FILL;
					composite5LData.verticalAlignment = GridData.FILL;
					composite5LData.grabExcessHorizontalSpace = true;
					compositeNetworkSettings = new Composite(compositeSettingData, SWT.BORDER);
					GridLayout composite5Layout = new GridLayout();
					composite5Layout.makeColumnsEqualWidth = true;
					compositeNetworkSettings.setLayout(composite5Layout);
					compositeNetworkSettings.setLayoutData(composite5LData);
					{
						labelNetworkSetting = new Label(compositeNetworkSettings, SWT.NONE);
						GridData labelNetworkSettingLData = new GridData();
						labelNetworkSetting.setLayoutData(labelNetworkSettingLData);
						labelNetworkSetting.setText("Network settings:");
					}
					{
						labelControlServer = new Label(compositeNetworkSettings, SWT.NONE);
						GridData labelControlServerLData = new GridData();
						labelControlServer.setLayoutData(labelControlServerLData);
						labelControlServer.setText("Simulation Control Server:");
					}
					{
						GridData composite1LData = new GridData();
						composite1 = new Composite(compositeNetworkSettings, SWT.NONE);
						GridLayout composite1Layout = new GridLayout();
						composite1Layout.makeColumnsEqualWidth = true;
						composite1Layout.numColumns = 5;
						composite1.setLayout(composite1Layout);
						composite1.setLayoutData(composite1LData);
						{
							labelCSIP = new Label(composite1, SWT.NONE);
							GridData labelCSIPLData = new GridData();
							labelCSIP.setLayoutData(labelCSIPLData);
							labelCSIP.setText("IP:");
						}
						{
							textCSIP = new Text(composite1, SWT.NONE);
							GridData textCSIPLData = new GridData();
							textCSIPLData.horizontalAlignment = GridData.FILL;
							textCSIP.setLayoutData(textCSIPLData);
							textCSIP.setText("");
						}
						{
							labelCSPort = new Label(composite1, SWT.NONE);
							labelCSPort.setText("Port:");
						}
						{
							textCSPort = new Text(composite1, SWT.NONE);
							GridData textCSPortLData = new GridData();
							textCSPortLData.horizontalAlignment = GridData.FILL;
							textCSPort.setLayoutData(textCSPortLData);
							textCSPort.setText("");
						}
						{
							labelCSPortCheck = new Label(composite1, SWT.NONE);
							GridData labelPortCheckLData = new GridData();
							labelPortCheckLData.horizontalAlignment = GridData.FILL;
							labelCSPortCheck.setLayoutData(labelPortCheckLData);
							labelCSPortCheck.setText("");
						}
					}
					{
						labelCC = new Label(compositeNetworkSettings, SWT.NONE);
						GridData labelCCLData = new GridData();
						labelCC.setLayoutData(labelCCLData);
						labelCC.setText("Runtime Control Server:");
					}
					{
						GridData composite2LData = new GridData();
						composite2 = new Composite(compositeNetworkSettings, SWT.NONE);
						GridLayout composite2Layout = new GridLayout();
						composite2Layout.makeColumnsEqualWidth = true;
						composite2Layout.numColumns = 5;
						composite2.setLayout(composite2Layout);
						composite2.setLayoutData(composite2LData);
						{
							labelCCIP = new Label(composite2, SWT.NONE);
							GridData labelCCIPLData = new GridData();
							labelCCIP.setLayoutData(labelCCIPLData);
							labelCCIP.setText("IP:");
						}
						{
							textCCIP = new Text(composite2, SWT.NONE);
							GridData textCCIPLData = new GridData();
							textCCIPLData.horizontalAlignment = GridData.FILL;
							textCCIP.setLayoutData(textCCIPLData);
							textCCIP.setText("");
						}
						{
							labelCCPort = new Label(composite2, SWT.NONE);
							GridData labelCCPortLData = new GridData();
							labelCCPort.setLayoutData(labelCCPortLData);
							labelCCPort.setText("Port:");
						}
						{
							textCCPort = new Text(composite2, SWT.NONE);
							GridData textCCPortLData = new GridData();
							textCCPort.setLayoutData(textCCPortLData);
							textCCPort.setText("");
						}
						{
							labelCCPortCheck = new Label(composite2, SWT.NONE);
							GridData labelCCPortCheckLData = new GridData();
							labelCCPortCheckLData.horizontalAlignment = GridData.FILL;
							labelCCPortCheck.setLayoutData(labelCCPortCheckLData);
							labelCCPortCheck.setText("");
						}
					}
					{
						labelTransferServer = new Label(compositeNetworkSettings, SWT.NONE);
						labelTransferServer.setText("Simulation Transfer Server:");
					}
					{
						GridData composite3LData = new GridData();
						composite3 = new Composite(compositeNetworkSettings, SWT.NONE);
						GridLayout composite3Layout = new GridLayout();
						composite3Layout.makeColumnsEqualWidth = true;
						composite3Layout.numColumns = 5;
						composite3.setLayout(composite3Layout);
						composite3.setLayoutData(composite3LData);
						{
							labelTSIP = new Label(composite3, SWT.NONE);
							GridData labelTSIPLData = new GridData();
							labelTSIP.setLayoutData(labelTSIPLData);
							labelTSIP.setText("IP:");
						}
						{
							textTSIP = new Text(composite3, SWT.NONE);
							GridData textTSIPLData = new GridData();
							textTSIPLData.horizontalAlignment = GridData.FILL;
							textTSIP.setLayoutData(textTSIPLData);
							textTSIP.setText("");
						}
						{
							labelTSPort = new Label(composite3, SWT.NONE);
							GridData labelTSPortLData = new GridData();
							labelTSPort.setLayoutData(labelTSPortLData);
							labelTSPort.setText("Port:");
						}
						{
							textTSPort = new Text(composite3, SWT.NONE);
							GridData textTSPortLData = new GridData();
							textTSPort.setLayoutData(textTSPortLData);
							textTSPort.setText("");
						}
						{
							labelTSPortCheck = new Label(composite3, SWT.NONE);
							GridData labelTSPortCheckLData = new GridData();
							labelTSPortCheckLData.horizontalAlignment = GridData.FILL;
							labelTSPortCheck.setLayoutData(labelTSPortCheckLData);
							labelTSPortCheck.setText("");
						}
					}
				}
				{
					GridData composite4LData = new GridData();
					composite4LData.verticalAlignment = GridData.FILL;
					composite4LData.horizontalAlignment = GridData.FILL;
					composite4LData.grabExcessHorizontalSpace = true;
					compositeSimulationSettings = new Composite(compositeSettingData, SWT.BORDER);
					GridLayout composite4Layout = new GridLayout();
					composite4Layout.makeColumnsEqualWidth = true;
					composite4Layout.numColumns = 3;
					compositeSimulationSettings.setLayout(composite4Layout);
					compositeSimulationSettings.setLayoutData(composite4LData);
					{
						labelInterval = new Label(compositeSimulationSettings, SWT.NONE);
						GridData labelIntervalLData = new GridData();
						labelInterval.setLayoutData(labelIntervalLData);
						labelInterval.setText("Interval:");
					}
					{
						textIntervalls = new Text(compositeSimulationSettings, SWT.NONE);
						GridData textIntervallsLData = new GridData();
						textIntervallsLData.horizontalAlignment = GridData.FILL;
						textIntervalls.setLayoutData(textIntervallsLData);
						textIntervalls.setText("");
					}
					{
						textIntervalUnit = new Text(compositeSimulationSettings, SWT.NONE);
						GridData textIntervalUnitLData = new GridData();
						textIntervalUnitLData.horizontalAlignment = GridData.FILL;
						textIntervalUnit.setLayoutData(textIntervalUnitLData);
						textIntervalUnit.setText("");
					}
					{
						labelCalcTolerance = new Label(compositeSimulationSettings, SWT.NONE);
						GridData labelCalcToleranceLData = new GridData();
						labelCalcTolerance.setLayoutData(labelCalcToleranceLData);
						labelCalcTolerance.setText("Tolerance:");
					}
					{
						textTolerance = new Text(compositeSimulationSettings, SWT.NONE);
						GridData textToleranceLData = new GridData();
						textToleranceLData.horizontalAlignment = GridData.FILL;
						textTolerance.setLayoutData(textToleranceLData);
						textTolerance.setText("");
					}
					{
						GridData labelFillEmpty001LData = new GridData();
						labelFillEmpty001 = new Label(compositeSimulationSettings, SWT.NONE);
						labelFillEmpty001.setLayoutData(labelFillEmpty001LData);
					}
					{
						labelSolver = new Label(compositeSimulationSettings, SWT.NONE);
						GridData labelSolverLData = new GridData();
						labelSolver.setLayoutData(labelSolverLData);
						labelSolver.setText("Solver:");
					}
					{
						labelUsedSolver = new Label(compositeSimulationSettings, SWT.NONE);
						GridData labelUsedSolverLData = new GridData();
						labelUsedSolverLData.horizontalAlignment = GridData.FILL;
						labelUsedSolver.setLayoutData(labelUsedSolverLData);
						labelUsedSolver.setText("");
					}
				}
			
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

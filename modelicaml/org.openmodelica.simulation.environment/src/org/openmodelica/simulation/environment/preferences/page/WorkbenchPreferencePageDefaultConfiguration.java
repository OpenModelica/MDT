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
package org.openmodelica.simulation.environment.preferences.page;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * OpenModelica Interactive preference page.
 *
 * @author Parham
 */
public class WorkbenchPreferencePageDefaultConfiguration extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	/** The Constant AUTHOR. */
	public final static String AUTHOR = "AUTHOR";
	
	/** The Constant COMPANY. */
	public final static String COMPANY = "COMPANY";
	
	/** The Constant SimulationControlServerPort. */
	public final static String SimulationControlServerPort = "SCSPort";
	
	/** The Constant RuntimeControlServerPort. */
	public final static String RuntimeControlServerPort = "RCSPort";
	
	/** The Constant SimulationTransferServerPort. */
	public final static String SimulationTransferServerPort = "STSPort";
	
	/** The Constant START. */
	public final static String START = "START";
	
	/** The Constant STOP. */
	public final static String STOP = "STOP";
	
	/** The Constant OUTPUT. */
	public final static String OUTPUT = "OUTPUT";
	
	/** The Constant TOLERANCE. */
	public final static String TOLERANCE = "TOLERANCE";
	
	/** The Constant SOLVER. */
	public final static String SOLVER = "SOLVER";
	
	/** The Constant DASSL. */
	public final static String DASSL = "dassl";
	
	/** The Constant EULER. */
	public final static String EULER = "euler";
	
	/** The Constant DASSL2. */
	public final static String DASSL2 = "dassl2";
	
	/** The Constant RUNGEKUTTA. */
	public final static String RUNGEKUTTA = "rungekutta";
	
	/** The simulation control server port integer field editor. */
	IntegerFieldEditor simulationControlServerPortIntegerFieldEditor;
	
	/** The runtime control server port integer field editor. */
	IntegerFieldEditor runtimeControlServerPortIntegerFieldEditor;
	
	/** The simulation transfer server port integer field editor. */
	IntegerFieldEditor simulationTransferServerPortIntegerFieldEditor;
	
	/**
	 * Instantiates a new workbench preference page default configuration.
	 */
	public WorkbenchPreferencePageDefaultConfiguration() {
		super(GRID);
	}

//	public WorkbenchPreferencePageDefaultConfiguration(String title) {
//		super(title);
//		// TODO Auto-generated constructor stub
//	}
//
//	public WorkbenchPreferencePageDefaultConfiguration(String title,
//			ImageDescriptor image) {
//		super(title, image);
//		// TODO Auto-generated constructor stub
//	}

	/* (non-Javadoc)
 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
 */
@Override
	public void createFieldEditors() {
//		addField(new DirectoryFieldEditor("WORKSPACEPATH", "&Directory preference:",
//				getFieldEditorParent()));

		addField(new StringFieldEditor(AUTHOR, "Author:",
				getFieldEditorParent()));
		addField(new StringFieldEditor(COMPANY, "Company:",
				getFieldEditorParent()));

		simulationControlServerPortIntegerFieldEditor = new IntegerFieldEditor(SimulationControlServerPort, "Simulation Control Server Port:",
				getFieldEditorParent(), 5);
		addField(simulationControlServerPortIntegerFieldEditor);
		
		runtimeControlServerPortIntegerFieldEditor = new IntegerFieldEditor(RuntimeControlServerPort, "Runtime Control Server Port:",
				getFieldEditorParent(), 5);
		addField(runtimeControlServerPortIntegerFieldEditor);
		
		simulationTransferServerPortIntegerFieldEditor = new IntegerFieldEditor(SimulationTransferServerPort, "Simulation Transfer Server Port:",
				getFieldEditorParent(), 5);
		addField(simulationTransferServerPortIntegerFieldEditor);
		
		addField(new StringFieldEditor(START, "Start:",
				getFieldEditorParent()));
		
		addField(new StringFieldEditor(STOP, "Stop:",
				getFieldEditorParent()));
		
		addField(new StringFieldEditor(OUTPUT, "Output Interval(s):",
				getFieldEditorParent()));
		
		addField(new StringFieldEditor(TOLERANCE, "Tolerance:",
				getFieldEditorParent()));
		
		addField(new ComboFieldEditor(SOLVER, "Solver:", new String[][] { { DASSL2, "dassl2" }, { DASSL, "dassl" }, { RUNGEKUTTA, "rungekutta" },
				{ EULER, "euler" } }, getFieldEditorParent()));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Set default simulation setup for interactive and non-interactive simulation.");
		
	}

//	@Override
//	protected Control createContents(Composite parent) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}

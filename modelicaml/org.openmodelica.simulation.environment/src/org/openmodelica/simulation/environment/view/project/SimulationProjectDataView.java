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

import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.part.ViewPart;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * Contains all composites to view all project data (Information, Initial Values, Configurations...)
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class SimulationProjectDataView extends ViewPart{
	
	/** The tab folder project view. */
	private TabFolder tabFolderProjectView;

	/**
	 * Displays all project relevant data e.g. name, version...
	 */
	private TabItem tabItemProjectData;
	/**
	 * Displays all initial values which are read from the initial file (e.g. init.txt)
	 */
	private TabItem tabItemInitialValues;
	
	/** Displays all simulation setting & model configuration files from the SimulationSessionConfig folder. */
	private TabItem tabItemSimulationSessionConfiguration;
	
	/**
	 * Instantiates a new simulation project data view.
	 */
	public SimulationProjectDataView() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		{
			tabFolderProjectView = new TabFolder(parent, SWT.NONE);
			tabFolderProjectView.setData(null);
			{
				tabItemProjectData = new TabItem(tabFolderProjectView, SWT.NONE);
				tabItemProjectData.setText("Data");
				tabItemProjectData.setControl(getTabOneControl(tabFolderProjectView));
				tabItemProjectData.setToolTipText("Select this tab to display all project relevant data of a selected simulation project.");
			}
			{
				tabItemInitialValues = new TabItem(tabFolderProjectView, SWT.NONE);
				tabItemInitialValues.setText("Initial Values");
				tabItemInitialValues.setControl(getTabTwoControl(tabFolderProjectView));
				tabItemInitialValues.setToolTipText("Select this tab to display all initial values of this model. The initial values can not be modified.");
			}
			{
				tabItemSimulationSessionConfiguration = new TabItem(tabFolderProjectView, SWT.NONE);
				tabItemSimulationSessionConfiguration.setText("SessionConfiguration");
				tabItemSimulationSessionConfiguration.setControl(getTabThreeControl(tabFolderProjectView));
				tabItemSimulationSessionConfiguration.setToolTipText("Select this tab to display all available simulation setting & model configuration files\nfrom the SimulationSessionConfig folder and to manage those files.\nYou can add, delete or manupulate a session config file.");
			}
			
			tabFolderProjectView.setSelection(0);
		}
		// TODO Auto-generated method stub

	}

	 /**
 	 * Gets the control for tab one.
 	 *
 	 * @param tabFolder the parent tab folder
 	 * @return Control
 	 */
	  private Control getTabOneControl(TabFolder tabFolder) {
	    // Create a composite and add four buttons to it
	    Composite composite = new SimulationProjectData_ProjectDescriptionComposite(tabFolder, this, SWT.NONE);
				Activator.getSimulationProjectCenter().addDataViewComposite((Observer)composite);
	    return composite;
	  }
	  
	  /**
  	 * Gets the control for tab two.
  	 *
  	 * @param tabFolder the parent tab folder
  	 * @return Control
  	 */
	  private Control getTabTwoControl(TabFolder tabFolder) {
	    // Create a composite and add four buttons to it
	    Composite composite = new SimulationProjectData_ModelInitialValuesComposite(tabFolder, SWT.NONE);
				Activator.getSimulationProjectCenter().addDataViewComposite((Observer)composite);
	    return composite;
	  }
	  
	  /**
  	 * Gets the control for tab two.
  	 *
  	 * @param tabFolder the parent tab folder
  	 * @return Control
  	 */
	  private Control getTabThreeControl(TabFolder tabFolder) {
	    // Create a composite and add four buttons to it
	    Composite composite = new SimulationProjectData_SessionConfigurationComposite(tabFolder, SWT.NONE);
				Activator.getSimulationProjectCenter().addDataViewComposite((Observer)composite);
	    return composite;
	  }
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Selects the Data View composite to be the first tab shown after selecting a new project
	 * from the listing view.
	 */
	public synchronized void setSelectionToDataView(){
		tabFolderProjectView.setSelection(tabItemSimulationSessionConfiguration); // Set to default tab (Project Data Tab)
	}
}

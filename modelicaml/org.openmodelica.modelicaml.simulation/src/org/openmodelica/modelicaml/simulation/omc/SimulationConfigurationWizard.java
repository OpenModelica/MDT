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
package org.openmodelica.modelicaml.simulation.omc;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.uml2.uml.Element;



// TODO: Auto-generated Javadoc
/**
 * The Class SimulationConfigurationWizard.
 */
public class SimulationConfigurationWizard extends Wizard{

	/** The one. */
	private SimulationConfigurationWizardPage one;
	
	/**
	 * Instantiates a new simulation configuration wizard.
	 * 
	 * @param modelElement
	 *            the model element
	 */
	public SimulationConfigurationWizard(Element modelElement) {
		super();
		setWindowTitle("ModelicaML Simulation using OpenModelica Compiler (OMC)");
		addPages(modelElement);
		setNeedsProgressMonitor(true);
	}


	/**
	 * Adds the pages.
	 * 
	 * @param modelElement
	 *            the model element
	 */
	public void addPages(Element modelElement) {
		one = new SimulationConfigurationWizardPage("Simulation Configuration: Simulatoins Parameters and Plot Settings", modelElement);
		addPage(one);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		
		OMCSimulationDataStorage.startTime = one.textStartTime.getText();
		OMCSimulationDataStorage.stopTime = one.textStopTime.getText();
		OMCSimulationDataStorage.tolerance = one.textTolerance.getText();
		OMCSimulationDataStorage.selectedVarPathList = one.plotVarPathList;
		OMCSimulationDataStorage.Solver = "\"" + one.comboSolver.getText() +"\"";
		
//		try {
//			new ProgressMonitorDialog(getContainer().getShell()).run(true, true,
//					new IRunnableWithProgress() {
//						@Override
//						public void run(IProgressMonitor monitor)
//								throws InvocationTargetException,
//								InterruptedException {
//							//monitor.beginTask("Create a new Simulation Session Configuration...",3);
//							
////							for (int total = 1; total < 2; total++) {
////								if (monitor.isCanceled())
////									throw new OperationCanceledException();
////								Thread.sleep(1000l); //TODO [20100506] CAN BE DELETED
//								
//								
////								switch (total) {
////								case 1:
////									monitor.setTaskName("Step: " + total + ", Preparing Filesystem...");
////									getShell().getDisplay().asyncExec(new Runnable() {
////										public void run() {
////											String pathToSessionConfigFolder = createConfigFolder();
////											try {
////												createSettingFile(pathToSessionConfigFolder);
////												createConfiguredModel(pathToSessionConfigFolder);
////											} catch (Exception e) {
////												// TODO Auto-generated catch block
////												e.printStackTrace();
////											}
////										}
////									});
////									monitor.worked(1);
////									break;
////								default:
////									break;
////								}	
////							}
//							monitor.done();
//						}
//					});
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			System.out.println("Abbruch1");
//		} catch (OperationCanceledException e){
//			System.out.println("Abbruch2");
//		}
		return true;
	}

}

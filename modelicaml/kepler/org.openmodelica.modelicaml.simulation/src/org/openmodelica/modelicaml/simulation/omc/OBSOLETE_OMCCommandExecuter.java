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
package org.openmodelica.modelicaml.simulation.omc;

import java.util.List;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.omc.OMCProxy;
import org.openmodelica.modelicaml.simulation.Activator;

// TODO: Auto-generated Javadoc
/**
 * The Class OMCCommandExecuter.
 */
public class OBSOLETE_OMCCommandExecuter {
	
	/** The status. */
	private ICompilerResult status;
	
	/** The proxy. */
	private OMCProxy proxy = new OMCProxy();
	
	/** The simulation parameters. */
	private String simulationParameters = "";
	
	/** The dir. */
//	private String dir;
	
	/** The error string. */
	private String errorString = "";
	
	/** The elt. */
	private Element elt;
	
	private List<String> filesToLoad;
//	private String modelFilePath;
	private String modelElementQualifiedName;
	private String plotCommand;
	private String simPar;
	
	/**
	 * Instantiates a new oMC command executer.
	 * 
	 * @param elt
	 *            the elt
	 * @param folderPath
	 *            the folder path
	 * @param modelFilePath
	 *            the model file path
	 * @param modelElementQualifiedName
	 *            the model element qualified name
	 * @param plotCommand
	 *            the plot command
	 * @param simPar
	 *            the sim par
	 * @throws org.modelica.mdt.core.compiler.ConnectException 
	 */
	public OBSOLETE_OMCCommandExecuter(Element elt, 
								List<String> filesToLoad, 
								String modelElementQualifiedName, 
								String plotCommand, 
								String simPar) throws org.modelica.mdt.core.compiler.ConnectException {
		
		this.filesToLoad = filesToLoad;
		this.modelElementQualifiedName = modelElementQualifiedName;
		this.plotCommand = plotCommand;
		this.simPar = simPar;
		
		this.elt = elt;

		Activator.setOmcProxy(proxy);
		
		loadAndSimulate();
		
	}

	/**
	 * Execute omc command.
	 * 
	 * @param folderPath
	 *            the folder path
	 * @param modelFilePath
	 *            the model file path
	 * @param modelElementQualifiedName
	 *            the model element qualified name
	 * @param plotCommand
	 *            the plot command
	 * @param simPar
	 *            the sim par
	 * @return the string
	 * @throws org.modelica.mdt.core.compiler.ConnectException 
	 */
	private ICompilerResult loadAndSimulate() throws org.modelica.mdt.core.compiler.ConnectException {
		
		if (simPar != null) {
			simulationParameters = "," + simPar;
		}
		
		status = proxy.sendExpression("clear()", true);
		
		//proxy.sendExpression("cd(\"" + dir + "\")");
		
		if (status == null) {
			System.err.println("No connection to OMC! ");
			OBSOLETE_MoldelicaMLSimulationMarkterCreator.modelicaMLSimulationAlert(elt, "error", "No connection to OMC! ");
		}
		
		for (String path : filesToLoad) {
			status = proxy.sendExpression("loadFile(\"" + path + "\")", true);
			
//				if (status.contains("error") || status.contains("false")) {
//					System.err.println("Cannot find the package " + folderPath + "/package.mo"+ "!");
//					MoldelicaMLSimulationMarkterCreator.modelicaMLSimulationAlert(elt, "error", "Cannot find the package " + folderPath + "/package.mo"+ "!");
//				}
		}
		
//			errorString = proxy.sendExpression("getErrorString()");
//			if (!errorString.equals("")) {
//				System.err.println(errorString);
//			}
//			
//			status = proxy.sendExpression("checkModel("
//					+ modelElementQualifiedName + ")");
//			
//			errorString = proxy.sendExpression("getErrorString()");
//			if (!errorString.equals("")) {
//				System.err.println(errorString);
//			}
		
		status = proxy.sendExpression("simulate("
				+ modelElementQualifiedName + simulationParameters + ")", true);
		
		if (status.getResult().toString().contains("error") || status.getResult().toString().contains("failed")) {
			setErrorString(status.getResult().toString());
			OBSOLETE_MoldelicaMLSimulationMarkterCreator.modelicaMLSimulationAlert(elt, "error", "Simulation of the class '"+ ((NamedElement)elt).getName() +"' failed." + "\n" + status);
//				DialogMessage dialog = new DialogMessage(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Errors", "Errors occured during simulation", status);
//				dialog.open();
		}
		else  {
//				MoldelicaMLSimulationMarkterCreator.modelicaMLSimulationAlert(elt, "info", "Simulation of the class '"+ ((NamedElement)elt).getName() +"' was successful.");
		}
		
		if (plotCommand != null) {
			status = proxy.sendExpression(plotCommand, true);
//				if (status.contains("No simulation result to plot")) {
//					MoldelicaMLSimulationMarkterCreator.modelicaMLSimulationAlert(elt, "error", "No simulation result to plot.");
//				}
//				else {
//					MoldelicaMLSimulationMarkterCreator.modelicaMLSimulationAlert(elt, "info", "Simulation results are plotted in a separate window.");
//				}
		}
		else {
//				MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Nothing to plot", "No variables were selected to be plotted after simulation.");
		}
		return status;
	}

	public String getErrorString() {
		return errorString;
	}

	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}

}

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
package org.openmodelica.modelicaml.simulation.testexecution.actions;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.openmodelica.modelicaml.simulation.dialogs.DialogPlot;

public class PlotResultsAction implements IWorkbenchWindowActionDelegate {

	protected String filePath = null;
//	private DialogPlot dialog;
	boolean canceled = false;
	private HashSet<String> preSelectedVariablesToPlot;
	

	class LongRunningOperation implements IRunnableWithProgress {

		public LongRunningOperation() {
		}

		public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

			monitor.beginTask("Reading simulation results",IProgressMonitor.UNKNOWN);
//			dialog.setResults(SimulationResult_XML_reader.getAllResultsAsStringFromXML(xmlFilePath));
			monitor.done();

			if (monitor.isCanceled()) {
				canceled = true;
			}
		}
	}
	
	/*
	 *  To be used by sub classes to set the file path based on user selection
	 */
	protected void setSelectedFilePath(){
	}
	
	
	@Override
	public void run(IAction action) {
		
		setSelectedFilePath();
		
		if (this.filePath != null) {
			filePath = filePath.replaceFirst("file://", "");
			if (fileExists(this.filePath)) {
				
				DialogPlot dialog = new DialogPlot(new Shell(), filePath, getPreSelectedVariablesToPlot());
//				dialog.open();
				
				try {
					// reset canceled
					canceled = false;
					
					// read results
					new ProgressMonitorDialog(new Shell()).run(true, true, new LongRunningOperation());
					
					//open dialog
					if ( !canceled) {
						dialog.open();
					}

		        } catch (InvocationTargetException e) {
		          MessageDialog.openError(new Shell(), "Error", e.getMessage());
		        } catch (InterruptedException e) {
		          MessageDialog.openInformation(new Shell(), "Cancelled", e.getMessage());
		        }
			}
			else {
				openError();
			}
		}
		else {
			openError();
		}
	}

	
	protected void openError(){
		MessageDialog.openError(new Shell(), "Error opening the simulation results ...", "The file does not exists: \n" + this.filePath);
	}
	
	
	protected boolean fileExists(String pathAbsolute){
		File file = new File(pathAbsolute);
		return file.isFile();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow window) {
	}

	public void setFilePath(String path){
		this.filePath = path;
	}


	public HashSet<String> getPreSelectedVariablesToPlot() {
		return preSelectedVariablesToPlot;
	}


	public void setPreSelectedVariablesToPlot(
			HashSet<String> preSelectedVariablesToPlot) {
		this.preSelectedVariablesToPlot = preSelectedVariablesToPlot;
	}

}

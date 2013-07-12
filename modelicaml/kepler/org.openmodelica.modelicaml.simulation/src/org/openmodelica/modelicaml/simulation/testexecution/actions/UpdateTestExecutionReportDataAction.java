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

import java.net.URI;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.openmodelica.modelicaml.simulation.evaluation.ParseJavaScript;

public class UpdateTestExecutionReportDataAction implements
		IWorkbenchWindowActionDelegate {

	private String path = null;
	private String reportHTMLFile;
	
	public void setPath(String path){
		this.path = path;
	}
	
	@Override
	public void run(IAction action) {
		if (this.path == null) {
			ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
			if(selection instanceof IStructuredSelection) {
				Object firstElement = ((IStructuredSelection)selection).getFirstElement();
				if (firstElement instanceof IFile) {
					String selectedFile = ((IFile)firstElement).getLocationURI().toString().replaceFirst("file:\\/", "");
					
					
					if (selectedFile.contains("report")) {
						reportHTMLFile = selectedFile;
						
						IFileSystem fileSystem = EFS.getLocalFileSystem();
						IFileStore xmlFile = fileSystem.getStore(URI.create(selectedFile));
						// get the parent folder (i.e. report-gen)
						IFileStore parent = xmlFile.getParent();
						if (parent != null ) {
							// get the verification_session_xxx folder
							IFileStore parent2 = parent.getParent();
							if (parent2 != null) {
								this.path = parent2.toURI().getRawPath().substring(1,parent2.toURI().getRawPath().length()) + "/";
							}
						}
					}
				}
			}
		}
		
		if (this.path != null) {
			// Find the report_data.js_template in the folder "/includes/", 
			// replace all place holders and save(overwrite) the report_data.js 
			try {
				ParseJavaScript.parseJSTemp_generateJSFile(this.path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// open report afterwards
			if (reportHTMLFile != null) {
				OpenTestExecutionReportAction openReport = new OpenTestExecutionReportAction();
				openReport.setReportFilePath(reportHTMLFile);
				openReport.run(null);
			}
			else {
				MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Update of the Verification Report Failed", "Could not find the report.html file.");
			}
		}
		else {
			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error Occured", "Could not access the verification sessioin report files.");
		}
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
	
}

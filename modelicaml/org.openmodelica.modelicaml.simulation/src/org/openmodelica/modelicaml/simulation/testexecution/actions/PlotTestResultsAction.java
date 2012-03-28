package org.openmodelica.modelicaml.simulation.testexecution.actions;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.openmodelica.modelicaml.simulation.Activator;
import org.openmodelica.modelicaml.simulation.testexecution.dialogs.DialogPlot;
import org.openmodelica.modelicaml.simulation.xml.SimulationResult_XML_reader;

public class PlotTestResultsAction implements
		IWorkbenchWindowActionDelegate {

	private String xmlFilePath = null;
	private boolean canceled = false;
	
	@Override
	public void run(IAction action) {
		if (this.xmlFilePath == null) {
			ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
			if(selection instanceof IStructuredSelection) {
				Object firstElement = ((IStructuredSelection)selection).getFirstElement();
				if (firstElement instanceof IFile) {
					this.xmlFilePath = ((IFile)firstElement).getLocationURI().toString().replaceFirst("file:\\/", "");
				}
			}
		}
		
		if (this.xmlFilePath != null) {
			xmlFilePath = xmlFilePath.replaceFirst("file://", "");
			if (fileExists(this.xmlFilePath)) {
				
				IWorkbench wb = PlatformUI.getWorkbench();
				IProgressService ps = wb.getProgressService();
				try {
					ps.busyCursorWhile(new IRunnableWithProgress() {
				      public void run(IProgressMonitor pm) {
				    	  try
				    		{
//					    	  pm.setTaskName("Reading simulation results file ...");
					    	  pm.beginTask("Reading simulation results file ...", 2);
				    		
					    	  Map<String, Map<String, String>> results = SimulationResult_XML_reader.getAllResultsAsStringFromXML(xmlFilePath);
					    	  pm.worked(1);
					    	  
					    	  if (!pm.isCanceled()) {
						    	  canceled = false;
//						    	  pm.beginTask("Populating simulation results ...", 1);
						    	  pm.setTaskName("Reading simulation results file ...");
						    	  Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().setResults(results);
						    	  pm.worked(1);
					    	  }
					    	  else {
					    		  canceled = true;
					    		  pm.done();
					    	  }
				    		}
				    	finally
				    		{
				    		pm.done();
				    		}
				    	  
				      }
					});
		    	  
					if (!canceled) {
						String simulationModelName = getSimulationModelName(xmlFilePath);
				    	Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().setSimulationModelName(simulationModelName);
							
				    	// Plot dialog
				    	DialogPlot dialog = new DialogPlot(new Shell(), getTitle(xmlFilePath));
				    	dialog.open();
					}
			    	  
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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

	
	private void openError(){
		MessageDialog.openError(new Shell(), "Error opening the simulation results ...", "The file does not exists: \n" + this.xmlFilePath);
	}
	
	
	private boolean fileExists(String pathAbsolute){
//		IFileSystem fileSystem = EFS.getLocalFileSystem();
//		IFileStore file = fileSystem.getStore(URI.create(pathAbsolute));
//		
//		IFileInfo reportFileInfo = file.fetchInfo();
//		if (reportFileInfo.exists()) {
//			return true;
//		}
		File file = new File(pathAbsolute);
		return file.isFile();
	}
	
	private String getTitle(String filePath){
		String title = "";
		File file = new File(filePath);
		String parentFolderPath = file.getParent();
		File sessionFolder = new File(parentFolderPath);
		
		if (sessionFolder.isDirectory()) {
			title = title + sessionFolder.getName() + "/" + file.getName();
			return title;
		}
		return filePath;
	}
	
	private String getSimulationModelName(String filePath){
		String name = "";
		if (filePath != null && filePath.length() != 0) {
			String[] splitted = filePath.split("/");
			name = splitted[splitted.length - 1];
			name = name.replaceFirst("_res.xml", "");

			String[] dotSplitted = name.split("\\.");
			name = dotSplitted[dotSplitted.length - 1];
			
		}
		return name;
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


	public void setXMLFilePath(String path){
		this.xmlFilePath = path;
	}
}

package org.openmodelica.modelicaml.simulation.testexecution.actions;

import java.io.File;
import java.net.URI;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.openmodelica.modelicaml.simulation.Activator;
import org.openmodelica.modelicaml.simulation.testexecution.dialogs.DialogPlot;
import org.openmodelica.modelicaml.simulation.xml.SimulationResult_XML_reader;

public class PlotTestResultsAction implements
		IWorkbenchWindowActionDelegate {

	private String xmlFilePath = null;
	
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
				Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().setResults(SimulationResult_XML_reader.getAllResultsAsStringFromXML(xmlFilePath));
//				System.out.println(Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().getResults());

				// Plot dialog
				DialogPlot dialog = new DialogPlot(new Shell(), getTitle(xmlFilePath));
				dialog.open();
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

package org.openmodelica.modelicaml.simulation.testexecution.actions;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.openmodelica.modelicaml.simulation.testexecution.dialogs.DialogPlot;
import org.openmodelica.modelicaml.simulation.xml.SimulationResult_XML_reader;

public class PlotTestResultsAction implements IWorkbenchWindowActionDelegate {

	private String xmlFilePath = null;
	private DialogPlot dialog;
	boolean canceled = false;
	

	class LongRunningOperation implements IRunnableWithProgress {

		public LongRunningOperation() {
		}

		public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

			monitor.beginTask("Reading simulation results",IProgressMonitor.UNKNOWN);

			dialog.setResults(SimulationResult_XML_reader.getAllResultsAsStringFromXML(xmlFilePath));
			monitor.done();

			if (monitor.isCanceled()) {
				canceled = true;
			}
		}
	}
	
	
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
				
				dialog = new DialogPlot(new Shell(), xmlFilePath);
				
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

	
	private void openError(){
		MessageDialog.openError(new Shell(), "Error opening the simulation results ...", "The file does not exists: \n" + this.xmlFilePath);
	}
	
	
	private boolean fileExists(String pathAbsolute){
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


	public void setXMLFilePath(String path){
		this.xmlFilePath = path;
	}
}

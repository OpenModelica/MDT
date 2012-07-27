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

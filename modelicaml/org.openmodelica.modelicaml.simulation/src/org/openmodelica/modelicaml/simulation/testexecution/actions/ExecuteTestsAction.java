package org.openmodelica.modelicaml.simulation.testexecution.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

public class ExecuteTestsAction implements
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
			
			// TODO: Start the execution as a job.
			MessageDialog.openInformation(new Shell(), "Title", "Starting execution ... ");
			
			
			// TODO: When finished -> run the update action 
			// find the js-files path 
//			UpdateTestExecutionReportDataAction updateAction = new UpdateTestExecutionReportDataAction();
			//updateAction.setPath();
//			// ...
//			updateAction.run(null);
			
			// TODO: When finished -> open report
			// find the html file path 
//			OpenTestExecutionReportAction openReportAction = new OpenTestExecutionReportAction();
//			openReportAction.setPath(html file path);
//			openReportAction.run(null);
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


	public void setXMLFilePath(String path){
		this.xmlFilePath = path;
	}
}

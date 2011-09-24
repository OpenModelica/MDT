package org.openmodelica.modelicaml.simulation.testexecution.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

public class UpdateTestExecutionReportDataAction implements
		IWorkbenchWindowActionDelegate {

	private String path = null;
	
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
					this.path = ((IFile)firstElement).getLocationURI().toString().replaceFirst("file:\\/", "");
				}
			}
		}
		
		if (this.path != null) {
			// TODO find the report_data.js_template in the folder "/includes/", 
			// replace all place holders and save(overwrite) the report_data.js 
			
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

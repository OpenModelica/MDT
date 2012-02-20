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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.openmodelica.modelicaml.simulation.evaluation.ParseJavaScript;

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
					String selectedFile = ((IFile)firstElement).getLocationURI().toString().replaceFirst("file:\\/", "");
					if (selectedFile.contains("report")) {
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
			ParseJavaScript.parseJSTemp_generateJSFile(this.path);	
		}
		else {
			MessageDialog.openError(new Shell(), "Error Occured", "Could not access the verification sessioin report files.");
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

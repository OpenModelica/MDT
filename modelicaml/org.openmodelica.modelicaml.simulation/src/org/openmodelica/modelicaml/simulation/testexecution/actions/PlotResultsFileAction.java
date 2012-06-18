package org.openmodelica.modelicaml.simulation.testexecution.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.openmodelica.modelicaml.simulation.dialogs.DialogPlot;

public class PlotResultsFileAction extends PlotResultsAction implements IWorkbenchWindowActionDelegate {

	private String filePath = null;
	
	@Override
	public void run(IAction action) {
		
		// if it is a .mat file that was selected
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			Object firstElement = ((IStructuredSelection)selection).getFirstElement();
			if (firstElement instanceof IFile) {
				String absolutePath = ((IFile)firstElement).getLocationURI().toString().replaceFirst("file:\\/", "");
				if ( absolutePath.endsWith(".mat") ) {
					this.filePath = ((IFile)firstElement).getLocationURI().toString().replaceFirst("file:\\/", "");
				}
			}
		}
		
		if (this.filePath != null) {
			filePath = filePath.replaceFirst("file://", "");
			if (fileExists(this.filePath)) {
				
				DialogPlot dialog = new DialogPlot(new Shell(), filePath);
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

}

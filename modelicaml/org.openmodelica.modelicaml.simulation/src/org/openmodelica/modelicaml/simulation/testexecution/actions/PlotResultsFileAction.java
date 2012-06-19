package org.openmodelica.modelicaml.simulation.testexecution.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

public class PlotResultsFileAction extends PlotResultsAction implements IWorkbenchWindowActionDelegate {

//	private String filePath = null;
	
	
	@Override
	protected void setSelectedFilePath() {
		// if it is a .mat file that was selected
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			Object firstElement = ((IStructuredSelection)selection).getFirstElement();
			if (firstElement instanceof IFile) {
				String absolutePath = ((IFile)firstElement).getLocationURI().toString().replaceFirst("file:\\/", "");
				if ( absolutePath.endsWith(".mat") ) {
					filePath = ((IFile)firstElement).getLocationURI().toString().replaceFirst("file:\\/", "");
				}
			}
		}
	}
	
	
//	@Override
//	public void run(IAction action) {
//		
//		setSelectedFilePath();
//		
//		if (this.filePath != null) {
//			filePath = filePath.replaceFirst("file://", "");
//			if (fileExists(this.filePath)) {
//				
//				DialogPlot dialog = new DialogPlot(new Shell(), filePath);
////				dialog.open();
//				
//				try {
//					// reset canceled
//					canceled = false;
//					
//					// read results
//					new ProgressMonitorDialog(new Shell()).run(true, true, new LongRunningOperation());
//					
//					//open dialog
//					if ( !canceled) {
//						dialog.open();
//					}
//
//		        } catch (InvocationTargetException e) {
//		          MessageDialog.openError(new Shell(), "Error", e.getMessage());
//		        } catch (InterruptedException e) {
//		          MessageDialog.openInformation(new Shell(), "Cancelled", e.getMessage());
//		        }
//			}
//			else {
//				openError();
//			}
//		}
//		else {
//			openError();
//		}
//	}

}

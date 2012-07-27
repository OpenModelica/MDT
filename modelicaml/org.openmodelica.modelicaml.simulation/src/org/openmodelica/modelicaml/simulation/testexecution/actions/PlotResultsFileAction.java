package org.openmodelica.modelicaml.simulation.testexecution.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

public class PlotResultsFileAction extends PlotResultsAction implements IWorkbenchWindowActionDelegate {

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
}

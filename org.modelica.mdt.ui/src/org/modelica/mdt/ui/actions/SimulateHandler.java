
package org.modelica.mdt.ui.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.modelica.mdt.core.IModelicaSourceFile;

public class SimulateHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection sel = HandlerUtil.getCurrentSelection(event);
		String fullPath = null;

		if (sel instanceof IStructuredSelection) {
			IStructuredSelection structSel = (IStructuredSelection)sel;
			Object firstElem = structSel.getFirstElement();
			if (firstElem instanceof IModelicaSourceFile) {
				IModelicaSourceFile modelicaSrc = (IModelicaSourceFile)firstElem;
				fullPath = modelicaSrc.getResource().getLocation().toString();
			}
		}
		else if (sel instanceof ITextSelection) {
			IEditorPart activeEditor = PlatformUI
					.getWorkbench()
					.getActiveWorkbenchWindow()
                    .getActivePage()
                    .getActiveEditor();
            IEditorInput editorInput = activeEditor.getEditorInput();
            if (editorInput instanceof IFileEditorInput) {
            	IFileEditorInput fileEditorInput = (IFileEditorInput)editorInput;
            	fullPath = fileEditorInput.getFile().getLocation().toString();
            }
		}
		
		if (fullPath != null) {
			Simulator.getInstance().Run(fullPath);
		}

		return null;
	}

}

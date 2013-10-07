// Copyright (c) 2005 by Leif Frenzel. All rights reserved.
// See http://leiffrenzel.de
package org.modelica.mdt.ui.refactoring.ui.actions;

import org.eclipse.core.resources.*;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.*;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.ITextEditor;

import org.modelica.mdt.ui.refactoring.core.*;
import org.modelica.mdt.ui.refactoring.ui.UITexts;
import org.modelica.mdt.ui.refactoring.ui.wizards.RenameFunctionWizard;

/** <p>action that is triggered from the editor context menu.</p>
 * 
 * <p>This action is declared in the <code>plugin.xml</code>.</p>
 * 
 * @author Leif Frenzel
 */
public class RenameFunction implements IEditorActionDelegate {

	private static final String EXT_MO = "mo"; //$NON-NLS-1$

	private ISelection selection;
	private IEditorPart targetEditor;
	private boolean onPropertiesFile;

	private RenameFunctionInfo info = new RenameFunctionInfo();


	// interface methods of IEditorActionDelegate
	/////////////////////////////////////////////

	public void setActiveEditor(final IAction action, final IEditorPart targetEditor) {
		this.targetEditor = targetEditor;
		onPropertiesFile = false;
		IFile file = getFile();
		if (file != null) {
			String fileExtension = file.getFileExtension();
			if (fileExtension != null)
			{
				if (fileExtension.equals(EXT_MO)) {
					onPropertiesFile = true;
				}
			}
		}                           
	}

	public void run(final IAction action) {
		if (!onPropertiesFile) {
			refuse();
		} 
		else {
			if (selection != null && selection instanceof ITextSelection) {
				applySelection((ITextSelection)selection);
				if (saveAll()) {
					openWizard();
				}
			}
		}
	}

	public void selectionChanged(final IAction action, final ISelection selection) {
		this.selection = selection;
	}


	// helper methods
	//////////////////

	private void applySelection(final ITextSelection textSelection) {
		info.setOldName(textSelection.getText());
		info.setNewName(textSelection.getText());
		info.setOffset(textSelection.getOffset());
		info.setSourceFile(getFile());
	}

	private void refuse() {
		String title = UITexts.renameFunction_refuseDlg_title;
		String message = UITexts.renameFunction_refuseDlg_message;
		MessageDialog.openInformation(getShell(), title, message);
	}

	private static boolean saveAll() {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		return IDE.saveAllEditors(new IResource[] { workspaceRoot }, false);
	}

	private void openWizard() {
		RefactoringProcessor processor = new RenameFunctionProcessor(info);
		RenameFunctionRefactoring ref = new RenameFunctionRefactoring(processor);
		RenameFunctionWizard wizard = new RenameFunctionWizard(ref, info);
		RefactoringWizardOpenOperation op = new RefactoringWizardOpenOperation(wizard);
		try {
			String titleForFailedChecks = ""; //$NON-NLS-1$
			op.run( getShell(), titleForFailedChecks );
		} 
		catch (final InterruptedException irex) {
			// operation was cancelled
		}
	}

	private Shell getShell() {
		Shell result = null;
		if (targetEditor != null) {
			result = targetEditor.getSite().getShell();
		} 
		else {
			result = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		}
		return result;
	}

	private final IFile getFile() {
		IFile result = null;
		if (targetEditor instanceof ITextEditor)  {
			ITextEditor editor = (ITextEditor)targetEditor;
			IEditorInput input = editor.getEditorInput();
			if (input instanceof IFileEditorInput) {
				result = ((IFileEditorInput)input).getFile();
			}
		}
		return result;
	}
}

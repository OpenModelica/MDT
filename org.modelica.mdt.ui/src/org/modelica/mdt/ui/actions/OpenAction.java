/******************************************************************************
 * @author Adrian Pop
 * Copyright (c) 2006 Adrian Pop, adrpo@ida.liu.se
 * All rights reserved. 
 * Permission to use, copy, modify, and distribute this software and
 * its documentation for NON-COMMERCIAL purposes and without fee is hereby 
 * granted, provided that this copyright notice appear in all copies and 
 * that both the copyright notice and this permission notice and warranty
 * disclaimer appear in supporting documentation, and that the name of
 * The Author not be used in advertising or publicity pertaining to
 * distribution of the software without specific, written prior permission.
 * 
 * COMMERCIAL use, copy, modification and distribution 
 * is NOT permitted without prior agreement with Adrian Pop.
 * 
 * THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE,
 * INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS
 * OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE
 * OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
 *****************************************************************************/
package org.modelica.mdt.ui.actions;

import java.util.Iterator;

import org.eclipse.core.runtime.Status;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;

import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.OpenStrategy;
import org.eclipse.jface.viewers.IStructuredSelection;

import org.eclipse.jface.text.ITextSelection;

import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.texteditor.IEditorStatusLine;
import org.modelica.mdt.ui.ModelicaLookupException;
import org.modelica.mdt.ui.editor.ModelicaEditor;
import org.modelica.mdt.ui.editor.EditorUtility;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.internal.core.ErrorManager;
//import org.modelica.mdt.ui.util.ExceptionHandler;
import org.modelica.mdt.ui.text.ModelicaCodeResolver;

/**
 * This action opens a Modelica editor on a Modelica element or file.
 * <p>
 * The action is applicable to selections containing elements of
 * type IModelicaElement
 */
public class OpenAction extends SelectionDispatchAction 
{
	
	private ModelicaEditor fEditor;
	
	/**
	 * Creates a new <code>OpenAction</code>. The action requires
	 * that the selection provided by the site's selection provider is of type <code>
	 * org.eclipse.jface.viewers.IStructuredSelection</code>.
	 * 
	 * @param site the site providing context information for this action
	 */
	public OpenAction(IWorkbenchSite site) {
		super(site);
	}
	
	/**
	 * Note: This constructor is for internal use only. Clients should not call this constructor.
	 * @param editor the Modelica editor
	 */
	public OpenAction(ModelicaEditor editor) 
	{
		this(editor.getEditorSite());
		fEditor= editor;
		setEnabled(editor.getEditorInput() != null); // EditorUtility.getEditorInputModelicaElement(fEditor) != null
	}
	
	/* (non-Modelicadoc)
	 * Method declared on SelectionDispatchAction.
	 */
	public void selectionChanged(ITextSelection selection) 
	{
		super.selectionChanged(selection);
	}

	/* (non-Modelicadoc)
	 * Method declared on SelectionDispatchAction.
	 */
	public void selectionChanged(IStructuredSelection selection) {
		setEnabled(checkEnabled(selection));
	}
	
	private boolean checkEnabled(IStructuredSelection selection) {
		if (selection.isEmpty()) {
			return false;
		}

		@SuppressWarnings("rawtypes")
		Iterator iter = selection.iterator();
		while (iter.hasNext()) {
			Object element = iter.next();
			if (element instanceof IModelicaElement) {
				continue;
			}
			if (element instanceof IFile) {
				continue;
			}
			if (element instanceof IStorage) {
				continue;
			}
			return false;
		}
		
		return true;
	}
	
	/* (non-Modelicadoc)
	 * Method declared on SelectionDispatchAction.
	 */
	public void run(ITextSelection selection) {
		if (!isProcessable())
			return;
		try {
			IModelicaElement[] elements= ModelicaCodeResolver.lookup(fEditor, selection);
			if (elements == null || elements.length == 0) {
				IEditorStatusLine statusLine= (IEditorStatusLine) fEditor.getAdapter(IEditorStatusLine.class);
				if (statusLine != null)
					statusLine.setMessage(false, "Lookup of identifier failed!", null); 
				getShell().getDisplay().beep();
				return;
			}
			IModelicaElement element= elements[0];
			if (elements.length > 1) {
				element= OpenActionUtil.selectModelicaElement(elements, getShell(), getDialogTitle(), "Select the link to open");
				if (element == null)
					return;
			}
			run(new Object[] {element} );
		} catch (ModelicaLookupException e) {
			e.printStackTrace();
		}
	}

	private boolean isProcessable() {
		if (fEditor != null) {
			IModelicaElement je= EditorUtility.getEditorInputModelicaElement(fEditor);
			if (je instanceof IModelicaElement)
				return true; // can process non-primary working copies
		}
		return true;
	}
	
	/* (non-Modelicadoc)
	 * Method declared on SelectionDispatchAction.
	 */
	public void run(IStructuredSelection selection) {
		if (!checkEnabled(selection))
			return;
		run(selection.toArray());
	}
	
	/**
	 * Note: this method is for internal use only. Clients should not call this method.
	 * 
	 * @param elements the elements to process
	 */
	public void run(Object[] elements) {
		if (elements == null)
			return;
		for (int i= 0; i < elements.length; i++) {
			Object element= elements[i];
			try {
				element= getElementToOpen(element);
				boolean activateOnOpen= fEditor != null ? true : OpenStrategy.activateOnOpen();
				OpenActionUtil.open(element, activateOnOpen);
			} catch (ModelicaLookupException e) {
				ErrorManager.logError(e); 
				
				ErrorDialog.openError(getShell(), 
					getDialogTitle(),
					"Error opening", Status.OK_STATUS);
			
			} catch (PartInitException x) {
								
				String name= null;
				
				if (element instanceof IModelicaElement) {
					name= ((IModelicaElement) element).getElementName();
				} else if (element instanceof IStorage) {
					name= ((IStorage) element).getName();
				} else if (element instanceof IResource) {
					name= ((IResource) element).getName();
				}
				
				if (name != null) {
					MessageDialog.openError(getShell(),
						"Error opening",  
						"Error opening" + new String[] { name, x.getMessage() });			
				}
			}		
		}
	}
	
	/**
	 * Note: this method is for internal use only. Clients should not call this method.
	 * 
	 * @param object the element to open
	 * @return the real element to open
	 * @throws ModelicaModelException if an error occurs while accessing the Modelica model
	 */
	public Object getElementToOpen(Object object) throws ModelicaLookupException {
		return object;
	}	
	
	private String getDialogTitle() {
		return "Error opening"; 
	}
	
//	private void showError(InvocationTargetException e) {
//		ExceptionHandler.handle(e, getShell(), getDialogTitle(), "Error opening"); 
//	}
}

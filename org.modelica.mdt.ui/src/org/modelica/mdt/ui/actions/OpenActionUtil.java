/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.ui.actions;

import org.eclipse.swt.widgets.Shell;

import org.eclipse.jface.window.Window;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.ui.ModelicaLookupException;
import org.modelica.mdt.ui.editor.EditorUtility;
import org.eclipse.jface.viewers.LabelProvider;

public class OpenActionUtil {

	private OpenActionUtil() {
		// no instance.
	}

	/**
	 * Opens the editor on the given element and subsequently selects it.
	 */
	public static void open(Object element) throws ModelicaLookupException, PartInitException {
		open(element, true);
	}

	/**
	 * Opens the editor on the given element and subsequently selects it.
	 */
	public static void open(Object element, boolean activate) throws ModelicaLookupException, PartInitException {
		try
		{
			EditorUtility.openInEditor((IModelicaElement)element);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Shows a dialog for resolving an ambiguous java element.
	 * Utility method that can be called by subclasses.
	 */
	public static IModelicaElement selectModelicaElement(IModelicaElement[] elements, Shell shell, String title, String message) {

		int nResults= elements.length;

		if (nResults == 0)
			return null;

		if (nResults == 1)
			return elements[0];

		ElementListSelectionDialog dialog= new ElementListSelectionDialog(shell, new LabelProvider());
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setElements(elements);

		if (dialog.open() == Window.OK) {
			Object[] selection= dialog.getResult();
			if (selection != null && selection.length > 0) {
				nResults= selection.length;
				for (int i= 0; i < nResults; i++) {
					Object current= selection[i];
					if (current instanceof IModelicaElement)
						return (IModelicaElement) current;
				}
			}
		}		
		return null;
	}	
}

/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.debug.ui.breakpoints;

import org.eclipse.ui.dialogs.SelectionDialog;
import org.modelica.mdt.debug.ui.MDTDebugUIPlugin;

/**
 * This is a specialization of <code>FilteredItemsSelectionDialog</code> used to present
 * users with a listing of exceptions to select to create exception breakpoints on
 * 
 * @since 3.3
 *
 */
public class AddFailureDialog extends SelectionDialog {
			
	 
	/**
	 * Constructor
	 */
	public AddFailureDialog() {
		super(MDTDebugUIPlugin.getActiveWorkbenchShell());
	}

}

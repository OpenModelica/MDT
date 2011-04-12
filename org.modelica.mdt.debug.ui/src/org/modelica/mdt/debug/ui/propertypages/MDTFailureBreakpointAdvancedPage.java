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
package org.modelica.mdt.debug.ui.propertypages;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class MDTFailureBreakpointAdvancedPage extends MDTBreakpointAdvancedPage {

	private ExceptionFilterEditor fFilterEditor;

	/**
	 * @see org.modelica.mdt.internal.debug.ui.propertypages.MDTBreakpointAdvancedPage#doStore()
	 */
	protected void doStore() {
		super.doStore();
		fFilterEditor.doStore();
	}

	protected void createTypeSpecificEditors(Composite parent) {
		fFilterEditor= new ExceptionFilterEditor(parent, this);
	}
	
	protected Button createButton(Composite parent, String text) {
		Button button= new Button(parent, SWT.CHECK | SWT.LEFT);
		button.setText(text);
		button.setFont(parent.getFont());
		button.setLayoutData(new GridData());
		return button;
	}

}

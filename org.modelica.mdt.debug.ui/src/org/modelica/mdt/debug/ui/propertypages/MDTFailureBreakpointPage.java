/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.debug.ui.propertypages;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.modelica.mdt.debug.core.IMDTFailureBreakpoint;
import org.modelica.mdt.debug.core.breakpoints.MDTExceptionBreakpoint;
import org.modelica.mdt.internal.core.ErrorManager;

/**
 * Implements the property page for a Java exception breakpoint
 */
public class MDTFailureBreakpointPage extends MDTBreakpointPage {

	//widgets
	private Button fCaughtButton;
	private Button fUncaughtButton;
	
	private static final String fgExceptionBreakpointError= PropertyPageMessages.JavaExceptionBreakpointPage_2; 
	/**
	 * @see org.modelica.mdt.internal.debug.ui.propertypages.MDTBreakpointPage#doStore()
	 */
	protected void doStore() throws CoreException {
		super.doStore();
		IMDTFailureBreakpoint breakpoint= (IMDTFailureBreakpoint) getBreakpoint();
		boolean caught= fCaughtButton.getSelection();
		if (caught != breakpoint.isCaught()) {
			breakpoint.setCaught(caught);
		}
		boolean uncaught= fUncaughtButton.getSelection();
		if (uncaught != breakpoint.isUncaught()) {
			breakpoint.setUncaught(uncaught);
		}
	}

	/**
	 * @see org.modelica.mdt.internal.debug.ui.propertypages.MDTBreakpointPage#createTypeSpecificEditors(org.eclipse.swt.widgets.Composite)
	 */
	protected void createTypeSpecificEditors(Composite parent) {
		setTitle(PropertyPageMessages.JavaExceptionBreakpointPage_5);
		IMDTFailureBreakpoint breakpoint= (IMDTFailureBreakpoint) getBreakpoint();
		SelectionAdapter exceptionBreakpointValidator= new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				validateExceptionBreakpoint();
			}
		};
		createLabel(parent, PropertyPageMessages.JavaExceptionBreakpointPage_3); 
		fEnabledButton.addSelectionListener(exceptionBreakpointValidator);
		fCaughtButton= createCheckButton(parent, PropertyPageMessages.JavaExceptionBreakpointPage_0); 
		try {
			fCaughtButton.setSelection(breakpoint.isCaught());
		} catch (CoreException e) {
			ErrorManager.logError(e);
		}
		fCaughtButton.addSelectionListener(exceptionBreakpointValidator);
		fUncaughtButton= createCheckButton(parent, PropertyPageMessages.JavaExceptionBreakpointPage_1); 
		try {
			fUncaughtButton.setSelection(breakpoint.isUncaught());
		} catch (CoreException e) {
			ErrorManager.logError(e);
		}
		fUncaughtButton.addSelectionListener(exceptionBreakpointValidator);
	}

	/**
	 * validates the exception breakpoint
	 */
	private void validateExceptionBreakpoint() {
		if (fEnabledButton.getSelection() && !(fCaughtButton.getSelection() || fUncaughtButton.getSelection())) {
			addErrorMessage(fgExceptionBreakpointError);
		} else {
			removeErrorMessage(fgExceptionBreakpointError);
		}
	}
	
}

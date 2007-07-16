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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;
import org.modelica.mdt.debug.core.IMDTBreakpoint;
import org.modelica.mdt.internal.core.ErrorManager;

/**
 * 
 */
public class MDTBreakpointAdvancedPage extends PropertyPage {

	/**
	 * @see org.eclipse.jface.preference.IPreferencePage#performOk()
	 */
	public boolean performOk() {
		doStore();
		return super.performOk();
	}

	/**
	 * Stores the values configured in this page.
	 */
	protected void doStore() {
		/*
		fThreadFilterEditor.doStore();
		if (fInstanceFilterEditor != null) {
			fInstanceFilterEditor.doStore();
		}
		*/
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		noDefaultAndApplyButton();
		Composite mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setFont(parent.getFont());
		mainComposite.setLayout(new GridLayout());
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		createTypeSpecificEditors(mainComposite);
		createInstanceFilterEditor(mainComposite);
		setValid(true);
		return mainComposite;
	}
	
	public void createInstanceFilterEditor(Composite parent) {
		IMDTBreakpoint breakpoint= getBreakpoint();
		/*
		try {
			IJavaObject[] instances = breakpoint.getInstanceFilters();
			if (instances.length > 0) {
				fInstanceFilterEditor= new InstanceFilterEditor(parent, breakpoint);
			}
		} catch (CoreException e) {
			ErrorManager.logError(e);
		}
		*/
	}

	/**
	 * Allow subclasses to create type-specific editors.
	 * @param parent
	 */
	protected void createTypeSpecificEditors(Composite parent) {
		// Do nothing.
	}


	public IMDTBreakpoint getBreakpoint() {
		return (IMDTBreakpoint) getElement();
	}

}

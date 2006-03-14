/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Link�pings universitet, Department of
 * Computer and Information Science, PELAB
 *
 * All rights reserved.
 *
 * (The new BSD license, see also
 * http://www.opensource.org/licenses/bsd-license.php)
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * * Neither the name of Link�pings universitet nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.modelica.mdt.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.modelica.mdt.core.preferences.PreferenceManager;
import org.modelica.mdt.internal.core.CorePlugin;

/**
 * The 'Modelica' preference page, the top-level page for
 * configuring MDT.
 */
public class ModelicaPreferencePage	extends PreferencePage
	implements IWorkbenchPreferencePage 
{

	private Button displayCompErrors;
	private Button useStandardOmcPath;
	private Button useCustomOmcPath;
	private Text customOmcPath;
	private Button browseButton;
	
	@Override
	protected Control createContents(Composite grandParent)
	{
		Composite parent = new Composite(grandParent, SWT.NULL);

		/* Create a data that takes up the extra space in the dialog */
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		parent.setLayoutData(data);

		GridLayout layout = new GridLayout();
		parent.setLayout(layout);			

		/* page 'title' */
		Label label = new Label(parent, SWT.NONE);
		label.setText("Settings for Modelica development:");
		
		/*
		 * 'error reportting settings' group
		 */
		
		/* dummy label for space */
		new Label(parent, SWT.NONE);
		
		Group group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		group.setText("Error reporting settings");
		
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		group.setLayoutData(data);
		
		layout = new GridLayout();
		group.setLayout(layout);

		displayCompErrors = new Button(group, SWT.CHECK);
		displayCompErrors.setText("Display internal &compatibility errors");

		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		displayCompErrors.setLayoutData(data);
		
		displayCompErrors.setSelection(PreferenceManager.getDisplayCompErrors());
		
		/*
		 * 'OMC path' group
		 */
		
		/* dummy label for space */
		new Label(parent, SWT.NONE);
		
		group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		group.setText("OpenModelica Compiler (OMC) Binary");
		
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		group.setLayoutData(data);
		
		layout = new GridLayout();
		layout.numColumns = 2;
		group.setLayout(layout);
		
		/* use standard path option */
		useStandardOmcPath = new Button(group, SWT.RADIO);
		useStandardOmcPath.setText("use OPENMODELICAHOME specified binary");

		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		data.horizontalSpan = 2;
		useStandardOmcPath.setLayoutData(data);
		
		useStandardOmcPath.setSelection(PreferenceManager.getUseStandardOmcPath());
				
		/* use custom path option */
		useCustomOmcPath = new Button(group, SWT.RADIO);
		useCustomOmcPath.setText("use custom binary:");

		useCustomOmcPath.setSelection(!PreferenceManager.getUseStandardOmcPath());
				
		data = new GridData(GridData.FILL_HORIZONTAL);		
		data.grabExcessHorizontalSpace = true;
		data.horizontalSpan = 2;
		useCustomOmcPath.setLayoutData(data);
		
		customOmcPath = new Text(group, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL |
					GridData.GRAB_HORIZONTAL);
		customOmcPath.setLayoutData(data);

		customOmcPath.setText(PreferenceManager.getCustomOmcPath());
		customOmcPath.setEnabled(useCustomOmcPath.getSelection());
		
		browseButton = new Button(group, SWT.PUSH);
		browseButton.setText("Browse...");
					
		data = new GridData();
		data.horizontalAlignment = GridData.END;
		browseButton.setLayoutData(data);
		
		browseButton.setEnabled(useCustomOmcPath.getSelection());

		/* setup the interactive widget business logic, yeah, multitiers application baby ! */
		useCustomOmcPath.addSelectionListener(new SelectionAdapter()
				{
					public void widgetSelected(SelectionEvent e)
					{
						customOmcPath.setEnabled(true);
						browseButton.setEnabled(true);
					}
				});

		useStandardOmcPath.addSelectionListener(new SelectionAdapter()
				{
					public void widgetSelected(SelectionEvent e)
					{
						customOmcPath.setEnabled(false);
						browseButton.setEnabled(false);
					}
				});

		browseButton.addSelectionListener(new SelectionAdapter()
				{
					public void widgetSelected(SelectionEvent e)
					{
						FileDialog dialog = new FileDialog(getShell());
						dialog.setText("pick OMC binary");
						String res = dialog.open();
						
						if (res != null)
						{
							customOmcPath.setText(res);
						}
					}
				});
		
		
		return parent;
	}
	
	public void init(IWorkbench workbench)
	{
		setPreferenceStore(CorePlugin.getDefault().getPreferenceStore());
	}
	
	protected void performDefaults() 
	{
		IPreferenceStore store = getPreferenceStore();
		/*
		 * reset widgets to default settings 
		 */
		displayCompErrors.setSelection
			(store.getDefaultBoolean(PreferenceManager.DISPLAY_COMPATIBILTY_ERRORS));
		useStandardOmcPath.setSelection
			(store.getDefaultBoolean(PreferenceManager.USE_STANDARD_OMC_PATH));
		useCustomOmcPath.setSelection
			(!store.getDefaultBoolean(PreferenceManager.USE_STANDARD_OMC_PATH));
		customOmcPath.setEnabled(useCustomOmcPath.getSelection());
		browseButton.setEnabled(useCustomOmcPath.getSelection());
		customOmcPath.setText(store.getDefaultString(PreferenceManager.CUSTOM_OMC_PATH));

	}

	
	public boolean performOk() 
	{
		/*
		 * save settings to the store
		 */
		IPreferenceStore store = getPreferenceStore();
		
		store.setValue(PreferenceManager.DISPLAY_COMPATIBILTY_ERRORS,
				displayCompErrors.getSelection());
		store.setValue(PreferenceManager.USE_STANDARD_OMC_PATH,
				useStandardOmcPath.getSelection());
		store.setValue(PreferenceManager.CUSTOM_OMC_PATH,
				customOmcPath.getText());

		return true;
	}
}
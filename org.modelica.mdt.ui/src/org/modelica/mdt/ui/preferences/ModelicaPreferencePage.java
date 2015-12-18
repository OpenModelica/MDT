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

	private Button startOMC;
	private Button displayCompErrors;
	private Button useStandardOmcPath;
	private Button useCustomOmcPath;
	private Button preventDuplicateClassNames;
	private Text customOmcPath;
	private Text omcIgnoreDirectoriesAndFiles;
	private Text omcCommandLineParameters;
	private Text omcLibraries;
	private Text omcGDBMaxStakDepth;
	private Text functionDocTestRegex;
	
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
		 * 'startOMC' group
		 */
		
		/* dummy label for space */
		new Label(parent, SWT.NONE);
		
		Group group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		group.setText("General Settings");
		
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		group.setLayoutData(data);
		
		layout = new GridLayout();
		group.setLayout(layout);

		startOMC = new Button(group, SWT.CHECK);
		startOMC.setText("&Use OpenModelica Compiler (OMC) from Modelica Development Tooling (MDT)");
		Label l = new Label(group, SWT.NONE);
		l.setText("" +
				"If you disable the use of OMC:\n" +
				"- the MDT code assist functionality will not work.\n" +
				"- the outline for Modelica files will not work.\n" +
				"- the MDT console will not work.\n" +
				"- the status bar will display 'Empty Compiler is Online'.\n" +
				"- the editing will be much more responsive.\n" +
				"You need to restart Eclipse if you enable/disable OMC.");
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		startOMC.setLayoutData(data);
		startOMC.setSelection(PreferenceManager.getStartOMC());
		
		/*
		 * 'error reporting settings' group
		 */
		
		/* dummy label for space */
		new Label(parent, SWT.NONE);
		
		group = new Group(parent, SWT.SHADOW_ETCHED_IN);
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
		 * OMC parameters
		 */	
		/* dummy label for space */
		new Label(parent, SWT.NONE);
		group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		group.setText("OMC command line parameters");		
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		group.setLayoutData(data);		
		layout = new GridLayout();
		layout.numColumns = 1;
		group.setLayout(layout);
		
		data = new GridData(GridData.FILL_HORIZONTAL);		
		data.grabExcessHorizontalSpace = true;
		data.horizontalSpan = 1;		
		omcCommandLineParameters = new Text(group, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		omcCommandLineParameters.setText(PreferenceManager.getOMCCommandLineParameters());		
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		omcCommandLineParameters.setLayoutData(data);		
		
		/*
		 * OMC Libraries
		 */	
		/* dummy label for space */
		new Label(parent, SWT.NONE);
		group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		group.setText("OMC libraries");		
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		group.setLayoutData(data);		
		layout = new GridLayout();
		layout.numColumns = 1;
		group.setLayout(layout);
		
		data = new GridData(GridData.FILL_HORIZONTAL);		
		data.grabExcessHorizontalSpace = true;
		data.horizontalSpan = 1;		
		omcLibraries = new Text(group, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		omcLibraries.setText(PreferenceManager.getOMCLibraries());		
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		omcLibraries.setLayoutData(data);	
		
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
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
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

		/*
		 * 'OMC disable directory walking
		 */	
		/* dummy label for space */
		new Label(parent, SWT.NONE);
		group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		group.setText("Disable loading of Modelica files and directories");		
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		group.setLayoutData(data);		
		layout = new GridLayout();
		layout.numColumns = 1;
		group.setLayout(layout);		

		data = new GridData(GridData.FILL_HORIZONTAL);		
		data.grabExcessHorizontalSpace = true;
		data.horizontalSpan = 1;		
		omcIgnoreDirectoriesAndFiles = new Text(group, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		omcIgnoreDirectoriesAndFiles.setText(PreferenceManager.getOMCIgnoredDirectoriesAndFiles());		
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		omcIgnoreDirectoriesAndFiles.setLayoutData(data);
		
		// 'prevent duplicate class names settings' group
		/* dummy label for space */
		new Label(parent, SWT.NONE);
		group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		group.setText("Prevent duplicate class names (check box to turn on experimental feature)");
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		group.setLayoutData(data);
		layout = new GridLayout();
		group.setLayout(layout);
		preventDuplicateClassNames = new Button(group, SWT.CHECK);
		preventDuplicateClassNames.setText("Prevent duplicate class names (experimental)");
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		preventDuplicateClassNames.setLayoutData(data);
		preventDuplicateClassNames.setSelection(PreferenceManager.getPreventDuplicateClassNames());

		// 'MDT GDB Max Stack Depth' group
		/* dummy label for space */
		new Label(parent, SWT.NONE);
		group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		group.setText("MDT GDB Max Stack Depth (default -1 [no limit])");
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		group.setLayoutData(data);
		layout = new GridLayout();
		group.setLayout(layout);
		omcGDBMaxStakDepth = new Text(group, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		omcGDBMaxStakDepth.setText(PreferenceManager.getOMCGDBMaxStackDepth());		
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		omcGDBMaxStakDepth.setLayoutData(data);
		
		/*
		 * added regEx modificator for the search of Function-Documentations
		 */
		/* dummy label for space */
		new Label(parent, SWT.NONE);
		group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		group.setText("The RegEx for function-documentations");
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		group.setLayoutData(data);
		layout = new GridLayout();
		group.setLayout(layout);
		functionDocTestRegex = new Text(group, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		functionDocTestRegex.setText(PreferenceManager.getFunctionDocTestRegex());		
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		functionDocTestRegex.setLayoutData(data);
		
		
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
		startOMC.setSelection
		(store.getDefaultBoolean(PreferenceManager.START_OMC));		
		displayCompErrors.setSelection
			(store.getDefaultBoolean(PreferenceManager.DISPLAY_COMPATIBILTY_ERRORS));
		useStandardOmcPath.setSelection
			(store.getDefaultBoolean(PreferenceManager.USE_STANDARD_OMC_PATH));
		useCustomOmcPath.setSelection
			(!store.getDefaultBoolean(PreferenceManager.USE_STANDARD_OMC_PATH));
		customOmcPath.setEnabled(useCustomOmcPath.getSelection());
		browseButton.setEnabled(useCustomOmcPath.getSelection());
		customOmcPath.setText(store.getDefaultString(PreferenceManager.CUSTOM_OMC_PATH));
		omcIgnoreDirectoriesAndFiles.setText(store.getDefaultString(PreferenceManager.OMC_IGNORED_DIRECTORIES_AND_FILES));
		omcCommandLineParameters.setText(store.getDefaultString(PreferenceManager.OMC_COMMAND_LINE_PARAMETERS));
		omcLibraries.setText(store.getDefaultString(PreferenceManager.OMC_LIBRARIES));
		preventDuplicateClassNames.setSelection(store.getDefaultBoolean(PreferenceManager.PREVENT_DUPLICATE_CLASS_NAMES));
		functionDocTestRegex.setText(store.getDefaultString(PreferenceManager.FUNCDOC_TEST_REGEX));
		
	}

	
	public boolean performOk() 
	{
		/*
		 * save settings to the store
		 */
		IPreferenceStore store = getPreferenceStore();
		
		store.setValue(PreferenceManager.START_OMC,
				startOMC.getSelection());		
		store.setValue(PreferenceManager.DISPLAY_COMPATIBILTY_ERRORS,
				displayCompErrors.getSelection());
		store.setValue(PreferenceManager.USE_STANDARD_OMC_PATH,
				useStandardOmcPath.getSelection());
		store.setValue(PreferenceManager.CUSTOM_OMC_PATH,
				customOmcPath.getText());
		store.setValue(PreferenceManager.OMC_IGNORED_DIRECTORIES_AND_FILES,
				omcIgnoreDirectoriesAndFiles.getText());
		store.setValue(PreferenceManager.OMC_COMMAND_LINE_PARAMETERS,
				omcCommandLineParameters.getText());
		store.setValue(PreferenceManager.OMC_LIBRARIES,
				omcLibraries.getText());
		store.setValue(PreferenceManager.PREVENT_DUPLICATE_CLASS_NAMES,
				preventDuplicateClassNames.getSelection());
		store.setValue(PreferenceManager.FUNCDOC_TEST_REGEX,
				functionDocTestRegex.getText());
		
		//functionDocTestRegex
		return true;
	}
}

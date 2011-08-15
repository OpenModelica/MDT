/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Linkoping University,
 * Department of Computer and Information Science,
 * SE-58183 Linkoping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 
 * AND THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S  
 * ACCEPTANCE OF THE OSMC PUBLIC LICENSE.
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from Linkoping University, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS
 * OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 */
package org.modelica.mdt.debug.gdb.ui.launcher;

import java.awt.Checkbox;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;
import org.modelica.mdt.debug.core.launcher.IMDTConstants;

/**
 * @author Adeel Asghar
 *
 */
public class GDBMainTab extends AbstractLaunchConfigurationTab {

	private Text fProgramFullPath;
	private Button fProgramWorkspaceBrowseButton;
	private Button fProgramFileSystemBrowseButton;
	private Text fWorkDirectory;
	private Button fWorkDirectoryWorkspaceBrowseButton;
	private Button fWorkDirectoryFileSystemBrowseButton;
	private Text fGDBPathTextBox;
	private Button fGDBWorkspaceBrowseButton;
	private Button fGDBFileSystemBrowseButton;
	private Button fDebugCSourceCheckBox;
	private Text fProgramArguments;
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		Font font = parent.getFont();
		Composite comp = new Composite(parent, SWT.NONE);
		setControl(comp);
		GridLayout compLayout = new GridLayout();
		comp.setLayout(compLayout);
		comp.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		comp.setFont(font);
		
		Composite top = new Composite(comp, SWT.NONE);
		GridLayout topLayout = new GridLayout();
		topLayout.verticalSpacing = 0;
		topLayout.horizontalSpacing = 2;		
		topLayout.numColumns = 4;
		top.setLayout(topLayout);
		top.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
		top.setFont(font);
		
		Label programLabel = new Label(top, SWT.NONE);
		programLabel.setText("&Program:");
		GridData gd = new GridData(GridData.BEGINNING);
		programLabel.setLayoutData(gd);
		programLabel.setFont(font);

		fProgramFullPath = new Text(top, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fProgramFullPath.setLayoutData(gd);
		fProgramFullPath.setFont(font);
		fProgramFullPath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
		
		fProgramWorkspaceBrowseButton = createPushButton(top, "&Workspace...", null);
		fProgramWorkspaceBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String result = browseWorkspaceEXEFiles();
				if (result != null)
					fProgramFullPath.setText(result);
			}
		});
		
		fProgramFileSystemBrowseButton = createPushButton(top, "&File System...", null);
		fProgramFileSystemBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String result = browseFileSystemEXEFiles();
				if (result != null)
					fProgramFullPath.setText(result);
			}
		});
		
		Label directoryLabel = new Label(top, SWT.NONE);
		directoryLabel.setText("&Work directory:");
		gd = new GridData(GridData.BEGINNING);
		directoryLabel.setLayoutData(gd);
		directoryLabel.setFont(font);

		fWorkDirectory = new Text(top, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fWorkDirectory.setLayoutData(gd);
		fWorkDirectory.setFont(font);
		fWorkDirectory.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
		
		fWorkDirectoryWorkspaceBrowseButton = createPushButton(top, "&Workspace...", null);
		fWorkDirectoryWorkspaceBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String result = browseWorkspaceDirectory();
				if (result != null)
					fWorkDirectory.setText(result);
			}
		});
		
		fWorkDirectoryFileSystemBrowseButton = createPushButton(top, "&File System...", null);
		fWorkDirectoryFileSystemBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String result = browseFileSystemDirectory();
				if (result != null)
					fWorkDirectory.setText(result);
			}
		});
		
		Label gdbLabel = new Label(top, SWT.NONE);
		gdbLabel.setText("&GDB path:");
		gd = new GridData(GridData.BEGINNING);
		gdbLabel.setLayoutData(gd);
		gdbLabel.setFont(font);

		fGDBPathTextBox = new Text(top, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fGDBPathTextBox.setLayoutData(gd);
		fGDBPathTextBox.setFont(font);
		fGDBPathTextBox.setText("${env_var:OMDEV}\\tools\\mingw\\bin\\gdb.exe");
		fGDBPathTextBox.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
		
		fGDBWorkspaceBrowseButton = createPushButton(top, "&Workspace...", null);
		fGDBWorkspaceBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String result = browseWorkspaceEXEFiles();
				if (result != null)
					fGDBPathTextBox.setText(result);
			}
		});
		
		fGDBFileSystemBrowseButton = createPushButton(top, "&File System...", null);
		fGDBFileSystemBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String result = browseFileSystemEXEFiles();
				if (result != null)
					fGDBPathTextBox.setText(result);
			}
		});
		
		createSeparator(comp, 1);
		Composite middle = new Composite(comp, SWT.NONE);
		GridLayout middleLayout = new GridLayout(1, false);
		middleLayout.verticalSpacing = 0;
		middleLayout.horizontalSpacing = 2;
		middle.setLayout(middleLayout);
		middle.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
		middle.setFont(font);
		fDebugCSourceCheckBox = createCheckButton(middle, "Debug C source files");
		fDebugCSourceCheckBox.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				updateLaunchConfigurationDialog();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		createSeparator(comp, 1);
				
		Composite bottom = new Composite(comp, SWT.NONE);
		GridLayout bottomLayout = new GridLayout(1, false);
		bottom.setLayout(bottomLayout);
		bottom.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		bottom.setFont(font);
		
		Label programArgumentsLabel = new Label(bottom, SWT.NONE);
		gd = new GridData(GridData.BEGINNING);				
		programArgumentsLabel.setText("&Arguments:");		
		programArgumentsLabel.setLayoutData(gd);
		programArgumentsLabel.setFont(font);

		gd = new GridData(GridData.FILL, GridData.FILL, true, true);
		fProgramArguments = new Text(bottom, SWT.MULTI | SWT.BORDER);
		fProgramArguments.setLayoutData(gd);
		fProgramArguments.setFont(font);
		fProgramArguments.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
	}

	/**
	 * Open a resource chooser to select a directory where to run a MDT program
	 * @return String 
	 */
	protected String browseWorkspaceDirectory() {
		// TODO Auto-generated method stub
		ResourceListSelectionDialog dialog = new ResourceListSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(),
				IResource.FOLDER);
		dialog.setTitle("Work directory for the executable");
		dialog.setMessage("Select Directory");
		// TODO: single select
		if (dialog.open() == Window.OK) 
		{
			Object[] files = dialog.getResult();
			IFolder directory = (IFolder) files[0];
			return directory.getLocation().toOSString();
		}
		else
			return null;
	}

	/**
	 * Open a resource chooser to select a program
	 * @return String
	 */
	protected String browseWorkspaceEXEFiles() {
		// TODO Auto-generated method stub
		ResourceListSelectionDialog dialog = new ResourceListSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(),
				IResource.FILE);
		dialog.setTitle("Executable program");
		dialog.setMessage("Select Executable Program");
		// TODO: single select
		if (dialog.open() == Window.OK) 
		{
			Object[] files = dialog.getResult();
			IFile file = (IFile) files[0];
			return file.getLocation().toOSString();
		}
		else
			return null;
	}

	/**
	 * Open a Directory Dialog to select a directory where to run a MDT program
	 * @return String
	 */
	protected String browseFileSystemDirectory() {
		// TODO Auto-generated method stub
		DirectoryDialog directoryDialog = new DirectoryDialog(getShell());
        directoryDialog.setText("Select Directory");
        directoryDialog.setMessage("Work directory for the executable");
        String dir = directoryDialog.open();
    	return dir;
	}

	/**
	 * Open a File Browser to select a program
	 * @return String
	 */
	protected String browseFileSystemEXEFiles() {
		// TODO Auto-generated method stub
		FileDialog fileDialog = new FileDialog(getShell(), SWT.OPEN);
        fileDialog.setText("Select Executable Program");
        String selected = fileDialog.open();
    	return selected;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
	 */
	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		// TODO Auto-generated method stub
		try 
		{
			String program = configuration.getAttribute(IMDTConstants.ATTR_MDT_PROGRAM, (String) null);
			if (program != null) {
				fProgramFullPath.setText(program);
			}
			String programArguments = configuration.getAttribute(IMDTConstants.ATTR_MDT_ARGUMENTS, (String) null);
			if (programArguments != null) {
				fProgramArguments.setText(programArguments);
			}			
			String programWorkDirectory = configuration.getAttribute(IMDTConstants.ATTR_MDT_WORK_DIRECTORY, (String) null);
			if (programWorkDirectory != null) {
				fWorkDirectory.setText(programWorkDirectory);
			}
			String gdbPath = configuration.getAttribute(IMDTConstants.ATTR_MDT_GDB_PATH, (String) null);
			if (gdbPath != null) {
				fGDBPathTextBox.setText(gdbPath);
			}
			String debugCSource = configuration.getAttribute(IMDTConstants.ATTR_MDT_DEBUG_C_SOURCE,  "false");
			if (debugCSource.equals("true")) {
				fDebugCSourceCheckBox.setSelection(true);
			} else {
				fDebugCSourceCheckBox.setSelection(false);
			}
		} catch (CoreException e) {
			setErrorMessage(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
		String program = fProgramFullPath.getText().trim();
		if (program.length() == 0) 
		{
			program = null;
		}
		
		configuration.setAttribute(IMDTConstants.ATTR_MDT_PROGRAM, program);
		String programArguments = fProgramArguments.getText().trim();
		if (programArguments.length() == 0) 
		{
			programArguments = null;
		}
		configuration.setAttribute(IMDTConstants.ATTR_MDT_ARGUMENTS, programArguments);
		
		String programWorkDirectory = fWorkDirectory.getText().trim();
		if (programWorkDirectory.length() == 0) 
		{
			programWorkDirectory = null;
		}
		configuration.setAttribute(IMDTConstants.ATTR_MDT_WORK_DIRECTORY, programWorkDirectory);
		
		String gdbPath = fGDBPathTextBox.getText().trim();
		if (gdbPath.length() == 0) 
		{
			gdbPath = null;
		}
		configuration.setAttribute(IMDTConstants.ATTR_MDT_GDB_PATH, gdbPath);
		
		String debugCSource;
		if (fDebugCSourceCheckBox.getSelection()) {
			debugCSource = "true";
		} else {
			debugCSource = "false";
		}
		configuration.setAttribute(IMDTConstants.ATTR_MDT_DEBUG_C_SOURCE, debugCSource);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Main";
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
	 */
	public boolean isValid(ILaunchConfiguration launchConfig) {
		String text = fProgramFullPath.getText();
		if (text.length() > 0) 
		{
			IPath path = new Path(text);
			java.io.File f = path.toFile();
			if (!(f.exists() && f.isFile())) {
				setErrorMessage("Specified program does not exist.");
				return false;				
			}
		} else {
			setMessage("Specify a program");
		}
		
		String directory = fWorkDirectory.getText();
		if (directory.length() > 0) 
		{
			IPath path = new Path(directory);
			java.io.File f = path.toFile(); 
			if (!(f.exists() && f.isDirectory())) {
				setErrorMessage("Specified work drirectory does not exist or is not a directory.");
				return false;				
			}
		} else {
			//setMessage("Specify a working directory");
		}
		
		return super.isValid(launchConfig);
	}
}

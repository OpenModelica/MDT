/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Linköpings universitet, Department of
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
 * * Neither the name of Linköpings universitet nor the names of its
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

package org.modelica.mdt.ui.wizards;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.ui.UIPlugin;

/**
 * The superclass of the a wizards page used to create new classes and packages.
 * 
 * To create a new type you need to know the folder where the type would be
 * placed. This class implements the 'source folder' and 'package' fields
 * used to determine the location of the new type.
 * 
 * It also implemets the functionality to prefill the 'source folder' and
 * 'package' fields from the selection passed to the wizard.
 * 
 * To use this class create a wizard page that subclasses this class
 * and pass along current selection to init() method. The information from
 * the field can be harvested via the getSourceFolder() and getParentPackage()
 * methods.
 */
public class NewTypePage extends WizardPage
{
	/* widgets */
	private Text sourceFolder;
	private Text parentPackage;
	private Button browsePackages;

	/* field status flags */
	public boolean sourceFolderValid = true;
	public boolean parentPackageValid = true;
	
	private IModelicaProject currentModelicaProject;
	private IModelicaClass currentParentPackage = null;
	
	private Object selection = null;
	private String proccessedParentPackage = "";
	private String proccessedSourceFolder = "";
	public static final String SOURCE_FOLDER_TAG = "newTypeSourceFolderTag";
	public static final String PARENT_PACKAGE_TAG = "newTypeParentPackageTag";
	
	
	public NewTypePage()
	{
		super("");
	}

	public void createControl(Composite parent)
	{
		/*
		 * setup widgets for this page
		 */
		Composite composite = new Composite(parent, SWT.NONE);
		setControl(composite);
		composite.setFont(parent.getFont());
		
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        composite.setLayout(layout);
        
        GridData gd;

        /* source folder field */
        Label l = new Label(composite, SWT.LEFT | SWT.WRAP);
        l.setText("Source folder:");
        gd = new GridData();
        gd.horizontalAlignment = GridData.BEGINNING;
        l.setLayoutData(gd);

	    sourceFolder = new Text(composite, SWT.SINGLE | SWT.BORDER);
        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL |
        					GridData.GRAB_HORIZONTAL);
        sourceFolder.setLayoutData(gd);
        UIPlugin.tag(sourceFolder, SOURCE_FOLDER_TAG);
        
        sourceFolder.addModifyListener(new ModifyListener()
    	{
        	public void modifyText(ModifyEvent e)
        	{
        		if (proccessedSourceFolder.equals(sourceFolder.getText()))
        		{
        			/* don't proccess programaticaly set text */
        			return;
        		}
    	       	processSourceFolderModifyEvent();
        	}
    	});


        Button b = new Button(composite, SWT.PUSH);
        b.setText("Browse...");
        gd = new GridData();
        gd.horizontalAlignment = GridData.END;
        b.setLayoutData(gd);
		b.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				handleBrowseSourceFolder();
			}
		});
		
        /* parent package field */
        l = new Label(composite, SWT.LEFT | SWT.WRAP);
        l.setText("Package:");
        gd = new GridData();
        gd.horizontalAlignment = GridData.BEGINNING;
        l.setLayoutData(gd);

        parentPackage = new Text(composite, SWT.SINGLE | SWT.BORDER);
        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL |
        					GridData.GRAB_HORIZONTAL);
        parentPackage.setLayoutData(gd);
        UIPlugin.tag(parentPackage, PARENT_PACKAGE_TAG);
        parentPackage.addModifyListener(new ModifyListener()
    	{
        	public void modifyText(ModifyEvent e)
        	{
        		if (proccessedParentPackage.equals(parentPackage.getText()))
        		{
        			/* don't proccess programaticaly set text */
        			return;
        		}
        		processParentPackageModifyEvent();
        	}
    	});

        browsePackages = new Button(composite, SWT.PUSH);
        browsePackages.setEnabled(false);
        browsePackages.setText("Browse...");
        gd = new GridData();
        gd.horizontalAlignment = GridData.END;
        browsePackages.setLayoutData(gd);
        browsePackages.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				handleBrowseParentPackage();
			}
		});

        
        /* create separator */
        l = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | 
        				  GridData.GRAB_HORIZONTAL);
        gd.horizontalSpan = 3;
        l.setLayoutData(gd);
        
        processSelection(selection);

	}

	private void checkIfSourceFolderAPackage()
	{
		/*
		 * check if source folder points to a folder package
		 * and update the parent package field in that case
		 */
		IResource container = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(new Path(sourceFolder.getText()));
		
		if (sourceFolderValid)
		{
			/* get the modelica project for the specified source folder path */
			currentModelicaProject = 
				ModelicaCore.getModelicaRoot().getProject
					(container.getProject().getName());
			
			/* 
			 * if the source folder points to a folder package
			 * update the parent package field and save the reference
			 * to that package object
			 */
			try
			{

			IModelicaElement res = 
				currentModelicaProject.findElement
					(container.getProjectRelativePath());
			
				/* check if res was found and it's of type IModelicaClass */
				if (res != null && (res instanceof IModelicaClass))
				{
					/* check if the ras is a package */
					if (((IModelicaClass)res).getRestriction() == 
						IModelicaClass.Restriction.PACKAGE)
					{
						/* update parent package field */
						currentParentPackage = (IModelicaClass) res;
						modifyParentPackageWidget
							(currentParentPackage.getFullName());
					}
				}
			}
			catch (CompilerException e)
			{
				/* ignore errors here */
			}
			catch (CoreException e)
			{
				/* ignore errors here */	
			}

		}

	}

	
	private void processSourceFolderModifyEvent()
	{
		IResource container = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(new Path(sourceFolder.getText()));
		
		/*
		 * check if entered source folder is valid and set error messages
		 * if it's not
		 */
		sourceFolderValid = false;
		
		if (sourceFolder.getText().length() == 0) 
		{
			setErrorMessage("Folder container must be specified");
		}
		else if (container == null ||
		    (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) 
		{
			setErrorMessage("Folder container must exist");
		}
		else if (!container.isAccessible()) 
		{
			setErrorMessage("Project must be writable");			
		}
		else
		{
			sourceFolderValid = true;
			setErrorMessage(null);
		}

		setPageComplete(isPageComplete());
		browsePackages.setEnabled(sourceFolderValid);
		checkIfSourceFolderAPackage();
	}
	
	private void processParentPackageModifyEvent()
	{
		String packageName = parentPackage.getText();


		if (currentModelicaProject == null)
		{
			/*
			 * don't process package field if source field don't
			 * point into some project
			 */
			return;
		}
		try
		{
			currentParentPackage = 
				currentModelicaProject.getClass(packageName);
			
			if (packageName.equals(""))
			{
				parentPackageValid = true;
				setErrorMessage(null);
			}
			else if (currentParentPackage != null)
			{
				IResource res = currentParentPackage.getResource();
				
				if (res.getType() == IResource.FOLDER)
				{
					modifySourceFolderWidget
						(res.getFullPath().makeRelative().toString());
				}
				parentPackageValid = true;
				setErrorMessage(null);
				
			}
			else
			{
				parentPackageValid = false;
				setErrorMessage("'"+ packageName + "' does not exist");
			}
		}
		catch (CompilerException e)
		{
			/* ignore errors here */
		}
		catch (CoreException e)
		{
			/* ignore errors here */
		}
		
		setPageComplete(isPageComplete());
	}	
	
	private void handleBrowseSourceFolder()
	{
		ContainerSelectionDialog dialog =
			new ContainerSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
				"Choose a source folder:");
		
		if (dialog.open() == ContainerSelectionDialog.OK)
		{
			Object[] result = dialog.getResult();
			if (result.length == 1)
			{
				setSourceFolder((IPath) result[0]);
				sourceFolderValid = true;
				setPageComplete(isPageComplete());
				checkIfSourceFolderAPackage();
			}
		}
	}
	
	private void handleBrowseParentPackage()
	{
		ElementTreeSelectionDialog dialog = 
			new ElementTreeSelectionDialog(getShell(),
				new WorkbenchLabelProvider(), 
				new PackageTreeContentProvider());

		dialog.setTitle("Package Selection");
		dialog.setMessage("Choose parent package:");
		dialog.setAllowMultiple(false);
		dialog.setInput(currentModelicaProject);

		if (dialog.open() == ContainerSelectionDialog.OK)
		{
			Object[] result = dialog.getResult();
			if (result.length == 1)
			{
				setParentPackage((IModelicaClass)result[0]);				
			}
		}
		
	}
	
	@Override
	public Composite getControl()
	{
		return (Composite) super.getControl();
	}
	
	/**
	 * set source folder field in the dialog
	 * 
	 * @param path the path to set source folder field to
	 */
	private void setSourceFolder(IPath path)
	{
        if (path != null)
        {	        	
        	modifySourceFolderWidget(path.makeRelative().toString());        	
        }	        			
	}	

	private void setParentPackage(IModelicaClass parentPkg)
	{
		modifyParentPackageWidget(parentPkg.getFullName());
		modifySourceFolderWidget
			(parentPkg.getResource().getFullPath().makeRelative().toString());
		sourceFolderValid = true;
		parentPackageValid = true;
		setPageComplete(isPageComplete());
	}

	/**
	 * this and only this method should be used to modify the parent package
	 * field text widget, otherwise there will be an endless loop generated
	 * 
	 * @param text the text to enter into the parent package field
	 */
	private void modifyParentPackageWidget(String text)
	{
		proccessedParentPackage = text;
		parentPackage.setText(text);		
	}
	
	/**
	 * this and only this method should be used to modify the source folder
	 * field text widget, otherwise there will be an endless loop generated
	 * 
	 * @param text the text to enter into the parent package field
	 */
	private void modifySourceFolderWidget(String text)
	{
		proccessedSourceFolder = text;
		sourceFolder.setText(text);		
	}

	
	public String getSourceFolder()
	{
		return sourceFolder.getText();
	}
	
	public String getParentPackge()
	{
		return parentPackage.getText();
	}

	/**
	 * 
	 * @param selection the elements selected
	 */
	private void processSelection(Object selection) 
	{
		IResource res;
		
		if (selection instanceof IModelicaClass)
		{
			res = ((IModelicaClass)selection).getResource();
			if (res == null)
			{
				/* ignore external classes (e.g. system library stuff) */
				return;
			}
		}
		else if (selection instanceof IModelicaElement)
		{
			res = ((IModelicaElement)selection).getResource();
		}
		else if (selection instanceof IResource)
		{
			res = ((IResource)selection);
		}
		else
		{
			/* ignore all other types of selections */
			return;
		}
		
		/*
		 * calculate the source folder path 
		 */
		IPath path = res.getFullPath();
		
		if (res.getType() == IResource.FILE)
		{
			path = path.removeLastSegments(1);
		}

		setSourceFolder(path);
		sourceFolderValid = true;
		browsePackages.setEnabled(sourceFolderValid);
		setPageComplete(isPageComplete());
		checkIfSourceFolderAPackage();
	}
	
	public void init(IStructuredSelection selection)
	{
		/*
		 * if the current selection is of a single element
		 * save it for later, it is used to prefill the source folder and
		 * package fields when widgets are created 
		 * (when createControl() is called)
		 */
		if (selection == null || selection.size() != 1)
		{
			return;
		}
		
		/* 
		 * now we know that a single element is selected
		 */
		this.selection = selection.getFirstElement();
	}

	@Override
	public boolean isPageComplete()
	{
		return sourceFolderValid && parentPackageValid;
	}
}

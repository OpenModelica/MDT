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
package org.modelica.mdt.ui.wizards;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
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
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.internal.core.ModelicaElement;
import org.modelica.mdt.internal.core.ModelicaPackage;
import org.modelica.mdt.internal.omcproxy.InitializationException;
import org.modelica.mdt.ui.ModelicaImages;

public class NewPackageWizard extends Wizard implements INewWizard
{

	public static final String PACKAGE_NAME_TAG = "packageNameTag";
	public static final String PACKAGE_DESC_TAG = "packageDescTag";
	public static final String SOURCE_FOLDER_TAG = "pkgSourceFolderTag";
	public static final String IS_ENCAPSULATED_TAG = "isEncapsulatedTag";

	private Text sourceFolder;
	@SuppressWarnings("unused")
	private Text parentPackage;
	private Text packageName;
	private Text packageDesc;
	public Button isEncapsulated;
	private NewPackagePage packagePage = new NewPackagePage();

	private class PackageCreator implements IRunnableWithProgress 
	{
		private IPath packagePath;
		private String packageName;
		private boolean isEncapsulated;
		private String description;

		/**
		 * 
		 * @param packagePath
		 * @param packageName
		 * @param description package documentation string, if is "" no
		 *        documentation string generated 
		 * @param isEncapsulated
		 */
		public PackageCreator(IPath packagePath, String packageName,
							String description, boolean isEncapsulated)
		{
			this.packagePath = packagePath;
			this.packageName = packageName;
			this.isEncapsulated = isEncapsulated;
			this.description = description;
		}
		public void run(IProgressMonitor monitor)
				throws InvocationTargetException, InterruptedException 
		{
			monitor.beginTask("Creating package " + packageName, 3);
			/*
			 * generate contents of package.mo
			 */
			String contents = "";
			
			if (isEncapsulated)
			{
				contents += "encapsulated ";
			}
			contents += "package " + packageName;

			if (!description.equals(""))
			{
				contents += " \"" + description + "\"";
			}
			contents += "\n\nend " + packageName + ";";
			
			monitor.worked(1);

			try 
			{
				/*
				 * create the new package's root folder  
				 */

				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				IContainer pkgParent = (IContainer) root.findMember(packagePath);

				IFolder pkgFolder = 
					pkgParent.getFolder(new Path(packageName));

				pkgFolder.create(false, true, null);
				monitor.worked(1);

				/*
				 * create the new package's package.mo file
				 */
				IFile packageMo = 
					pkgFolder.getFile(new Path("package.mo"));

				packageMo.create(new ByteArrayInputStream(contents.getBytes()),
						true, null);
				monitor.worked(1);

			}
			catch (CoreException e) 
			{
				throw new InvocationTargetException(e);
			}
			finally
			{
				monitor.done();
			}
		}
	}

	public class NewPackagePage extends WizardPage
	{
		private Object selection = null;
		protected boolean packageNameValid = false;
		public boolean sourceFolderValid = true;

		public NewPackagePage()
		{
			super("");
		}

		public void createControl(Composite parent)
		{
			setPageComplete(false);
			/*
			 * configure description of this page
			 */
			setTitle("Modelica Package");
			setDescription("Create a new Modelica package.");
			
			setImageDescriptor(ModelicaImages.getImageDescriptor(
					             ModelicaImages.IMG_WIZBAN_PACKAGE));
			
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
	        MdtPlugin.tag(sourceFolder, SOURCE_FOLDER_TAG);
	        
	        sourceFolder.addModifyListener(new ModifyListener()
	    	{
	        	/* check if entered path exists */
	        	public void modifyText(ModifyEvent e)
	        	{
	    	       		sourceFolderChanged();
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
					handleBrowse();
				}
			});

//	        /* parent package field */
//	        l = new Label(composite, SWT.LEFT | SWT.WRAP);
//	        l.setText("Parent package:");
//	        gd = new GridData();
//	        gd.horizontalAlignment = GridData.BEGINNING;
//	        l.setLayoutData(gd);
//	        
//	        parentPackage = new Text(composite, SWT.SINGLE | SWT.BORDER);
//	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL |
//	        					GridData.GRAB_HORIZONTAL);
//	        parentPackage.setLayoutData(gd);
//
//	        
//	        parentPackage.addModifyListener(new ModifyListener()
//	    	{
//	    	      	/* check if entered path exists */
//	    	       	public void modifyText(ModifyEvent e)
//	    	       	{
//	    	       		//sourceFolderChanged();
//	    	       	}
//	    	});
//
//
//	        b = new Button(composite, SWT.PUSH);
//	        b.setText("Browse...");
//	        gd = new GridData();
//	        gd.horizontalAlignment = GridData.END;
//	        b.setLayoutData(gd);
//			b.addSelectionListener(new SelectionAdapter()
//			{
//				public void widgetSelected(SelectionEvent e)
//				{
//					//handleBrowse();
//				}
//			});
			
	        /* create separator */
	        l = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | 
	        				  GridData.GRAB_HORIZONTAL);
	        gd.horizontalSpan = 3;
	        l.setLayoutData(gd);
			
	        
	        /* package name field */
	        l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Name:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        packageName = new Text(composite,  SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL |
	        		          GridData.GRAB_HORIZONTAL);
	        gd.horizontalSpan = 2;
	        packageName.setLayoutData(gd);
	        MdtPlugin.tag(packageName, PACKAGE_NAME_TAG);

	        packageName.addModifyListener(new ModifyListener()
	        {
	        	/* check if entered package name is valid */
	        	public void modifyText(ModifyEvent e)
	        	{
	        		if (!ModelicaElement.isLegalIdentifierName
	        				(packageName.getText()))
	        		{
	        			packageNameValid  = false;
	        			updateStatus("Package name is not valid. Illegal " +
	        					"identifier.",
	        					DialogPage.WARNING);
	        		}
	        		else
	        		{
	        			packageNameValid  = true;
	        			updateStatus(null, DialogPage.NONE);
	        		}
	        	}
	        });
	        packageName.setFocus();

	        /* package description field */
	        l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Description:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        packageDesc = new Text(composite,  SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL |
	        		          GridData.GRAB_HORIZONTAL);
	        gd.horizontalSpan = 2;
	        packageDesc.setLayoutData(gd);
	        MdtPlugin.tag(packageDesc, PACKAGE_DESC_TAG);
	        
	        /* is encapsulated package field */
	        new Label(composite, SWT.NONE); /* empty label for padding */
	        isEncapsulated = new Button(composite, SWT.CHECK);
	        isEncapsulated.setText("is encapsulated package");
	        
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        gd.horizontalSpan = 2;
	        isEncapsulated.setLayoutData(gd);
	        MdtPlugin.tag(isEncapsulated, IS_ENCAPSULATED_TAG);
	        
	        try
	        {
				setSelection(selection);
			}
	        catch (InitializationException e1) 
	        {
				// TODO Proper error handling
				e1.printStackTrace();
				MdtPlugin.log(e1);
			}
		}
		
		private void sourceFolderChanged()
		{
			IResource container = ResourcesPlugin.getWorkspace().getRoot()
					.findMember(new Path(sourceFolder.getText()));

			sourceFolderValid = false;
			if (sourceFolder.getText().length() == 0) {
				updateStatus("File container must be specified", 
						DialogPage.ERROR);
				return;
			}
			if (container == null ||
			    (container.getType() & 
			      (IResource.PROJECT | IResource.FOLDER)) == 0) 
			{
				updateStatus("File container must exist", DialogPage.ERROR);
				return;
			}
			if (!container.isAccessible()) {
				updateStatus("Project must be writable", DialogPage.ERROR);
				return;
			}
			sourceFolderValid = true;
			updateStatus(null, DialogPage.NONE);
		}

		private void handleBrowse()
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
				}
			}
		}

		private void setSelection(Object selection) 
			throws InitializationException
		{
			IResource res;
			
			if (selection instanceof ModelicaPackage)
			{
				/*
				 *  ModelicaPackage used instead of IModelicaPackage
				 * to ignore modelica packages elements that are listed under
				 * system library subtree
				 */

				//setParentPackage((ModelicaPackage)selection);
				res = ((ModelicaPackage)selection).getResource();
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

		}
		
		/**
		 * set source folder field in the dialog
		 * 
		 * @param path the path to set source folder field to
		 */
		public void setSourceFolder(IPath path)
		{
	        if (path != null)
	        {	        	
	        	sourceFolder.setText(path.makeRelative().toString());
	        	sourceFolderChanged();
	        }	        			
		}
		
//		/**
//		 * set parent package field in the dialog
//		 * 
//		 * @param parentPkg the parent package to set or null to set
//		 * empty parent package 
//		 */
//		private void setParentPackage(ModelicaPackage parentPkg) 
//		{
//			if (parentPkg == null)
//			{
//				parentPackage.setText("");
//			}
//			else
//			{
//				parentPackage.setText(parentPkg.getFullName());
//			}
//		}

		public void init(IStructuredSelection selection)
		{
			if (selection == null || selection.size() != 1)
			{
				return;
			}
			
			/* 
			 * now we know that a single element is selected
			 */
			this.selection = selection.getFirstElement();
		}
		
		
		private void updateStatus(String message, int messageType)
		{
			setMessage(message, messageType);
			setPageComplete((packageNameValid && sourceFolderValid));
		}

	}

	public NewPackageWizard()
	{
		super();
		setNeedsProgressMonitor(true);
	}

	
	@Override
	public boolean performFinish()
	{
		IRunnableWithProgress runable = 
			new PackageCreator(new Path(sourceFolder.getText()),
							   packageName.getText(),
							   packageDesc.getText(),
							   isEncapsulated.getSelection());

		try
		{
			getContainer().run(true, false, runable);
		}
		catch (InterruptedException e)
		{
			return false;
		}
		catch (InvocationTargetException e)
		{
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", 
					realException.getMessage());
			return false;
		}
		return true;
	}


	public void init(IWorkbench workbench, IStructuredSelection selection)
	{
		packagePage.init(selection);
		setWindowTitle("New Modelica Package");
	}

	@Override
	public void addPages()
	{
		addPage(packagePage);
	}
}


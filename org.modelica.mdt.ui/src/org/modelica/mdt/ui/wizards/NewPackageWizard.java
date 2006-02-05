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
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.ui.ModelicaImages;
import org.modelica.mdt.ui.UIPlugin;

/**
 * Implements New Modelica Package wizard.
 * 
 * @author Homer Simpson
 */
public class NewPackageWizard extends Wizard implements INewWizard
{

	public static final String PACKAGE_NAME_TAG = "packageNameTag";
	public static final String PACKAGE_DESC_TAG = "packageDescTag";
	public static final String IS_ENCAPSULATED_TAG = "isEncapsulatedTag";

	private Text packageName;
	private Text packageDesc;
	public Button isEncapsulated;
	private NewPackagePage packagePage = new NewPackagePage();

	private class PackageCreator implements IRunnableWithProgress 
	{
		private IPath packagePath;
		private String parentPackage;
		private String packageName;
		private boolean isEncapsulated;
		private String description;

		/**
		 * 
		 * @param packagePath
		 * @param parentPackage parent package full name or "" to create
		 *        a top level package
		 * @param packageName
		 * @param description package documentation string, if is "" no
		 *        documentation string generated 
		 * @param isEncapsulated
		 */
		public PackageCreator(IPath packagePath, String parentPackage,
						    String packageName, String description, 
						    boolean isEncapsulated)
		{
			this.packagePath = packagePath;
			this.parentPackage = parentPackage;
			this.packageName = packageName;
			this.isEncapsulated = isEncapsulated;
			this.description = description;
		}
		
		/**
		 * the workhorse of this class, contains the actual code
		 * that creates the folder and files of the package
		 * 
		 * @param monitor where the progress of creation of the package is 
		 *  reported
		 */
		private void createPackage(IProgressMonitor monitor)
			throws CoreException
		{
			monitor.beginTask("Creating package " + packageName, 2);
			/*
			 * generate contents of package.mo
			 */
			String contents = "";
			
			if (!parentPackage.equals(""))
			{
				/* generate within clause if we are not top level package */
				contents += "within " + parentPackage + ";\n\n";
			}
			
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

			monitor.done();

		}
		public void run(IProgressMonitor monitor)
				throws InvocationTargetException, InterruptedException 
		{
			/*
			 * run all package creation operations inside a IWorkspaceRunnble
			 * to provide better batching of change events
			 */
			IWorkspaceRunnable wr = new IWorkspaceRunnable()
			{
				public void run(IProgressMonitor monitor) throws CoreException
				{
					createPackage(monitor);
				}
			};
			
			try
			{
				ResourcesPlugin.getWorkspace().run(wr, monitor);
			}
			catch (CoreException e) 
			{
				throw new InvocationTargetException(e);
			}
		}
	}

	public class NewPackagePage extends NewTypePage
	{
		protected boolean packageNameValid = false;
		
		public void createControl(Composite parent)
		{
			super.createControl(parent);
			setPageComplete(false);
			
			Composite composite = getControl();
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
	        GridData gd;

	        /* package name field */
	        Label l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Name:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        packageName = new Text(composite,  SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL |
	        		          GridData.GRAB_HORIZONTAL);
	        gd.horizontalSpan = 2;
	        packageName.setLayoutData(gd);
	        UIPlugin.tag(packageName, PACKAGE_NAME_TAG);

	        packageName.addModifyListener(new ModifyListener()
	        {
	        	/* check if entered package name is valid */
	        	public void modifyText(ModifyEvent e)
	        	{
	        		if (!ModelicaCore.isLegalIdentifierName
	        				(packageName.getText()))
	        		{
	        			packageNameValid  = false;
	        			setErrorMessage("Package name is not valid. Illegal " +
	        					"identifier.");
	        		}
	        		else
	        		{
	        			packageNameValid  = true;
	        			setErrorMessage(null);
	        		}
	        		setPageComplete(isPageComplete());
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
	        UIPlugin.tag(packageDesc, PACKAGE_DESC_TAG);
	        
	        /* is encapsulated package field */
	        new Label(composite, SWT.NONE); /* empty label for padding */
	        isEncapsulated = new Button(composite, SWT.CHECK);
	        isEncapsulated.setText("is encapsulated package");
	        
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        gd.horizontalSpan = 2;
	        isEncapsulated.setLayoutData(gd);
	        UIPlugin.tag(isEncapsulated, IS_ENCAPSULATED_TAG);
	        

		}

		@Override
		public boolean isPageComplete()
		{
			return (packageNameValid && super.isPageComplete());
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
			new PackageCreator(new Path(packagePage.getSourceFolder()),
							   packagePage.getParentPackge(),
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

